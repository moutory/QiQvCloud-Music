const user = {
    state: {
        avator: '',
        username: '',
        userId: '',
        userToken: '',  // 用户token，用户确认当前用户是否登录
    },
    getters: {
        avator: state => {
            let avator = state.avator;
            if(!avator){
                avator =  JSON.parse(window.sessionStorage.getItem('avator'));
            }
            return avator;
        },
        username: state => {
            let username = state.username;
            if(!username){
                username =  JSON.parse(window.sessionStorage.getItem('username'));
            }
            return username;
        },
        userId: state => {
            let userId = state.userId;
            if(!userId){
                userId =  JSON.parse(window.sessionStorage.getItem('userId'));
            }
            return userId;
        },
        userToken: state => {
            let userToken = state.userToken;
            if(!userToken){
                userToken = JSON.parse(window.sessionStorage.getItem('userToken'));
            }
            return userToken;
        },
    },
    mutations: {
        setAvator: (state,avator) => {
            state.avator = avator;
            window.sessionStorage.setItem('avator',JSON.stringify(avator));
        }, 
        setUsername: (state,username) => {
            state.username = username;
            window.sessionStorage.setItem('username',JSON.stringify(username));
        }, 
        setUserId: (state,userId) => {
            state.userId = userId;
            window.sessionStorage.setItem('userId',JSON.stringify(userId));
        }, 
        setUserToken: (state,userToken) => {
            state.userToken = userToken;
            window.sessionStorage.setItem('userToken',JSON.stringify(userToken));
        }, 
    }
}
export default user;