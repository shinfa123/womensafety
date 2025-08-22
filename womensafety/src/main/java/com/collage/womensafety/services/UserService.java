package com.collage.womensafety.services;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.collage.womensafety.dao.UserDao;
import com.collage.womensafety.domain.MyUser;


@Service
public class UserService implements UserDetailsService{
	@Autowired
	private UserDao userDao;
	User user=null;
    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
    	Optional<MyUser> optionalLoginUser = userDao.findByUserName(userName);
    	if (optionalLoginUser.isPresent()) {
    		MyUser firstLoginUser = optionalLoginUser.get();
    		if(firstLoginUser.isAdmin) {
    	    user=new User(firstLoginUser.getUserName(),firstLoginUser.getPassword(),
                    new ArrayList<>());
    		}
    	}
    	return user;
    }
    
	public MyUser saveUser(MyUser user) {
		return userDao.save(user);
	}
	
	public ArrayList<MyUser> getAllUsers() {
		return (ArrayList<MyUser>) userDao.findAll();
	}
}
