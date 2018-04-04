package jwd.apoteka.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import jwd.apoteka.model.User;

@Repository
public interface UserRepository 
	extends PagingAndSortingRepository<User, Long> {

	@Query("SELECT u FROM User u WHERE "
			+ "(:name IS NULL or u.firstName like :name ) OR "
			+ "(:name IS NULL OR u.lastName like :name)"
			)
	List<User> pretragaPoImenuIPrezimenu(@Param("name") String ime);
}
