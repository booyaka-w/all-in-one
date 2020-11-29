package com.booyaka.web.system.service;

import com.booyaka.web.system.model.SysRoleMenu;
import com.github.pagehelper.PageInfo;
import java.util.List;

/**
 * TODO 角色菜单关系表Service
 *
 * @author booyaka
 * @date 2020-11-28 22:01:03
 */
public interface SysRoleMenuService {
    /**
     * TODO 添加SysRoleMenu
     *  	
     * @param sysRoleMenu
     * @return int
     * @throws Exception
     */
    int insertSelective(SysRoleMenu sysRoleMenu) throws Exception;

    /**
     * TODO 根据主键删除SysRoleMenu
     *  	
     * @param roleMenuId
     * @return int
     * @throws Exception
     */
    int deleteByPrimaryKey(String roleMenuId) throws Exception;

    /**
     * TODO 根据对象属性更新SysRoleMenu
     *  	
     * @param sysRoleMenu
     * @return int
     * @throws Exception
     */
    int updateSelectiveByPrimaryKey(SysRoleMenu sysRoleMenu) throws Exception;

    /**
     * TODO 根据主键查询SysRoleMenu
     *  	
     * @param roleMenuId
     * @return SysRoleMenu
     */
    SysRoleMenu queryByPrimaryKey(String roleMenuId);

    /**
     * TODO 根据对象查询SysRoleMenu
     *  	
     * @param sysRoleMenu
     * @return List<SysRoleMenu>
     */
    List<SysRoleMenu> querySelective(SysRoleMenu sysRoleMenu);

    /**
     * TODO 根据对象查询SysRoleMenu
     *  	
     * @param sysRoleMenu
     * @return PageInfo<SysRoleMenu>
     */
    PageInfo<SysRoleMenu> querySelectiveForPage(SysRoleMenu sysRoleMenu);
}