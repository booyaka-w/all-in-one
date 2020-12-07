package com.booyaka.web.system.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.booyaka.web.system.dao.SysRoleMapper;
import com.booyaka.web.system.model.SysRole;
import com.booyaka.web.system.service.SysRoleService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

/**
 * TODO 角色信息表ServiceImpl
 *
 * @author booyaka
 * @date 2020-12-01 13:45:38
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class SysRoleServiceImpl implements SysRoleService {
	@Autowired
	private SysRoleMapper sysRoleMapper;

	@Override
	public int insertSelective(SysRole sysRole) throws Exception {
		return sysRoleMapper.insertSelective(sysRole);
	}

	@Override
	public int deleteByPrimaryKey(String roleId) throws Exception {
		return sysRoleMapper.deleteByPrimaryKey(roleId);
	}

	@Override
	public int updateSelectiveByPrimaryKey(SysRole sysRole) throws Exception {
		return sysRoleMapper.updateSelectiveByPrimaryKey(sysRole);
	}

	@Override
	public SysRole queryByPrimaryKey(String roleId) {
		return sysRoleMapper.queryByPrimaryKey(roleId);
	}

	@Override
	public List<SysRole> querySelective(SysRole sysRole) {
		return sysRoleMapper.querySelective(sysRole);
	}

	@Override
	public PageInfo<SysRole> querySelectiveForPage(SysRole sysRole) {
		PageHelper.startPage(sysRole.getPageNum(), sysRole.getPageSize(), sysRole.getOrderBy());
		List<SysRole> list = sysRoleMapper.querySelective(sysRole);
		return new PageInfo<SysRole>(list);
	}
}