package cn.claredai.service;

import cn.claredai.model.SysUser;

import java.util.List;

/**
 * @Author daixiaosong
 * @Date create in 15:56 2019/3/7
 */
public interface ISysUserService {
    List<SysUser> selectList();

    SysUser selectById(Integer userId);

    SysUser selectByLoginName(String loginName);

    int insert(SysUser record);

    int update(SysUser record);

    int deleteById(Integer userId);
}
