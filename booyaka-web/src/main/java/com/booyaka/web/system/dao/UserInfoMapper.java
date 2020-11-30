package com.booyaka.web.system.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.booyaka.web.system.model.UserInfo;

/**
 * TODO 账户信息Dao
 *
 * @author booyaka
 * @date 2020-11-28 22:05:02
 */
@Mapper
public interface UserInfoMapper {
	/**
	 * TODO 添加
	 * 
	 * @param userInfo
	 * @return int
	 * @throws Exception
	 */
	int insertSelective(UserInfo userInfo) throws Exception;

	/**
	 * TODO 根据主键删除
	 * 
	 * @param userId
	 * @return int
	 * @throws Exception
	 */
	int deleteByPrimaryKey(String userId) throws Exception;

	/**
	 */
	int updateSelectiveByPrimaryKey(UserInfo userInfo) throws Exception;

	/**
	 */
	UserInfo queryByPrimaryKey(String userId);

	/**
	 */
	List<UserInfo> querySelective(UserInfo userInfo);

	UserInfo queryByUserName(String userName);
}