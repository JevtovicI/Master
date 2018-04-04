package jwd.apoteka.service;

import java.util.List;

import org.springframework.data.domain.Page;

import jwd.apoteka.model.User;

public interface UserService {

	User findOne(Long id);
	Page<User> findAll(int page);
	User save(User user);
	
	//za korisnika se u ovom primeru (bez
	//specijalnog razloga) koristi
	//varijanta brisanja koja NE vraća entitet
	void delete(Long id); 
	List<User> pretragaPoImenuIPrezimenu(String ime);
	
}
