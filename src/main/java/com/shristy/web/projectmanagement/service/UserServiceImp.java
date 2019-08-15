package com.shristy.web.projectmanagement.service;

import com.shristy.web.projectmanagement.entity.AppUser;
import com.shristy.web.projectmanagement.entity.AppRole;
import com.shristy.web.projectmanagement.entity.AppUserRole;
import com.shristy.web.projectmanagement.repository.AppRoleRepository;
import com.shristy.web.projectmanagement.repository.AppUserRepository;
import com.shristy.web.projectmanagement.repository.impl.AppRoleServiceImpl;
import com.shristy.web.projectmanagement.repository.impl.AppUserRoleServiceImpl;
import com.shristy.web.projectmanagement.repository.impl.AppUserServiceImpl;
import java.util.Arrays;
import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImp implements UserService {

    @Autowired
    BCryptPasswordEncoder encoder;
    @Autowired
    AppRoleRepository roleRepository;
    @Autowired
    AppUserRepository userRepository;
    @Autowired
    private AppRoleServiceImpl arr;

    @Autowired
    private AppUserRoleServiceImpl ur;
    @Autowired
    private AppUserServiceImpl aur;

    @Override
    public boolean isUserAlreadyPresent(AppUser user) {
        Boolean result = false;
        for (AppUser users : userRepository.findAll()) {
            if (users == user) {
                result = true;
            }

        }
        return result;
    }

    @Override
    public void saveUser(AppUser user) {
        user.setEncrytedPassword(encoder.encode(user.getEncrytedPassword()));
        //user.setStatus("VERIFIED");
        AppUserRole urole = new AppUserRole();
        user.setUserId(user.getUserId());

        user.setEnabled(true);
       // aur.save(user);

		userRepository.save(user);}
    }

