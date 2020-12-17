package com.qiqv.music.utils;

/**
 * 自定义响应数据结构
 *  对接口的请求进行响应
 */
public class QiqvJSONResult {

    /**
     * 响应状态码
     */
    private Integer code;
    /**
     * 响应信息
     */
    private String msg;
    /**
     * 响应体
     */
    private Object data;

    //------------ 封装常用的工具方法 ----------------

    /** 请求成功，有返回值
     * @param data
     * @return
     */
    public static QiqvJSONResult ok(Object data){
        return new QiqvJSONResult(data);
    }

    /**请求成功，无返回值
     * @return
     */
    public static QiqvJSONResult ok(){
        return new QiqvJSONResult(null);
    }

    /** 请求失败，服务器异常500
     * @param msg
     * @return
     */
    public static QiqvJSONResult errorMsg(String msg){
        return new QiqvJSONResult(500,msg,null);
    }


    /**
     * 请求失败，用户未登录
     * @param msg
     * @return
     */
    public static QiqvJSONResult noAuth(String msg){
        return new QiqvJSONResult(401,msg,null);
    }

    public QiqvJSONResult(Object data){
        this.code=200;
        this.msg="SUCCESS";
        this.data=data;
    }




    public QiqvJSONResult(Integer code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public boolean isOK(){
        return this.code==200;
    }
    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
