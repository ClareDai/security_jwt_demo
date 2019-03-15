package cn.claredai.mapper;

import cn.claredai.model.SysMenu;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 菜单表 数据层
 *
 * @author claredai
 * @date 2016/03/06
 */
@Repository
public interface SysMenuMapper {
    SysMenu selectById(Integer menuId);

    int insert(SysMenu record);

    int update(SysMenu record);

    int deleteById(Integer menuId);

    List<String> selectMenuByUserId(Integer userId);
}