package com.booyaka.web.system.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.booyaka.web.system.dao.SysUserRoleMapper;
import com.booyaka.web.system.model.SysUserRole;
import com.booyaka.web.system.service.SysUserRoleService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

/**
 * TODO 用户角色关系表ServiceImpl
 *
 * @author booyaka
 * @date 2020-11-28 22:01:21
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class SysUserRoleServiceImpl implements SysUserRoleService {
	@Autowired
	private SysUserRoleMapper sysUserRoleMapper;

	@Override
	public int insertSelective(SysUserRole sysUserRole) throws Exception {
		return sysUserRoleMapper.insertSelective(sysUserRole);
	}

	@Override
	public int deleteByPrimaryKey(String userRoleId) throws Exception {
		return sysUserRoleMapper.deleteByPrimaryKey(userRoleId);
	}

	@Override
	public int updateSelectiveByPrimaryKey(SysUserRole sysUserRole) throws Exception {
		return sysUserRoleMapper.updateSelectiveByPrimaryKey(sysUserRole);
	}

	@Override
	public SysUserRole queryByPrimaryKey(String userRoleId) {
		return sysUserRoleMapper.queryByPrimaryKey(userRoleId);
	}

	@Override
	public List<SysUserRole> querySelective(SysUserRole sysUserRole) {
		return sysUserRoleMapper.querySelective(sysUserRole);
	}

	@Override
	public PageInfo<SysUserRole> querySelectiveForPage(SysUserRole sysUserRole) {
		PageHelper.startPage(sysUserRole.getPageNum(), sysUserRole.getPageSize(), sysUserRole.getOrderBy());
		List<SysUserRole> list = sysUserRoleMapper.querySelective(sysUserRole);
		return new PageInfo<SysUserRole>(list);
	}
}