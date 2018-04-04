package jwd.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import jwd.model.Tim;


@Repository
public interface TimRepository extends JpaRepository<Tim, Long> {
}
