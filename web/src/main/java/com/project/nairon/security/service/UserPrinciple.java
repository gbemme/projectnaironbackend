/**
 * 
 */
package com.project.nairon.security.service;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import javax.persistence.Column;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.project.nairon.models.naironuser.NaironUser;

/**
 * @author gbemisola
 *
 */
public class UserPrinciple implements UserDetails {
	
 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

private Long naironUserId;
  
  private String email;
  
  private String username;

  private String phoneNumber;

  @JsonIgnore
  private String password;


  private String fullname;

  
  private String businessSector;

 
  private String businessName;


  private String gender;
  
  
  private Collection<? extends GrantedAuthority> authorities;
  
  
	

	

	



	public UserPrinciple(Long naironUserId, String email,  String phoneNumber, String password,
		String fullname, String businessSector, String businessName, String gender,
		Collection<? extends GrantedAuthority> authorities) {
	super();
	this.naironUserId = naironUserId;
	this.email = email;
	this.phoneNumber = phoneNumber;
	this.password = password;
	this.fullname = fullname;
	this.businessSector = businessSector;
	this.businessName = businessName;
	this.gender = gender;
	this.authorities = authorities;
}



	public static UserPrinciple build(NaironUser user ) {
		List <GrantedAuthority> authorities = user.getRoles()
				.stream().map(role -> new SimpleGrantedAuthority(
						role.getName().name())).collect(Collectors.toList());
		
		
		return new UserPrinciple(
				user.getNaironUserId(),
				user.getEmail(),
				user.getPhoneNumber(),
				user.getPassword(),
				user.getFullname(),
				user.getBusinessSector(),
				user.getBusinessName(),
				user.getGender(),
				authorities
				
						
				);
		
				
	}
	
	
	
	public Long getNaironUserId() {
		return naironUserId;
	}



	public String getEmail() {
		return email;
	}


	

	public String getPhoneNumber() {
		return phoneNumber;
	}



	public String getFullname() {
		return fullname;
	}


	

	public String getBusinessSector() {
		return businessSector;
	}


	

	public String getBusinessName() {
		return businessName;
	}


	
	public String getGender() {
		return gender;
	}
	
	@Override
	public String getPassword() {
	
		return password;
	}

	@Override
	public String getUsername() {
	
		return username;
	}


	
	


	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
	
		return authorities;
	}

	

	@Override
	public boolean isAccountNonExpired() {
		
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
	
		return true;
	}

	@Override
	public boolean isEnabled() {
		
		return true;
	}
	
	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        
        UserPrinciple user = (UserPrinciple) o;
        return Objects.equals(naironUserId, user.naironUserId);
    }

}
