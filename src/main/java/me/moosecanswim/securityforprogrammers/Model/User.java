package me.moosecanswim.securityforprogrammers.Model;


import javax.persistence.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Entity
//@Table(name="User_DATA")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    //@Column(name="email",nullable = false)
    private String email;
    //@Column(name="password")
    private String password;
    //@Column(name="first_name")
    private String firstName;
    //@Column(name="last_name")
    private String lastName;
    //@Column(name="emabled")
    private boolean enabled;
    //@Column(name="username")
    private String username;
    @ManyToMany(fetch = FetchType.EAGER)
    //@JoinTable(joinColumns = @JoinColumn(name="user_id"), inverseJoinColumns = @JoinColumn(name="role_id"))
    @JoinColumn(name="userId")
    private Set<Role> roles;


    public User(){
        setRoles(new HashSet<Role>());
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void addRoles(Role r){
        System.out.println("the role i am adding to a user is "+r.toString());
        this.roles.add(r);
    }


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", enabled=" + enabled +
                ", username='" + username + '\'' +
                ", roles=" + roles +
                '}';
    }
}
