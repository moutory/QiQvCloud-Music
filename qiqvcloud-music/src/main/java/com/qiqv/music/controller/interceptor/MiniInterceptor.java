package com.qiqv.music.controller.interceptor;

import com.qiqv.music.utils.JSONUtils;
import com.qiqv.music.utils.QiqvJSONResult;
import com.qiqv.music.utils.RedisOperator;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;

/**
 * 自定义拦截器类
 */
public class MiniInterceptor implements HandlerInterceptor {

    @Autowired
    private RedisOperator redisOperator;

    // token规则为 user-reids-token:userId : UUID
    private static String USER_REDIS_TOKEN = "user-redis-token";


    /**
     * 判断用户是否登录
     *  若用户userId不存在，则为未登录
     *  若用户userId存在，则判断token是否存在
     *      若存在，则用户状态为已登录
     *      若不存在，则用户状态为登录超时
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     *
     */
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 如果是 嗅探请求，则直接放行
        if("OPTIONS".equals(request.getMethod())){
            return true;
        }
        String userId = request.getHeader("userId");
        String userOldToken = request.getHeader("userToken");
        if(StringUtils.isNotBlank(userId) && StringUtils.isNotBlank(userOldToken)){
            String userTokenKey = USER_REDIS_TOKEN + ":" + userId;
            String userToken = redisOperator.getValue(userTokenKey);
            // 用户有token，但最新token为空，说明登录状态过期
            if(StringUtils.isBlank(userToken)){
                returnErrorResponse(response, QiqvJSONResult.noAuth("登录过期，请重新登录"));
                return false;
            }
            // 两个token不一致，可能是恶意用户乱填token
            if(!userOldToken.equals(userToken)){
                returnErrorResponse(response, QiqvJSONResult.noAuth("无效token，请重新登录"));
                return false;
            }
        }else{
            System.out.println("该用户没有登录");
            returnErrorResponse(response, QiqvJSONResult.noAuth("请登录后再操作"));
            return false;
        }
        return true;
    }

    public void returnErrorResponse(HttpServletResponse response, QiqvJSONResult qiqvJSONResult) throws IOException {
        OutputStream outputStream = null ;
        try {
            response.setContentType("application/json");
            response.setCharacterEncoding("utf-8");
            outputStream = response.getOutputStream();
            outputStream.write(JSONUtils.objectToJson(qiqvJSONResult).getBytes("UTF-8"));
            outputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(outputStream != null){
                outputStream.close();
            }
        }
    }


}
