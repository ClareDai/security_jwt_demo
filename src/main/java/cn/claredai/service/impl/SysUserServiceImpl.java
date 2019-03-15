package cn.claredai.service.impl;

import cn.claredai.mapper.SysUserMapper;
import cn.claredai.model.SysUser;
import cn.claredai.service.ISysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author daixiaosong
 * @Date create in 15:58 2019/3/7
 */
@Service
public class SysUserServiceImpl implements ISysUserService {

    @Autowired
    SysUserMapper userMapper;

    @Override
    public List<SysUser> selectList() {
        return userMapper.selectList();
    }

    @Override
    public SysUser selectById(Integer userId) {
        return userMapper.selectById(userId);
    }

    @Override
    public SysUser selectByLoginName(String loginName) {
        return userMapper.selectByLoginName(loginName);
    }

    @Override
    public int insert(SysUser record) {
        return userMapper.insert(record);
    }

    @Override
    public int update(SysUser record) {
        return userMapper.update(record);
    }

    @Override
    public int deleteById(Integer userId) {
        return userMapper.deleteById(userId);
    }
}
