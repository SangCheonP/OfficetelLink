package com.ssafy.offistellink.user.controller;

import com.ssafy.offistellink.user.model.dto.UserDto;
import com.ssafy.offistellink.user.model.service.UserService;
import com.ssafy.offistellink.util.JWTUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Tag(name = "User", description = "유저 정보 관리")
@RequestMapping("/user")
@CrossOrigin("*")
@RestController
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private JWTUtil jwtUtil;

    // 모든 유저 조회
    // GET /user
    @Operation(summary = "모든 유저 조회", description = "모든 유저의 정보를 조회")
    @GetMapping("")
    public ResponseEntity<?> list() throws Exception {
        List<UserDto> users = userService.getAllUser();
        return ResponseEntity.ok(users);
    }

    // 유저 추가
    // POST /user/register
    @Operation(summary = "회원가입", description = "아이디와 비밀번호 등을 이용하여 회원가입 처리")
    @PostMapping("/register")
    public ResponseEntity<String> addUser(@RequestBody UserDto userDto) throws Exception {
        if (userService.addUser(userDto)) {
            return ResponseEntity.ok("사용자가 성공적으로 등록되었습니다.");
        } else {
            return ResponseEntity.badRequest().body("사용자 등록에 실패했습니다.");
        }
    }

    // 모든 유저 메일 가져오기
    // GET /user/email
    @Operation(summary = "모든 메일 조회", description = "모든 유저의 이메일을 가져옴")
    @GetMapping("/email")
    public ResponseEntity<List<String>> getAllEmail() throws Exception {
        List<String> emailList = userService.getAllEmail();

        if (emailList.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(emailList);
        }
    }

    // 로그인
    // GET /user/login
    @Operation(summary = "로그인", description = "아이디와 비밀번호를 이용하여 로그인 처리")
    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> login(
            @RequestBody @Parameter(description = "로그인 시 필요한 회원정보(아이디, 비밀번호).", required = true) UserDto userDto
    ) {
        log.debug("login user : {}", userDto);
        Map<String, Object> resultMap = new HashMap<String, Object>();
        HttpStatus status = HttpStatus.ACCEPTED;

        try {
            UserDto loginUser = userService.login(userDto);
            if (loginUser != null) {
                String accessToken = jwtUtil.createAccessToken(loginUser.getEmail());
                String refreshToken = jwtUtil.createRefreshToken(loginUser.getEmail());

                log.debug("access token : {}", accessToken);
                log.debug("refresh token : {}", refreshToken);

                // 발급받은 refresh token 을 DB에 저장
                userService.saveRefreshToken(loginUser.getEmail(), refreshToken);

                // JSON 으로 token 전달
                resultMap.put("access-token", accessToken);
                resultMap.put("refresh-token", refreshToken);

                status = HttpStatus.CREATED;
            } else {
                resultMap.put("message", "아이디 또는 패스워드를 확인해 주세요.");
                status = HttpStatus.UNAUTHORIZED;
            }
        } catch (Exception e) {
            log.debug("로그인 에러 발생 : {}", e);
            resultMap.put("message", e.getMessage());
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }

        return new ResponseEntity<Map<String, Object>>(resultMap, status);
    }

    //  회원인증
    // GET /user/info/{email}
    @Operation(summary = "회원인증", description = "회원 정보를 담은 Token 을 반환한다.")
    @GetMapping("/info/{email}")
    public ResponseEntity<Map<String, Object>> getInfo(
            @PathVariable("email") @Parameter(description = "인증할 회원의 이메일", required = true) String email,
            HttpServletRequest request) {

        Map<String, Object> resultMap = new HashMap<>();
        HttpStatus status = HttpStatus.ACCEPTED;

        if (jwtUtil.checkToken(request.getHeader("Authorization"))) {
            log.info("사용 가능한 토큰!");
            try {
                // 로그인 사용자 정보
                UserDto userDto = userService.userInfo(email);
                resultMap.put("userInfo", userDto);
                status = HttpStatus.OK;
            } catch (Exception e) {
                log.error("정보조회 실패 : {}", e);
                resultMap.put("message", e.getMessage());
                status = HttpStatus.INTERNAL_SERVER_ERROR;
            }
        } else {
            log.error("사용 불가능 토큰!!!");
            status = HttpStatus.UNAUTHORIZED;
        }

        return new ResponseEntity<Map<String, Object>>(resultMap, status);
    }

    // A/T 재발급
    // POST /user/refresh
    @Operation(summary = "Access Token 재발급", description = "만료된 access token 을 재발급 받는다.")
    @PostMapping("/refresh")
    public ResponseEntity<?> refreshToken(@RequestBody UserDto userDto, HttpServletRequest request)
            throws Exception {
        Map<String, Object> resultMap = new HashMap<>();
        HttpStatus status = HttpStatus.ACCEPTED;
        String token = request.getHeader("refreshToken");
        log.debug("token : {}, userDto : {}", token, userDto);

        // DB에 저장되어 있는 refresh token과 비교 후 재발급
        if (jwtUtil.checkToken(token)) {
            if (token.equals(userService.getRefreshToken(userDto.getEmail()))) {
                String accessToken = jwtUtil.createAccessToken(userDto.getEmail());

                log.debug("token : {}", accessToken);
                log.debug("정상적으로 access token 재발급!!!");

                resultMap.put("access-token", accessToken);

                status = HttpStatus.CREATED;
            }
        } else {
            log.debug("refresh token 도 사용 불가!!!!!!!");
            status = HttpStatus.UNAUTHORIZED;
        }
        return new ResponseEntity<Map<String, Object>>(resultMap, status);
    }

    @Operation(summary = "로그아웃", description = "회원 정보를 담은 Token 을 제거한다.")
    @GetMapping("/logout/{email}")
    public ResponseEntity<?> removeToken(@PathVariable ("email") @Parameter(description = "로그아웃 할 회원의 이메일", required = true) String email){
        Map<String, Object> resultMap = new HashMap<>();
        HttpStatus status = HttpStatus.ACCEPTED;

        try{
            userService.deleteRefreshToken(email);
            status = HttpStatus.OK;
        }catch (Exception e){
            log.error("로그아웃 실패 : {}", e);
            resultMap.put("message", e.getMessage());
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }

        return new ResponseEntity<Map<String, Object>>(resultMap, status);
    }
}
