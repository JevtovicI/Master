package bma.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import bma.model.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

}
