package cn.claredai.model;

import lombok.Data;

/**
 * 角色表 实体类
 *
 * @author claredai
 * @date 2016/03/06
 */
@Data
public class SysRole {
    private Integer roleId;

    private String roleName;

    private String roleKey;

}