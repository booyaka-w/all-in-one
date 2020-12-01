package com.booyaka.web.system.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.booyaka.web.system.model.SysMenu;

/**
 * TODO 菜单信息表Dao
 *
 * @author booyaka
 * @date 2020-12-01 13:45:31
 */
@Mapper
public interface SysMenuMapper {
	/**
	 * TODO 添加
	 * 
	 * @param sysMenu
	 * @return int
	 * @throws Exception
	 */
	int insertSelective(SysMenu sysMenu) throws Exception;

	/**
	 * TODO 根据主键删除
	 * 
	 * @param menuId
	 * @return int
	 * @throws Exception
	 */
	int deleteByPrimaryKey(String menuId) throws Exception;

	/**
	 * TODO 根据对象属性更新
	 * 
	 * @param sysMenu
	 * @return int
	 * @throws Exception
	 */
	int updateSelectiveByPrimaryKey(SysMenu sysMenu) throws Exception;

	/**
	 * TODO 根据主键查询
	 * 
	 * @param menuId
	 * @return com.booyaka.web.system.model.SysMenu
	 */
	SysMenu queryByPrimaryKey(String menuId);

	/**
	 * TODO 根据对象查询
	 * 
	 * @param sysMenu
	 * @return List<com.booyaka.web.system.model.SysMenu>
	 */
	List<SysMenu> querySelective(SysMenu sysMenu);

	List<SysMenu> queryModelByUserId(String userId);

	List<SysMenu> queryMenuByUserId(String userId);

	List<SysMenu> queryButtonByUserId(String userId);
}