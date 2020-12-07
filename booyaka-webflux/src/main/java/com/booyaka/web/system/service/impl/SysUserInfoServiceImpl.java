package com.booyaka.web.system.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.booyaka.web.system.dao.SysUserInfoMapper;
import com.booyaka.web.system.model.SysUserInfo;
import com.booyaka.web.system.service.SysUserInfoService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

/**
 * TODO 用户信息表ServiceImpl
 *
 * @author booyaka
 * @date 2020-12-01 13:45:57
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class SysUserInfoServiceImpl implements SysUserInfoService {
	@Autowired
	private SysUserInfoMapper sysUserInfoMapper;

	@Override
	public int insertSelective(SysUserInfo sysUserInfo) throws Exception {
		return sysUserInfoMapper.insertSelective(sysUserInfo);
	}

	@Override
	public int deleteByPrimaryKey(String userId) throws Exception {
		return sysUserInfoMapper.deleteByPrimaryKey(userId);
	}

	@Override
	public int updateSelectiveByPrimaryKey(SysUserInfo sysUserInfo) throws Exception {
		return sysUserInfoMapper.updateSelectiveByPrimaryKey(sysUserInfo);
	}

	@Override
	public SysUserInfo queryByPrimaryKey(String userId) {
		return sysUserInfoMapper.queryByPrimaryKey(userId);
	}

	@Override
	public List<SysUserInfo> querySelective(SysUserInfo sysUserInfo) {
		return sysUserInfoMapper.querySelective(sysUserInfo);
	}

	@Override
	public PageInfo<SysUserInfo> querySelectiveForPage(SysUserInfo sysUserInfo) {
		PageHelper.startPage(sysUserInfo.getPageNum(), sysUserInfo.getPageSize(), sysUserInfo.getOrderBy());
		List<SysUserInfo> list = sysUserInfoMapper.querySelective(sysUserInfo);
		return new PageInfo<SysUserInfo>(list);
	}

	@Override
	public SysUserInfo queryByUserName(String username) {
		return sysUserInfoMapper.queryByUserName(username);
	}
}