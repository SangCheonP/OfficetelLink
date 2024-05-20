import { useToast } from 'vue-toastification';
const defaultOptions = {
    msg: 'My toast content',
    timeout: 3000,
    type: 'default', // or "success", "error", "default", "info" and "warning"
    closeButton: 'button', // button 버튼나타남, false 버튼없음
    showCloseButtonOnHover: true, // true, false
    hideProgressBar: false, // true, false
    pauseOnHover: false, // true, false
    position: 'top-center', // top-right, top-center, top-left, bottom-right, bottom-center, bottom-left.
    icon: true, //기본아이콘 true, 아이콘 컴포넌트가 있다면 해당 컴포넌트를 연결해도 됨.
};
const toast = useToast();

export default {
    toast: {
        setToast: (options) => {
            const mergedOptions = { ...defaultOptions, ...options };
            toast(mergedOptions.msg, mergedOptions);
        },
        successToast: (msg) => {
            const options = {
                type: 'success',
            };
            const mergedOptions = { ...defaultOptions, ...options };
            toast(msg, mergedOptions);
        },
        errorToast: (msg) => {
            const options = {
                type: 'error',
            };
            const mergedOptions = { ...defaultOptions, ...options };
            toast(msg, mergedOptions);
        },
        defaultToast: (msg) => {
            const options = {
                type: 'default',
            };
            const mergedOptions = { ...defaultOptions, ...options };
            toast(msg, mergedOptions);
        },
        infoToast: (msg) => {
            const options = {
                type: 'info',
            };
            const mergedOptions = { ...defaultOptions, ...options };
            toast(msg, mergedOptions);
        },
        warningToast: (msg) => {
            const options = {
                type: 'warning',
            };
            const mergedOptions = { ...defaultOptions, ...options };
            toast(msg, mergedOptions);
        },
    },
};