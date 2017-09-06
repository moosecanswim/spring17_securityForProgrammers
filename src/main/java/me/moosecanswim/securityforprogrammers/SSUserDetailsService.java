package me.moosecanswim.securityforprogrammers;

import me.moosecanswim.securityforprogrammers.Model.User;
import me.moosecanswim.securityforprogrammers.Model.Role;
import me.moosecanswim.securityforprogrammers.Repositories.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.Set;

@Transactional
@Service
public class SSUserDetailsService implements UserDetailsService {
    private UserRepository userRepository;

    public SSUserDetailsService(UserRepository userRepository){
        this.userRepository=userRepository;
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("my username is " +username);
        try{
            System.out.println("i am in the try loop of loadUserByUsername");
            User user = userRepository.findUserByUsername(username);
            System.out.println("i have found a user in userRepository");
            if(user == null){
                System.out.println("user not found with the provided username ");
                return null;
            }
            //System.out.println(" user from username " + user.toString());
            System.out.println(user.getUsername());
            System.out.println(user.getPassword());
            System.out.println(getAuthorities(user).toString());//here is my error************

            return new org.springframework.security.core.userdetails.User(user.getUsername(),user.getPassword(),getAuthorities(user));
        }
        catch (Exception e){
            System.out.println("exception! " +e.toString());

            throw new UsernameNotFoundException("User not found");
        }
    }
    private Set<GrantedAuthority> getAuthorities(User user){
        System.out.println("my getAuthorities User is: " + user+ " and i have " +user.getRoles().size() + " roles");
        int count=1;

        Set<GrantedAuthority> authorities = new HashSet<GrantedAuthority>();

        for(Role role: user.getRoles()){
            System.out.println(String.format("%s has %s role (%d)",user.getUsername(),role.toString(),count));
            count++;
            GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(role.getRole());
            authorities.add(grantedAuthority);
        }
        System.out.println("user authorities are "+ authorities.toString());
        return authorities;
    }
}
