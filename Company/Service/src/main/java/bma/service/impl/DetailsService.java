package bma.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import bma.model.Employee;
import bma.repository.UserRepository;

@Component
public class DetailsService implements UserDetailsService{

	@Autowired
	UserRepository users;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Employee employee = users.findByUsername(username);

        if (employee == null) {
            throw new UsernameNotFoundException(username + " was not found");
        }
        System.out.println(employee.getPassword());
       // System.out.println(employee.getRoles().size());
        return new org.springframework.security.core.userdetails.User(
        		employee.getUsername(),
        		employee.getPassword(),
                AuthorityUtils.createAuthorityList(employee.getRoles().toArray(new String[employee.getRoles().size()]))
        );                  
        
	}
}


//employee.getRoles().toArray(new String[employee.getRoles().size()])));