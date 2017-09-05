package me.moosecanswim.securityforprogrammers;

import me.moosecanswim.securityforprogrammers.Model.Person;
import me.moosecanswim.securityforprogrammers.Model.Role;
import me.moosecanswim.securityforprogrammers.Repositories.PersonRepository;
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
public class SSPersonDetailsService implements UserDetailsService {
    private PersonRepository personRepository;

    public SSPersonDetailsService(PersonRepository personRepository){
        this.personRepository=personRepository;
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("my username is " +username);
        try{
            Person person = personRepository.findByUsername(username);
            System.out.println("my login person is" + person.toString());
            if(person == null){
                System.out.println("person not found with the provided username " + person.toString());
                return null;
            }
            System.out.println(" person from username " + person.toString());
            return new org.springframework.security.core.userdetails.User(person.getUsername(),person.getPassword(),getAuthorities(person));
        }
        catch (Exception e){
            throw new UsernameNotFoundException("User not found");
        }
    }
    private Set<GrantedAuthority> getAuthorities(Person person){
        Set<GrantedAuthority> authorities = new HashSet<GrantedAuthority>();
        for(Role role: person.getRoles()){
            GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(role.getRole());
            authorities.add(grantedAuthority);
        }
        System.out.println("person authorities are "+ authorities.toString());
        return authorities;
    }
}
