package bma.service.impl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bma.model.Role;
import bma.repository.RoleRepository;
import bma.service.RoleService;

@Service("roleService")
@Transactional
public class RoleServiceImpl implements RoleService {

	@Autowired
	RoleRepository roleRepository;

	@Override
	public void save(Role roles) {
		roleRepository.save(roles);
		
	}
	
	
}
