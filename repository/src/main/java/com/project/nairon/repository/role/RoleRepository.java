/**
 * 
 */
package com.project.nairon.repository.role;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.nairon.models.role.Role;
import com.project.nairon.models.role.RoleName;

/**
 * @author gbemisola
 *
 */
@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
	
	Optional<Role> findByName(RoleName roleName);

}
