package com.booyaka.web.system.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.booyaka.web.system.dao.SysRoleMenuMapper;
import com.booyaka.web.system.model.SysRoleMenu;
import com.booyaka.web.system.service.SysRoleMenuService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

/**
 * TODO 角色菜单关系表ServiceImpl
 *
 * @author booyaka
 * @date 2020-11-28 22:01:03
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class SysRoleMenuServiceImpl implements SysRoleMenuService {
	@Autowired
	private SysRoleMenuMapper sysRoleMenuMapper;

	@Override
	public int insertSelective(SysRoleMenu sysRoleMenu) throws Exception {
		return sysRoleMenuMapper.insertSelective(sysRoleMenu);
	}

	@Override
	public int deleteByPrimaryKey(String roleMenuId) throws Exception {
		return sysRoleMenuMapper.deleteByPrimaryKey(roleMenuId);
	}

	@Override
	public int updateSelectiveByPrimaryKey(SysRoleMenu sysRoleMenu) throws Exception {
		return sysRoleMenuMapper.updateSelectiveByPrimaryKey(sysRoleMenu);
	}

	@Override
	public SysRoleMenu queryByPrimaryKey(String roleMenuId) {
		return sysRoleMenuMapper.queryByPrimaryKey(roleMenuId);
	}

	@Override
	public List<SysRoleMenu> querySelective(SysRoleMenu sysRoleMenu) {
		return sysRoleMenuMapper.querySelective(sysRoleMenu);
	}

	@Override
	public PageInfo<SysRoleMenu> querySelectiveForPage(SysRoleMenu sysRoleMenu) {
		PageHelper.startPage(sysRoleMenu.getPageNum(), sysRoleMenu.getPageSize(), sysRoleMenu.getOrderBy());
		List<SysRoleMenu> list = sysRoleMenuMapper.querySelective(sysRoleMenu);
		return new PageInfo<SysRoleMenu>(list);
	}
}