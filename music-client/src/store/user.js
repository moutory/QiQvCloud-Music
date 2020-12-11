const user = {
    state: {
        avator: '',
        username: '',
        userId: '',
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
    }
}
export default user;