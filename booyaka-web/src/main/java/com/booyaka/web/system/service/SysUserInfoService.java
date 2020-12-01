package com.booyaka.web.system.service;

import java.util.List;

import com.booyaka.web.system.model.SysUserInfo;
import com.github.pagehelper.PageInfo;

/**
 * TODO 用户信息表Service
 *
 * @author booyaka
 * @date 2020-12-01 13:45:57
 */
public interface SysUserInfoService {
	/**
	 * TODO 添加SysUserInfo
	 * 
	 * @param sysUserInfo
	 * @return int
	 * @throws Exception
	 */
	int insertSelective(SysUserInfo sysUserInfo) throws Exception;

	/**
	 * TODO 根据主键删除SysUserInfo
	 * 
	 * @param userId
	 * @return int
	 * @throws Exception
	 */
	int deleteByPrimaryKey(String userId) throws Exception;

	/**
	 * TODO 根据对象属性更新SysUserInfo
	 * 
	 * @param sysUserInfo
	 * @return int
	 * @throws Exception
	 */
	int updateSelectiveByPrimaryKey(SysUserInfo sysUserInfo) throws Exception;

	/**
	 * TODO 根据主键查询SysUserInfo
	 * 
	 * @param userId
	 * @return SysUserInfo
	 */
	SysUserInfo queryByPrimaryKey(String userId);

	/**
	 * TODO 根据对象查询SysUserInfo
	 * 
	 * @param sysUserInfo
	 * @return List<SysUserInfo>
	 */
	List<SysUserInfo> querySelective(SysUserInfo sysUserInfo);

	/**
	 * TODO 根据对象查询SysUserInfo
	 * 
	 * @param sysUserInfo
	 * @return PageInfo<SysUserInfo>
	 */
	PageInfo<SysUserInfo> querySelectiveForPage(SysUserInfo sysUserInfo);

	/**
	 * TODO 根据名字查询用户
	 * 
	 * @param username
	 * @return SysUserInfo
	 */
	SysUserInfo queryByUserName(String username);
}