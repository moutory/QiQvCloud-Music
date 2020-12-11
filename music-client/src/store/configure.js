const configure = {
    state: {
        HOST: 'http://localhost:8888',
        activeName: '',
        showAside: false,                // 是否显示播放列表
        loginIn: false,                 // 用户是否已经登录
        isActive: false,                 //  当前播放歌曲是否已经被收藏
    },
    getters: {
        activeName: state =>{
            let activeName = state.activeName;
            if(!activeName){
                return JSON.parse(window.sessionStorage.getItem('activeName'));
            }
            return activeName
        },
        showAside: state =>{
            let showAside = state.showAside;
            if(!showAside){
                return JSON.parse(window.sessionStorage.getItem('showAside'));
            }
            return showAside
        },
        loginIn: state => {
            let loginIn = state.loginIn;
            if(!loginIn){
                return JSON.parse(window.sessionStorage.getItem('loginIn'));
            }
            return loginIn;
        },
        isActive: state => {
            let isActive = state.isActive;
            if(!isActive){
                return JSON.parse(window.sessionStorage.getItem('isActive'));
            }
            return isActive;
        },
    },
    mutations: {
        setActiveName: (state,activeName) =>{
            state.activeName = activeName;
            window.sessionStorage.setItem('activeName',JSON.stringify(activeName));
        },
        setShowAside: (state,showAside) =>{
            state.showAside = showAside;
            window.sessionStorage.setItem('showAside',JSON.stringify(showAside));
        },
        setLoginIn: (state,loginIn) => {
            state.loginIn = loginIn;
            window.sessionStorage.setItem('loginIn',JSON.stringify(loginIn));
        },
        setIsActive: (state,isActive) => {
            state.isActive = isActive;
            window.sessionStorage.setItem('isActive',JSON.stringify(isActive));
        },
    }
}

export default configure;