package cn.claredai.controller.system;

import cn.claredai.controller.BaseController;
import cn.claredai.model.JwtUser;
import cn.claredai.model.SysUser;
import cn.claredai.service.ISysUserService;
import cn.claredai.util.JsonResult;
import cn.claredai.util.JwtTokenUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

/**
 * @Author daixiaosong
 * @Date create in 16:07 2019/3/7
 */
@Slf4j
@RestController
public class LoginController extends BaseController {
    @Autowired
    ISysUserService userService;
    @Autowired
    @Qualifier("jwtUserDetailsServiceImpl")
    UserDetailsService userDetailsService;

    @PostMapping("/login")
    public JsonResult login(@RequestParam("username") String username,
                            @RequestParam("password") String password,
                            @RequestParam("isRememberMe") Boolean isRememberMe,
                            HttpServletResponse response){
        JwtUser jwtUser = (JwtUser) userDetailsService.loadUserByUsername(username);
        jwtUser.setIsRemember(isRememberMe);
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        if(!passwordEncoder.matches(password, jwtUser.getPassword())){
            throw new AccountExpiredException("密码错误");
        }
        // 生成令牌
        final String token = JwtTokenUtil.generateToken(jwtUser);
        response.setHeader("token", JwtTokenUtil.TOKEN_PREFIX + token);
        return JsonResult.success("登陆成功");
    }

    @GetMapping("/getUserInfo")
    public JsonResult getUserInfo() {
        SysUser user = getUser();
        return JsonResult.success(user);
    }

    @GetMapping("/logout")
    public JsonResult logout() {
        SecurityContextHolder.clearContext();
        log.info("登出操作");
        return JsonResult.success("注销成功");
    }


    public static void main(String[] args) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        System.out.println(passwordEncoder.encode("123456"));
    }
}
