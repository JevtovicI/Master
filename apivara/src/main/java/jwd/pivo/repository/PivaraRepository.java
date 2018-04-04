package jwd.pivo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import jwd.pivo.model.Pivara;


@Repository
public interface PivaraRepository extends JpaRepository<Pivara, Long> {
}
