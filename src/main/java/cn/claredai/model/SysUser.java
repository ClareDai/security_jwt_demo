package cn.claredai.model;

import lombok.Data;

/**
 * 用户表 实体类
 *
 * @author claredai
 * @date 2016/03/06
 */
@Data
public class SysUser {
    private Integer userId;

    private String userName;

    private String loginName;

    private String password;

}