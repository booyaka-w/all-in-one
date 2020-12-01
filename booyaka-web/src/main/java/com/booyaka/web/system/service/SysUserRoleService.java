package com.booyaka.web.system.service;

import com.booyaka.web.system.model.SysUserRole;
import com.github.pagehelper.PageInfo;
import java.util.List;

/**
 * TODO 用户角色关系表Service
 *
 * @author booyaka
 * @date 2020-12-01 13:46:06
 */
public interface SysUserRoleService {
    /**
     * TODO 添加SysUserRole
     *  	
     * @param sysUserRole
     * @return int
     * @throws Exception
     */
    int insertSelective(SysUserRole sysUserRole) throws Exception;

    /**
     * TODO 根据主键删除SysUserRole
     *  	
     * @param userRoleId
     * @return int
     * @throws Exception
     */
    int deleteByPrimaryKey(String userRoleId) throws Exception;

    /**
     * TODO 根据对象属性更新SysUserRole
     *  	
     * @param sysUserRole
     * @return int
     * @throws Exception
     */
    int updateSelectiveByPrimaryKey(SysUserRole sysUserRole) throws Exception;

    /**
     * TODO 根据主键查询SysUserRole
     *  	
     * @param userRoleId
     * @return SysUserRole
     */
    SysUserRole queryByPrimaryKey(String userRoleId);

    /**
     * TODO 根据对象查询SysUserRole
     *  	
     * @param sysUserRole
     * @return List<SysUserRole>
     */
    List<SysUserRole> querySelective(SysUserRole sysUserRole);

    /**
     * TODO 根据对象查询SysUserRole
     *  	
     * @param sysUserRole
     * @return PageInfo<SysUserRole>
     */
    PageInfo<SysUserRole> querySelectiveForPage(SysUserRole sysUserRole);
}