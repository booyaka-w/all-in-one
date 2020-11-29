package com.booyaka.web.system.service;

import com.booyaka.web.system.model.UserInfo;
import com.github.pagehelper.PageInfo;
import java.util.List;

/**
 * TODO 账户信息Service
 *
 * @author booyaka
 * @date 2020-11-28 22:05:02
 */
public interface UserInfoService {
    /**
     * TODO 添加UserInfo
     *  	
     * @param userInfo
     * @return int
     * @throws Exception
     */
    int insertSelective(UserInfo userInfo) throws Exception;

    /**
     * TODO 根据主键删除UserInfo
     *  	
     * @param userId
     * @return int
     * @throws Exception
     */
    int deleteByPrimaryKey(String userId) throws Exception;

    /**
     * TODO 根据对象属性更新UserInfo
     *  	
     * @param userInfo
     * @return int
     * @throws Exception
     */
    int updateSelectiveByPrimaryKey(UserInfo userInfo) throws Exception;

    /**
     * TODO 根据主键查询UserInfo
     *  	
     * @param userId
     * @return UserInfo
     */
    UserInfo queryByPrimaryKey(String userId);

    /**
     * TODO 根据对象查询UserInfo
     *  	
     * @param userInfo
     * @return List<UserInfo>
     */
    List<UserInfo> querySelective(UserInfo userInfo);

    /**
     * TODO 根据对象查询UserInfo
     *  	
     * @param userInfo
     * @return PageInfo<UserInfo>
     */
    PageInfo<UserInfo> querySelectiveForPage(UserInfo userInfo);
}