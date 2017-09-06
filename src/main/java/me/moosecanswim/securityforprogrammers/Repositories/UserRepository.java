package me.moosecanswim.securityforprogrammers.Repositories;

import me.moosecanswim.securityforprogrammers.Model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User,Long> {
    User findUserByUsername(String username);
}
