package ch.oester.robin.backend.group;

import org.springframework.data.repository.CrudRepository;

public interface GroupRepository extends CrudRepository<Group, Long> {

  Group findByName(String name);

  long count();
}
