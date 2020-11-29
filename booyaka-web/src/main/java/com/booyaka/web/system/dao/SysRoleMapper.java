package com.booyaka.web.system.dao;

import com.booyaka.web.system.model.SysRole;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;

/**
 * TODO 角色信息表Dao
 *
 * @author booyaka
 * @date 2020-11-28 22:00:52
 */
@Mapper
public interface SysRoleMapper {
    /**
     * TODO 添加
     *  	
     * @param sysRole
     * @return int
     * @throws Exception
     */
    int insertSelective(SysRole sysRole) throws Exception;

    /**
     * TODO 根据主键删除
     *  	
     * @param roleId
     * @return int
     * @throws Exception
     */
    int deleteByPrimaryKey(String roleId) throws Exception;

    /**
     */
    int updateSelectiveByPrimaryKey(SysRole sysRole) throws Exception;

    /**
     */
    SysRole queryByPrimaryKey(String roleId);

    /**
     */
    List<SysRole> querySelective(SysRole sysRole);
}