package cn.claredai.mapper;

import cn.claredai.model.SysUser;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 用户表 数据层
 *
 * @author claredai
 * @date 2016/03/06
 */
@Repository
public interface SysUserMapper {
    List<SysUser> selectList();

    SysUser selectById(Integer userId);

    SysUser selectByLoginName(String loginName);

    int insert(SysUser record);

    int update(SysUser record);

    int deleteById(Integer userId);
}