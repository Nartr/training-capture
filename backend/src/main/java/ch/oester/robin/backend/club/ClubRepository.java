package ch.oester.robin.backend.club;

import org.springframework.data.repository.CrudRepository;

public interface ClubRepository extends CrudRepository<Club, Long> {

  Club findByName(String name);

  long count();
}
