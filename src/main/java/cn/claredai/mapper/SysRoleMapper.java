package cn.claredai.mapper;

import cn.claredai.model.SysRole;
import org.springframework.stereotype.Repository;

/**
 * 角色表 数据层
 *
 * @author claredai
 * @date 2016/03/06
 */
@Repository
public interface SysRoleMapper {
    SysRole selectById(Integer roleId);

    int insert(SysRole record);

    int update(SysRole record);

    int deleteById(Integer roleId);
}