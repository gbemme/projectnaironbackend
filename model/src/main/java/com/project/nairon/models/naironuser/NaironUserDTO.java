package com.project.nairon.models.naironuser;

import java.util.Set;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;


//web validation constrains added
public class NaironUserDTO {

        @Email
        @NotNull
        private String email;
        
        //@NotNull
       // private String username;


        @NotNull
        private String phoneNumber;

        @NotNull
        private String password;

        @NotNull
        private String fullname;

        private String businessSector;

        private String businessName;

        private String gender;

       private Set<String> role;
       
        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }
        

//        public String getUsername() {
//			return username;
//		}
//
//		public void setUsername(String username) {
//			this.username = username;
//		}

		public String getPhoneNumber() {
            return phoneNumber;
        }

        public void setPhoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getFullname() {
            return fullname;
        }

        public void setFullname(String fullname) {
            this.fullname = fullname;
        }

        public String getBusinessSector() {
            return businessSector;
        }

        public void setBusinessSector(String businessSector) {
            this.businessSector = businessSector;
        }

        public String getBusinessName() {
            return businessName;
        }

        public void setBusinessName(String businessName) {
            this.businessName = businessName;
        }

        public String getGender() {
            return gender;
        }

        public void setGender(String gender) {
            this.gender = gender;
        }
        

    public Set<String> getRole() {
			return role;
		}

		public void setRole(Set<String> role) {
			this.role = role;
		}

		
	
}
