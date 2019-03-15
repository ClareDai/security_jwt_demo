package cn.claredai.model;

import lombok.Data;

/**
 * 角色和菜单关联表 实体类
 *
 * @author claredai
 * @date 2016/03/06
 */
@Data
public class SysRoleMenu {
    private Integer roleId;

    private Integer menuId;

}