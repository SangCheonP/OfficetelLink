import { localAxios } from "@/util/http-commons";

const local = localAxios();

async function getRecentNotice(email, success, fail){
    await local.get(`/notices/${email}/10notices`).then(success).catch(fail);
}

export{
    getRecentNotice
}