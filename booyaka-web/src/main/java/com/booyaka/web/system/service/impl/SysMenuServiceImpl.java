package com.booyaka.web.system.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.booyaka.web.system.dao.SysMenuMapper;
import com.booyaka.web.system.model.SysMenu;
import com.booyaka.web.system.service.SysMenuService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

/**
 * TODO 菜单信息表ServiceImpl
 *
 * @author booyaka
 * @date 2020-12-01 13:45:31
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class SysMenuServiceImpl implements SysMenuService {
	@Autowired
	private SysMenuMapper sysMenuMapper;

	@Override
	public int insertSelective(SysMenu sysMenu) throws Exception {
		return sysMenuMapper.insertSelective(sysMenu);
	}

	@Override
	public int deleteByPrimaryKey(String menuId) throws Exception {
		return sysMenuMapper.deleteByPrimaryKey(menuId);
	}

	@Override
	public int updateSelectiveByPrimaryKey(SysMenu sysMenu) throws Exception {
		return sysMenuMapper.updateSelectiveByPrimaryKey(sysMenu);
	}

	@Override
	public SysMenu queryByPrimaryKey(String menuId) {
		return sysMenuMapper.queryByPrimaryKey(menuId);
	}

	@Override
	public List<SysMenu> querySelective(SysMenu sysMenu) {
		return sysMenuMapper.querySelective(sysMenu);
	}

	@Override
	public PageInfo<SysMenu> querySelectiveForPage(SysMenu sysMenu) {
		PageHelper.startPage(sysMenu.getPageNum(), sysMenu.getPageSize(), sysMenu.getOrderBy());
		List<SysMenu> list = sysMenuMapper.querySelective(sysMenu);
		return new PageInfo<SysMenu>(list);
	}

	@Override
	public List<SysMenu> queryButtonByUserId(String userId) {
		return sysMenuMapper.queryButtonByUserId(userId);
	}

	@Override
	public List<SysMenu> queryModelByUserId(String userId) {
		return sysMenuMapper.queryModelByUserId(userId);
	}

	@Override
	public List<SysMenu> queryMenuByUserId(String userId, String menuId) {
		return sysMenuMapper.queryMenuByUserId(userId);
	}
}