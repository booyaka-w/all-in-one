package com.booyaka.web.system.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.booyaka.web.system.model.SysUserInfo;

/**
 * TODO 用户信息表Dao
 *
 * @author booyaka
 * @date 2020-12-01 13:45:57
 */
@Mapper
public interface SysUserInfoMapper {
	/**
	 * TODO 添加
	 * 
	 * @param sysUserInfo
	 * @return int
	 * @throws Exception
	 */
	int insertSelective(SysUserInfo sysUserInfo) throws Exception;

	/**
	 * TODO 根据主键删除
	 * 
	 * @param userId
	 * @return int
	 * @throws Exception
	 */
	int deleteByPrimaryKey(String userId) throws Exception;

	/**
	 * TODO 根据对象属性更新
	 * 
	 * @param sysUserInfo
	 * @return int
	 * @throws Exception
	 */
	int updateSelectiveByPrimaryKey(SysUserInfo sysUserInfo) throws Exception;

	/**
	 * TODO 根据主键查询
	 * 
	 * @param userId
	 * @return com.booyaka.web.system.model.SysUserInfo
	 */
	SysUserInfo queryByPrimaryKey(String userId);

	/**
	 * TODO 根据对象查询
	 * 
	 * @param sysUserInfo
	 * @return List<com.booyaka.web.system.model.SysUserInfo>
	 */
	List<SysUserInfo> querySelective(SysUserInfo sysUserInfo);

	/**
	 * TODO 根据名字查询用户
	 * 
	 * @param username
	 * @return SysUserInfo
	 */
	SysUserInfo queryByUserName(String username);
}