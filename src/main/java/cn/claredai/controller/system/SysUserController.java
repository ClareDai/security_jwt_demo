package cn.claredai.controller.system;

import cn.claredai.model.SysUser;
import cn.claredai.service.ISysUserService;
import cn.claredai.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author daixiaosong
 * @Date create in 16:02 2019/3/7
 */
@RestController
@RequestMapping("/system")
public class SysUserController {
    @Autowired
    ISysUserService sysUserService;

    @PreAuthorize("hasAnyAuthority('ADMIN','SYSTEM_USER_SELECT')")
    @GetMapping("/users")
    public JsonResult userList(){
        List<SysUser> userList = sysUserService.selectList();
        return JsonResult.success(userList);
    }
}
