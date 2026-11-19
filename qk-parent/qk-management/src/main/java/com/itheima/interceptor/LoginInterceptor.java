package com.itheima.interceptor;

import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpStatus;
import com.itheima.common.utils.JwtUtil;
import com.itheima.common.utils.UserHolder;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.jspecify.annotations.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;


/**
 * 自定义请求拦截器
 */
@Slf4j
@Component
public class LoginInterceptor implements HandlerInterceptor {

    /**
     * Controller的每一个方法执行前调用
     * 返回true：放行，继续执行Controller方法
     * 返回false：拦截，不执行Controller方法
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("preHandle ....");
        String token = request.getHeader("token");
        log.info("-------------token: {}", token);
        if (StrUtil.isBlank(token)){
            log.info("token为空,请登录");
            response.setStatus(HttpStatus.HTTP_UNAUTHORIZED);
            return false;
        }
        try {
            Claims claims = JwtUtil.parseToken(token);
            Integer userId = claims.get("id", Integer.class);
            UserHolder.setCurrentUser(userId);
        } catch (Exception e) {
            log.info("token不合法");
            response.setStatus(HttpStatus.HTTP_UNAUTHORIZED);
            return false;
        }
        return true; // 放行
    }


    /**
     * Controller方法正常执行结束后调用
     * 注意：如果Controller执行时发生未处理的异常，
     * 通常不会执行这个方法
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable ModelAndView modelAndView) throws Exception {
        System.out.println("postHandle ....");
    }


    /**
     * 整个请求在服务器端处理结束时调用
     * 无论Controller正常执行还是发生异常，
     * 一般都会执行，常用于最后的收尾工作
     *
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable Exception ex) throws Exception {
        System.out.println("afterCompletion ....");
        //用户删除
        UserHolder.removeCurrentUser();
    }
}
