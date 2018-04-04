package jwd.apoteka.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import jwd.apoteka.model.User;
import jwd.apoteka.repository.UserRepository;
import jwd.apoteka.service.UserService;

@Service
public class JpaUserService implements UserService {
	@Autowired
	private UserRepository userRepository;

	@Override
	public User findOne(Long id) {
		return userRepository.findOne(id);
	}

	@Override
	public Page<User> findAll(int page) {
		
		return userRepository.findAll(new PageRequest(page, 10));
	}

	@Override
	public User save(User user) {
		return userRepository.save(user);
	}

	@Override
	public void delete(Long id) {
		userRepository.delete(id);
	}

	@Override
	public List<User> pretragaPoImenuIPrezimenu(String ime) {
		if(ime != null){
			ime = "%" + ime + "%";
		}
		return userRepository.pretragaPoImenuIPrezimenu(ime);
	}

}
