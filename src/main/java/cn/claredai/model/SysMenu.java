package cn.claredai.model;

import lombok.Data;

import java.util.Date;

/**
 * 菜单表 实体类
 *
 * @author claredai
 * @date 2016/03/06
 */
@Data
public class SysMenu {
    private Integer menuId;

    private String menuName;

    private Integer parentId;

    private Integer orderNum;

    private String url;

    private String menuType;

    private String visible;

    private String perms;

    private String icon;

}