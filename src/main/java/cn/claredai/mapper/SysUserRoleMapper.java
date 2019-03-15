package cn.claredai.mapper;

import cn.claredai.model.SysUserRole;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 用户和角色表 数据层
 *
 * @author claredai
 * @date 2016/03/06
 */
@Repository
public interface SysUserRoleMapper {
    List<SysUserRole> selectByUserId(Integer userId);

    int insert(SysUserRole record);

    int deleteById(Integer userId);

}