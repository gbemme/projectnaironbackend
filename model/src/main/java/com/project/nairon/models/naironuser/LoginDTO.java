/**
 * 
 */
package com.project.nairon.models.naironuser;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

/**
 * @author gbemisola
 *
 */
public class LoginDTO {
	

	@NotEmpty
	private String password;
	
	@NotEmpty
	@Email
	private String email;




	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	
	

}
