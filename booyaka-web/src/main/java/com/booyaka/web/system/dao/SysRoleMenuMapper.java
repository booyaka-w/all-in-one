package com.booyaka.web.system.dao;

import com.booyaka.web.system.model.SysRoleMenu;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;

/**
 * TODO 角色菜单关系表Dao
 *
 * @author booyaka
 * @date 2020-11-28 22:01:03
 */
@Mapper
public interface SysRoleMenuMapper {
    /**
     * TODO 添加
     *  	
     * @param sysRoleMenu
     * @return int
     * @throws Exception
     */
    int insertSelective(SysRoleMenu sysRoleMenu) throws Exception;

    /**
     * TODO 根据主键删除
     *  	
     * @param roleMenuId
     * @return int
     * @throws Exception
     */
    int deleteByPrimaryKey(String roleMenuId) throws Exception;

    /**
     */
    int updateSelectiveByPrimaryKey(SysRoleMenu sysRoleMenu) throws Exception;

    /**
     */
    SysRoleMenu queryByPrimaryKey(String roleMenuId);

    /**
     */
    List<SysRoleMenu> querySelective(SysRoleMenu sysRoleMenu);
}