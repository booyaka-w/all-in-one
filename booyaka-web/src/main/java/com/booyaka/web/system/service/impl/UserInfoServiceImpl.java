package com.booyaka.web.system.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.booyaka.web.system.dao.UserInfoMapper;
import com.booyaka.web.system.model.UserInfo;
import com.booyaka.web.system.service.UserInfoService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

/**
 * TODO 账户信息ServiceImpl
 *
 * @author booyaka
 * @date 2020-11-28 22:05:02
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class UserInfoServiceImpl implements UserInfoService {
	@Autowired
	private UserInfoMapper userInfoMapper;

	@Override
	public int insertSelective(UserInfo userInfo) throws Exception {
		return userInfoMapper.insertSelective(userInfo);
	}

	@Override
	public int deleteByPrimaryKey(String userId) throws Exception {
		return userInfoMapper.deleteByPrimaryKey(userId);
	}

	@Override
	public int updateSelectiveByPrimaryKey(UserInfo userInfo) throws Exception {
		return userInfoMapper.updateSelectiveByPrimaryKey(userInfo);
	}

	@Override
	public UserInfo queryByPrimaryKey(String userId) {
		return userInfoMapper.queryByPrimaryKey(userId);
	}

	@Override
	public List<UserInfo> querySelective(UserInfo userInfo) {
		return userInfoMapper.querySelective(userInfo);
	}

	@Override
	public PageInfo<UserInfo> querySelectiveForPage(UserInfo userInfo) {
		PageHelper.startPage(userInfo.getPageNum(), userInfo.getPageSize(), userInfo.getOrderBy());
		List<UserInfo> list = userInfoMapper.querySelective(userInfo);
		return new PageInfo<UserInfo>(list);
	}
}