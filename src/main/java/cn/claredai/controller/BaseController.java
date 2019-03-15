package cn.claredai.controller;

import cn.claredai.exception.BadRequestException;
import cn.claredai.model.SysUser;
import cn.claredai.service.ISysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author daixiaosong
 * @Date create in 11:57 2019/3/8
 */
public abstract class BaseController {
    @Autowired
    HttpServletRequest request;
    @Autowired
    ISysUserService userService;

    public SysUser getUser(){
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (userDetails == null) {
            throw new BadRequestException(HttpStatus.UNAUTHORIZED, "登录状态过期");
        }
        SysUser user = userService.selectByLoginName(userDetails.getUsername());
        return user;
    }
}
