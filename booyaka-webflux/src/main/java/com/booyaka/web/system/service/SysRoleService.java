package com.booyaka.web.system.service;

import com.booyaka.web.system.model.SysRole;
import com.github.pagehelper.PageInfo;
import java.util.List;

/**
 * TODO 角色信息表Service
 *
 * @author booyaka
 * @date 2020-12-01 13:45:38
 */
public interface SysRoleService {
    /**
     * TODO 添加SysRole
     *  	
     * @param sysRole
     * @return int
     * @throws Exception
     */
    int insertSelective(SysRole sysRole) throws Exception;

    /**
     * TODO 根据主键删除SysRole
     *  	
     * @param roleId
     * @return int
     * @throws Exception
     */
    int deleteByPrimaryKey(String roleId) throws Exception;

    /**
     * TODO 根据对象属性更新SysRole
     *  	
     * @param sysRole
     * @return int
     * @throws Exception
     */
    int updateSelectiveByPrimaryKey(SysRole sysRole) throws Exception;

    /**
     * TODO 根据主键查询SysRole
     *  	
     * @param roleId
     * @return SysRole
     */
    SysRole queryByPrimaryKey(String roleId);

    /**
     * TODO 根据对象查询SysRole
     *  	
     * @param sysRole
     * @return List<SysRole>
     */
    List<SysRole> querySelective(SysRole sysRole);

    /**
     * TODO 根据对象查询SysRole
     *  	
     * @param sysRole
     * @return PageInfo<SysRole>
     */
    PageInfo<SysRole> querySelectiveForPage(SysRole sysRole);
}