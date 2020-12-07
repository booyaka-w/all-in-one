package com.booyaka.web.system.dao;

import com.booyaka.web.system.model.SysRoleMenu;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;

/**
 * TODO 角色菜单关系表Dao
 *
 * @author booyaka
 * @date 2020-12-01 13:45:45
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
     * TODO 根据对象属性更新
     *  	
     * @param sysRoleMenu
     * @return int
     * @throws Exception
     */
    int updateSelectiveByPrimaryKey(SysRoleMenu sysRoleMenu) throws Exception;

    /**
     * TODO 根据主键查询
     *  	
     * @param roleMenuId
     * @return com.booyaka.web.system.model.SysRoleMenu
     */
    SysRoleMenu queryByPrimaryKey(String roleMenuId);

    /**
     * TODO 根据对象查询
     *  	
     * @param sysRoleMenu
     * @return List<com.booyaka.web.system.model.SysRoleMenu>
     */
    List<SysRoleMenu> querySelective(SysRoleMenu sysRoleMenu);
}