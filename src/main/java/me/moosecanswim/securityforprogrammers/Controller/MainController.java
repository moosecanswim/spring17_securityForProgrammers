package me.moosecanswim.securityforprogrammers.Controller;

import me.moosecanswim.securityforprogrammers.Model.Person;
import me.moosecanswim.securityforprogrammers.Model.Role;
import me.moosecanswim.securityforprogrammers.Repositories.PersonRepository;
import me.moosecanswim.securityforprogrammers.Repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Collection;

@Controller
public class MainController {

    @Autowired
    RoleRepository roleRepository;
    @Autowired
    PersonRepository personRepository;

    @RequestMapping("/")
    public String index(){

        if(roleRepository.count()<1) {

            Person person1 = new Person();
            person1.setId(1);
            person1.setEmail("jim@jim.com");
            person1.setEnabled(true);
            person1.setFirstName("Jim");
            person1.setLastName("Jimmerson");
            person1.setPassword("password");
            person1.setUsername("jim");

            Person person2 = new Person();
            person2.setId(2);
            person2.setEmail("bob@bob.com");
            person2.setEnabled(true);
            person2.setFirstName("Bob");
            person2.setLastName("Bobberson");
            person2.setPassword("password");
            person2.setUsername("bob");

            Person person3 = new Person();
            person3.setId(3);
            person3.setEmail("admin@admin.com");
            person3.setEnabled(true);
            person3.setFirstName("Admin");
            person3.setLastName("User");
            person3.setPassword("password");
            person3.setUsername("admin");

            personRepository.save(person1);
            personRepository.save(person2);
            personRepository.save(person3);

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
            person1.addRoles(role1);
            person2.addRoles(role1);
            person3.addRoles(role1);
            person3.addRoles(role2);


            personRepository.save(person1);
            personRepository.save(person2);
            personRepository.save(person3);

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
}
