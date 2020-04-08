package com.project.nairon.repository.naironuser;


import com.project.nairon.models.naironuser.NaironUser;
import com.project.nairon.models.naironuser.NaironUserId;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NaironUserRepository extends JpaRepository<NaironUser, Long> {
	 //Optional<NaironUser> findByUsername(String username);
	 //Boolean existsByUsername(String username);
	Optional<NaironUser> findByEmail(String email);
	Boolean existsByEmail(String email);
}
