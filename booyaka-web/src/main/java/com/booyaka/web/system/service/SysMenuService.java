package com.booyaka.web.system.service;

import java.util.List;

import com.booyaka.web.system.model.SysMenu;
import com.github.pagehelper.PageInfo;

/**
 * TODO 菜单信息表Service
 *
 * @author booyaka
 * @date 2020-12-01 13:45:31
 */
public interface SysMenuService {
	/**
	 * TODO 添加SysMenu
	 * 
	 * @param sysMenu
	 * @return int
	 * @throws Exception
	 */
	int insertSelective(SysMenu sysMenu) throws Exception;

	/**
	 * TODO 根据主键删除SysMenu
	 * 
	 * @param menuId
	 * @return int
	 * @throws Exception
	 */
	int deleteByPrimaryKey(String menuId) throws Exception;

	/**
	 * TODO 根据对象属性更新SysMenu
	 * 
	 * @param sysMenu
	 * @return int
	 * @throws Exception
	 */
	int updateSelectiveByPrimaryKey(SysMenu sysMenu) throws Exception;

	/**
	 * TODO 根据主键查询SysMenu
	 * 
	 * @param menuId
	 * @return SysMenu
	 */
	SysMenu queryByPrimaryKey(String menuId);

	/**
	 * TODO 根据对象查询SysMenu
	 * 
	 * @param sysMenu
	 * @return List<SysMenu>
	 */
	List<SysMenu> querySelective(SysMenu sysMenu);

	/**
	 * TODO 根据对象查询SysMenu
	 * 
	 * @param sysMenu
	 * @return PageInfo<SysMenu>
	 */
	PageInfo<SysMenu> querySelectiveForPage(SysMenu sysMenu);

	List<SysMenu> queryButtonByUserId(String userId);

	List<SysMenu> queryModelByUserId(String userId);

	List<SysMenu> queryMenuByUserId(String userId, String menuId);

	/**
	 * 查询系统所有按钮
	 * 
	 * @return
	 */
	List<SysMenu> queryAllButton();
}