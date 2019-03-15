package cn.claredai.mapper;

import cn.claredai.model.SysRoleMenu;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 角色和菜单表 数据层
 *
 * @author claredai
 * @date 2016/03/06
 */
@Repository
public interface SysRoleMenuMapper {
    List<SysRoleMenu> selectByRoleId(Integer roleId);

    int insert(SysRoleMenu record);

    int deleteById(Integer roleId);
}