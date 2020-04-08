package com.project.nairon.service.naironuser;

import com.project.nairon.models.naironuser.NaironUser;
import com.project.nairon.models.naironuser.NaironUserDTO;
import com.project.nairon.repository.naironuser.NaironUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NaironUserServiceImpl implements NaironUserService {

    @Autowired
    private NaironUserRepository naironUserRepository;


    @Override
    public NaironUser registerNewUserAccount(NaironUserDTO naironUserDTO) {

        NaironUser naironUser = new NaironUser();
        naironUser.setFullname(naironUserDTO.getFullname());
        naironUser.setPassword(naironUserDTO.getPassword());
        naironUser.setEmail(naironUserDTO.getEmail());
        naironUser.setPhoneNumber(naironUserDTO.getPhoneNumber());
        naironUser.setBusinessName(naironUserDTO.getBusinessName());
        naironUser.setGender(naironUserDTO.getGender());
        

        return naironUserRepository.save(naironUser);
    }
}
