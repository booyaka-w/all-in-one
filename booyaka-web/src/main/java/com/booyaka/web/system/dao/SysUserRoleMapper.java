package com.booyaka.web.system.dao;

import com.booyaka.web.system.model.SysUserRole;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;

/**
 * TODO 用户角色关系表Dao
 *
 * @author booyaka
 * @date 2020-11-28 22:01:21
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
     */
    int updateSelectiveByPrimaryKey(SysUserRole sysUserRole) throws Exception;

    /**
     */
    SysUserRole queryByPrimaryKey(String userRoleId);

    /**
     */
    List<SysUserRole> querySelective(SysUserRole sysUserRole);
}