package com.rc.redacaoconectada.entities;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.io.Serializable;
import java.time.Instant;
import java.util.*;
import java.util.stream.Collectors;

@Entity
@Table(name = "tb_user")
@Inheritance(strategy = InheritanceType.JOINED)
@NoArgsConstructor
@AllArgsConstructor
@Data
public class User implements Serializable, UserDetails {
  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String cpf;

  private String name;

  //@Column(unique = true)
  private String email;

  private String password;

  @Column(columnDefinition = "TIMESTAMP WITHOUT TIME ZONE")
  private Instant birthdate;

  private String graduation;

  private String schoolName;

  private String state;

  private String city;

  @Column(name = "image", length=1500000)
  private String image;

  @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
  private final List<Essay> essays = new ArrayList<>();

  @OneToMany(mappedBy = "essay")
  private final Set<Comment> comments = new HashSet<>();

  @OneToMany(mappedBy = "user")
  private final Set<ChangeRoleRequest> changeRoleRequest = new HashSet<>();
  
  @ManyToMany(mappedBy = "userUpVotes")
  private final List<Essay> likedEssays = new ArrayList<>();

  @ManyToMany(fetch = FetchType.EAGER)
  @JoinTable(name = "tb_user_role",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
  private final Set<Role> roles = new HashSet<>();

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return roles.stream().map(role -> new SimpleGrantedAuthority(role.getAuthority()))
            .collect(Collectors.toList());
  }

  @Override
  public String getUsername() {
    return email;
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return true;
  }
  
  public void addRole(Role role){
    this.roles.add(role);
  }
  @Override
  public String toString() {
    return "User{" +
            "id=" + id +
            ", cpf='" + cpf + '\'' +
            ", name='" + name + '\'' +
            ", email='" + email + '\'' +
            ", password='" + password + '\'' +
            ", birthdate=" + birthdate +
            ", graduation='" + graduation + '\'' +
            ", schoolName='" + schoolName + '\'' +
            ", state='" + state + '\'' +
            ", city='" + city + '\'' +
            ", essays=" + essays +
            ", comments=" + comments +
            ", roles=" + roles +
            '}';
  }
}
