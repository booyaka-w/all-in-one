package com.booyaka.web.system.dao;

import com.booyaka.web.system.model.SysUserRole;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;

/**
 * TODO 用户角色关系表Dao
 *
 * @author booyaka
 * @date 2020-12-01 13:46:06
 */
@Mapper
public interface SysUserRoleMapper {
    /**
     * TODO 添加
     *  	
     * @param sysUserRole
     * @return int
     * @throws Exception
     */
    int insertSelective(SysUserRole sysUserRole) throws Exception;

    /**
     * TODO 根据主键删除
     *  	
     * @param userRoleId
     * @return int
     * @throws Exception
     */
    int deleteByPrimaryKey(String userRoleId) throws Exception;

    /**
     * TODO 根据对象属性更新
     *  	
     * @param sysUserRole
     * @return int
     * @throws Exception
     */
    int updateSelectiveByPrimaryKey(SysUserRole sysUserRole) throws Exception;

    /**
     * TODO 根据主键查询
     *  	
     * @param userRoleId
     * @return com.booyaka.web.system.model.SysUserRole
     */
    SysUserRole queryByPrimaryKey(String userRoleId);

    /**
     * TODO 根据对象查询
     *  	
     * @param sysUserRole
     * @return List<com.booyaka.web.system.model.SysUserRole>
     */
    List<SysUserRole> querySelective(SysUserRole sysUserRole);
}