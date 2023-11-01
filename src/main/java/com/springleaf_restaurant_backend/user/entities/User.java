// package com.springleaf_restaurant_backend.user.entities;

// import lombok.*;

// import java.util.ArrayList;
// import java.util.Collection;
// import java.util.List;

// import org.springframework.security.core.GrantedAuthority;
// import org.springframework.security.core.authority.SimpleGrantedAuthority;
// import org.springframework.security.core.userdetails.UserDetails;

// import com.fasterxml.jackson.annotation.JsonIgnore;
// import com.springleaf_restaurant_backend.security.token.Token;

// import jakarta.persistence.*;

// @Data
// @AllArgsConstructor
// @NoArgsConstructor
// @Entity
// @Builder
// @Table(name = "Users")
// public class User implements UserDetails {
//     @Id
//     @Column(name = "user_id")
//     @GeneratedValue(strategy = GenerationType.IDENTITY)
//     private Long userId;

//     @Column(name = "full_name")
//     private String fullName;

//     @Column(name = "username")
//     private String username;

//     @Column(name = "password")
//     private String password;

//     @Column(name = "phone")
//     private String phone;

//     @Column(name = "email")
//     private String email;

//     @Column(name = "address")
//     private Integer address;

//     @Column(name = "image")
//     private String image;

//     @Column(name = "manager_id")
//     private Long managerId;

//     @Column(name = "restaurant_brach_id")
//     private Long restaurantBrachId;

//     @Column(name = "role_id")
//     private Integer roleId;

//     @Transient
//     private String roleName;

//     @JsonIgnore
//     @OneToMany(mappedBy = "user")
//     private List<Token> tokens;

//     @Override
//     public Collection<? extends GrantedAuthority> getAuthorities() {
//         // Sử dụng thông tin roleName để lấy danh sách quyền
//         List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        
//         // Thêm quyền ROLE_<role_name> vào danh sách authorities
//         authorities.add(new SimpleGrantedAuthority("ROLE_" + roleName)); 
    
//         return authorities;
//     }

//     @Override
//     public String getPassword() {
//         return password;
//     }

//     @Override
//     public String getUsername() {
//         return username;
//     }

//     @Override
//     public boolean isAccountNonExpired() {
//         return true;
//     }

//     @Override
//     public boolean isAccountNonLocked() {
//         return true;
//     }

//     @Override
//     public boolean isCredentialsNonExpired() {
//         return true;
//     }

//     @Override
//     public boolean isEnabled() {
//         return true;
//     }
// }
