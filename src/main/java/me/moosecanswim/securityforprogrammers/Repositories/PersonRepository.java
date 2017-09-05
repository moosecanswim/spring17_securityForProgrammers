package me.moosecanswim.securityforprogrammers.Repositories;

import me.moosecanswim.securityforprogrammers.Model.Person;
import org.springframework.data.repository.CrudRepository;

public interface PersonRepository extends CrudRepository<Person,Long> {
    Person findByUsername(String username);
}
