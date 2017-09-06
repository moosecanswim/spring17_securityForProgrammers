package me.moosecanswim.securityforprogrammers.Model;

import javax.persistence.*;
import java.util.Collection;
import java.util.HashSet;

@Entity
public class Role {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long id;
    //@Column(unique=true)
    private String role;
    @ManyToMany(mappedBy="roles",fetch= FetchType.EAGER)
    private Collection<User> user;

    public Role(){
        setUser(new HashSet<User>());
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "Role{" +
                "id=" + id +
                ", role='" + role + '\'' +
                ", user=" + user +
                '}';
    }

    public Collection<User> getUser() {
        return user;
    }

    public void setUser(Collection<User> user) {
        this.user = user;
    }


}
