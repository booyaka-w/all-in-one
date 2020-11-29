package com.booyaka.web.system.service;

import com.booyaka.web.system.model.UserInfo;

public interface SystemService {

	UserInfo queryByUserName(String userName);

}
