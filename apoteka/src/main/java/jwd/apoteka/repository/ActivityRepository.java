package jwd.apoteka.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import jwd.apoteka.model.Activity;

@Repository
public interface ActivityRepository 
	extends JpaRepository<Activity, Long> {

//	@Query("select a from Activity a where a.name = :name")
//	List<Activity> findByName(@Param("name") String name);

	List<Activity> findByName(String name);
	
	List<Activity> findByNameLike(String name);
	
}
