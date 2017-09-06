package me.moosecanswim.securityforprogrammers.Controller;

import me.moosecanswim.securityforprogrammers.Model.User;
import me.moosecanswim.securityforprogrammers.Model.Role;
import me.moosecanswim.securityforprogrammers.Repositories.UserRepository;
import me.moosecanswim.securityforprogrammers.Repositories.RoleRepository;
import me.moosecanswim.securityforprogrammers.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Collection;

@Controller
public class MainController {

    @Autowired
    RoleRepository roleRepository;
    @Autowired
    UserRepository userRepository;

    @RequestMapping("/")
    public String index(){

        if(roleRepository.count()<1) {
            addDefaults();
        }

        return "index";
    }
    @RequestMapping("/login")
    public String login(){
        return "login";
    }
    @RequestMapping("/admin")
    public String admin(){
        return "admin";
    }
    @RequestMapping("/secure")
    public String secure(){
        return "secure";
    }

    public void addDefaults(){
        User user1 = new User();
        user1.setId(1);
        user1.setEmail("jim@jim.com");
        user1.setEnabled(true);
        user1.setFirstName("Jim");
        user1.setLastName("Jimmerson");
        user1.setPassword("password");
        user1.setUsername("jim");

        User user2 = new User();
        user2.setId(2);
        user2.setEmail("bob@bob.com");
        user2.setEnabled(true);
        user2.setFirstName("Bob");
        user2.setLastName("Bobberson");
        user2.setPassword("password");
        user2.setUsername("bob");

        User user3 = new User();
        user3.setId(3);
        user3.setEmail("admin@admin.com");
        user3.setEnabled(true);
        user3.setFirstName("Admin");
        user3.setLastName("User");
        user3.setPassword("password");
        user3.setUsername("admin");

        userRepository.save(user1);
        userRepository.save(user2);
        userRepository.save(user3);

        Role role1 = new Role();
        role1.setId(1);
        role1.setRole("USER");

        Role role2 = new Role();
        role2.setId(2);
        role2.setRole("ADMIN");

        roleRepository.save(role1);
        roleRepository.save(role2);

        System.out.println("role 1 is" + role1.toString());
        System.out.println("role 2 is " + role2.toString());
        user1.addRoles(role1);
        user2.addRoles(role1);
        user3.addRoles(role1);
        user3.addRoles(role2);


        userRepository.save(user1);
        userRepository.save(user2);
        userRepository.save(user3);
    }
}
