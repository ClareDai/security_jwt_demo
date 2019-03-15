package cn.claredai.model;

import lombok.Data;

/**
 * 用户和角色关联表 实体类
 *
 * @author claredai
 * @date 2016/03/06
 */
@Data
public class SysUserRole {
    private Integer userId;

    private Integer roleId;

}