package com.booyaka.web.system.dao;

import com.booyaka.web.system.model.SysRole;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;

/**
 * TODO 角色信息表Dao
 *
 * @author booyaka
 * @date 2020-12-01 13:45:38
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
     * TODO 根据对象属性更新
     *  	
     * @param sysRole
     * @return int
     * @throws Exception
     */
    int updateSelectiveByPrimaryKey(SysRole sysRole) throws Exception;

    /**
     * TODO 根据主键查询
     *  	
     * @param roleId
     * @return com.booyaka.web.system.model.SysRole
     */
    SysRole queryByPrimaryKey(String roleId);

    /**
     * TODO 根据对象查询
     *  	
     * @param sysRole
     * @return List<com.booyaka.web.system.model.SysRole>
     */
    List<SysRole> querySelective(SysRole sysRole);
}