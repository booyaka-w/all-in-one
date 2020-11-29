package com.booyaka.web.system.dao;

import com.booyaka.web.system.model.SysMenu;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;

/**
 * TODO 菜单信息表Dao
 *
 * @author booyaka
 * @date 2020-11-28 22:00:28
 */
@Mapper
public interface SysMenuMapper {
    /**
     * TODO 添加
     *  	
     * @param sysMenu
     * @return int
     * @throws Exception
     */
    int insertSelective(SysMenu sysMenu) throws Exception;

    /**
     * TODO 根据主键删除
     *  	
     * @param menuId
     * @return int
     * @throws Exception
     */
    int deleteByPrimaryKey(String menuId) throws Exception;

    /**
     */
    int updateSelectiveByPrimaryKey(SysMenu sysMenu) throws Exception;

    /**
     */
    SysMenu queryByPrimaryKey(String menuId);

    /**
     */
    List<SysMenu> querySelective(SysMenu sysMenu);
}