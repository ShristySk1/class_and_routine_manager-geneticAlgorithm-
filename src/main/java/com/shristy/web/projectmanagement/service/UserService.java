package com.shristy.web.projectmanagement.service;

import com.shristy.web.projectmanagement.entity.AppUser;

public interface UserService {

	public void saveUser(AppUser user);
	
	public boolean isUserAlreadyPresent(AppUser user);
}
