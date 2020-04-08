package com.project.nairon.controller.naironuser;

import java.util.HashSet;
import java.util.Set;
import java.util.logging.Logger;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.nairon.models.naironuser.LoginDTO;
import com.project.nairon.models.naironuser.NaironUser;
import com.project.nairon.models.naironuser.NaironUserDTO;
import com.project.nairon.models.role.Role;
import com.project.nairon.models.role.RoleName;
import com.project.nairon.repository.naironuser.NaironUserRepository;
import com.project.nairon.repository.role.RoleRepository;
import com.project.nairon.security.jwt.JwtProvider;
import com.project.nairon.security.jwt.JwtResponse;
import com.project.nairon.service.naironuser.NaironUserService;

import io.swagger.v3.oas.annotations.tags.Tag;
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/user")
@Tag(name = "Nairon Users", description = "Nairon User API Service By Rabbi")
public class NaionUserController {

    Logger logger = Logger.getLogger(getClass().getName());
    
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    private NaironUserService naironUserService;
    
    @Autowired
    private NaironUserRepository userRepository;
    
    @Autowired
    RoleRepository roleRepository;
    
    
    @Autowired
    PasswordEncoder encoder;
    
    
    @Autowired
    JwtProvider jwtProvider;


    @GetMapping("/")
    public String test() {
        return "Application is live and running";
    }
    
    
    @PostMapping("/signin")
    public ResponseEntity<?> authenticateuser(@Valid @RequestBody LoginDTO loginDTO){
    	
    	Authentication authentication = authenticationManager.authenticate(
    			new UsernamePasswordAuthenticationToken(
    					loginDTO.getEmail(),
    					loginDTO.getPassword()
    					)
    			);
    	
    	SecurityContextHolder.getContext().setAuthentication(authentication);
    	
    	String jwt = jwtProvider.generateJwtToken(authentication);
    	
    	return ResponseEntity.ok(new JwtResponse(jwt));
    	
    }


    @PostMapping("/signup")
    public ResponseEntity<String> registerUserAccount(@RequestBody NaironUserDTO naironUserDTO){

        logger.info("Registration user account with information: {}"+ naironUserDTO);

        if(userRepository.existsByEmail(naironUserDTO.getEmail())) {
			System.out.println("Error");
         return new ResponseEntity<String>("Fail -> Email is already in use!",
                 HttpStatus.BAD_REQUEST);
         
     }
        NaironUser user = new  NaironUser(naironUserDTO.getEmail(), 
        		 naironUserDTO.getPhoneNumber(),
   			 encoder.encode(naironUserDTO.getPassword()),
   			 naironUserDTO.getFullname(),
   			 naironUserDTO.getBusinessSector(),
   			 naironUserDTO.getBusinessName(),
   			 naironUserDTO.getGender());
   		
   	 Set<String> strRoles = naironUserDTO.getRole();
   	 Set<Role> roles =  new HashSet<>();
   	 
   	 strRoles.forEach(role -> {
            switch(role) {
            case "advertiser":
              Role advertiserRole = roleRepository.findByName(RoleName.ROLE_ADVERTISER)
                    .orElseThrow(() -> new RuntimeException("Fail! -> Cause: User Role not find."));
              roles.add(advertiserRole);
              
              break;
            case "publisher":
                  Role publisherRole = roleRepository.findByName(RoleName.ROLE_PUBLISHER)
                    .orElseThrow(() -> new RuntimeException("Fail! -> Cause: User Role not find."));
                  roles.add(publisherRole);
                  
              break;
            default:
                Role adminRole = roleRepository.findByName(RoleName.ROLE_ADMIN)
                    .orElseThrow(() -> new RuntimeException("Fail! -> Cause: User Role not find."));
                roles.add(adminRole);              
            }
          });

       
   	user.setRoles(roles);
    userRepository.save(user);
 

        return ResponseEntity.ok().body("User registered successfully!");

    }

}
