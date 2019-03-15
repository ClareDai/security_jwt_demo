package cn.claredai.security;

import cn.claredai.model.JwtUser;
import cn.claredai.util.JsonResult;
import cn.claredai.util.JwtTokenUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * @Author daixiaosong
 * @Date create in 14:41 2019/3/7
 */
public class JwtLoginFilter extends UsernamePasswordAuthenticationFilter {
    private AuthenticationManager authenticationManager;
    private ThreadLocal<Boolean> rememberMe = new ThreadLocal<>();

    public JwtLoginFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
//        super.setFilterProcessesUrl("/admin/login");
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request,
                                                HttpServletResponse response) throws AuthenticationException {

        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String isRememberMe = request.getParameter("isRememberMe");

        JwtUser user = new JwtUser(username,password,null);
        user.setIsRemember(Boolean.parseBoolean(isRememberMe));

        rememberMe.set(user.getIsRemember());
        return authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        user.getUsername(),
                        user.getPassword(),
                        new ArrayList<>())
        );

    }

    /**
     * 成功验证后调用的方法
     * 如果验证成功，就生成token并返回
     */
    @Override
    protected void successfulAuthentication(HttpServletRequest request,
                                            HttpServletResponse response,
                                            FilterChain chain,
                                            Authentication authResult) throws IOException, ServletException {
        boolean isRemember = rememberMe.get();

        JwtUser user = (JwtUser) authResult.getPrincipal();
        user.setIsRemember(isRemember);
        String jwtToken = JwtTokenUtil.generateToken(user);
        response.setHeader("token", JwtTokenUtil.TOKEN_PREFIX + jwtToken);
        String result = JSON.toJSONString(JsonResult.success("登录成功"));
        response.getWriter().write(result);
    }

    /**
     * 验证失败时候调用的方法
     */
    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
        String result = JSON.toJSONString(JsonResult.fail(failed.getMessage()));
        response.getWriter().write(result);
    }

}
