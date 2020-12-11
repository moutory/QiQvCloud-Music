import axios from 'axios';
import router from '../router';

axios.defaults.timeout=5000; // 超时时间为5秒
axios.defaults.withCredentrials=true; // 允许跨域
// 设置响应头content-type
axios.defaults.headers.post['Content-Type'] = 'application/json;charset=UTF-8';
// 基础URL
axios.defaults.baseURL= 'http://localhost:8888';

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