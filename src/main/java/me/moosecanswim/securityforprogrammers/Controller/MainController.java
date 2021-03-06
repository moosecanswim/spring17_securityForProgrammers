package me.moosecanswim.securityforprogrammers.Controller;

import me.moosecanswim.securityforprogrammers.Model.User;
import me.moosecanswim.securityforprogrammers.Model.Role;
import me.moosecanswim.securityforprogrammers.Repositories.UserRepository;
import me.moosecanswim.securityforprogrammers.Repositories.RoleRepository;
import me.moosecanswim.securityforprogrammers.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;
import java.security.Principal;
import java.util.Collection;

@Controller
public class MainController {
    @Autowired
    private UserService userService;
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
    public String login(Principal p){
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
    @RequestMapping("/testRoles")
    public @ResponseBody String showRoles(){
        Iterable <Role> r = roleRepository.findAll();
        String x="<h2>ROLE DETAILS</><br/>";
        for(Role item:r){
            x+="Role details:"+item.getRole()+" has an ID of " +item.getId() +"<br/>";
        }
        Role findR= roleRepository.findByRole("ADMIN");
        x+=findR.getRole()+" was found with an ID of "+ findR.getId();
        return x;
    }

    @RequestMapping("/adduser")
    public @ResponseBody String addUser(){
        User u = new User();
        u.setEmail("someone@somewhere.com");
        u.setUsername("newuser");
        u.setPassword("password");
        u.setEnabled(true);
        u.addRole(roleRepository.findByRole("ADMIN"));
        userRepository.save(u);
        return "User added";
    }

    @GetMapping("/register")
    public String showRegistrationPage(Model toSend){
        toSend.addAttribute("user", new User());
        return "registration";
    }
    @PostMapping("/register")
    public String processRegistrationPage(@Valid User user, BindingResult result,Model toSend){
        toSend.addAttribute("user", user);
        if(result.hasErrors()){
            return "registration";
        }else{
            userService.saveUser(user);
            toSend.addAttribute("message","User Account Successfully Created");
        }
        return "index";
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

        user1.addRole(role1);
        user2.addRole(role1);
        user3.addRole(role1);
        user3.addRole(role2);


        userRepository.save(user1);
        userRepository.save(user2);
        userRepository.save(user3);
    }
}
