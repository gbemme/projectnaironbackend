/**
 * 
 */
package com.project.nairon.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.nairon.models.naironuser.NaironUser;
import com.project.nairon.repository.naironuser.NaironUserRepository;

/**
 * @author gbemisola
 *
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	
	@Autowired
	NaironUserRepository userRepository;

	@Override
	@Transactional
	public UserDetails loadUserByUsername(String email) 
			throws UsernameNotFoundException {
		NaironUser user = userRepository.findByEmail(email).orElseThrow(() ->
		new UsernameNotFoundException("User Not Found with -> username or email : " + email));
		
		return UserPrinciple.build(user);
	}

}
