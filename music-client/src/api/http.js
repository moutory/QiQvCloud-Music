import axios from 'axios';
import router from '../router';


axios.defaults.timeout=5000; // 超时时间为5秒
axios.defaults.withCredentrials=true; // 允许跨域
// 设置响应头content-type
axios.defaults.headers.post['Content-Type'] = 'application/json;charset=UTF-8';
// 基础URL
axios.defaults.baseURL= 'http://localhost:8888';

// 设置请求拦截器
axios.interceptors.request.use(function (config) {
    // Do something before request is sent
    //window.localStorage.getItem("accessToken") 获取token的value
    let token = JSON.parse(window.sessionStorage.getItem('userToken'));
    let userId = JSON.parse(window.sessionStorage.getItem('userId'));
    if (token && userId) {
        //将token放到请求头发送给服务器,将tokenkey放在请求头中
        console.log(token);
        console.log(userId);
        config.headers.userId = userId;
        config.headers.userToken = token;     
        //也可以这种写法
        // config.headers['accessToken'] = token;
    }
    return config;
}, function (error) {
    // Do something with request error
    return Promise.reject(error);
});


// 定义响应器
axios.interceptors.response.use(
    response => {
        if(response.status==200){
            return Promise.resolve(response);
        }else{
            return Promise.reject(response);
        }
    },
    error=>{
        if(error.response.status){
            switch(error.response.status){
                case 401:
                router.replace({
                    path: '/',
                    query: {
                        redirect: router.currentRoute.fullPath
                    }
                });
                break;
                case 404:
                    break;
            }
            return Promise.reject(error.response);
        }
    }
);

/**
 * 封装get方法
 */
export function get(url,params={}){
    return new Promise((resolve,reject)=>{
        axios.get(url,{params:params})
        .then(response =>{
            resolve(response.data);
        })
        .catch(err => {
            reject(err);
        })
    });
}

export function post(url,data){
    return new Promise((resolve,reject)=>{
        axios.post(url,data)
        .then(response => {
            resolve(response.data);
        })
        .catch(err =>{
            reject(err);
        })
    });
}