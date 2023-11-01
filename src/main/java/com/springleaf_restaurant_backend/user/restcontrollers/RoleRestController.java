// package com.springleaf_restaurant_backend.user.restcontrollers;

// import java.util.List;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RestController;

// import com.springleaf_restaurant_backend.security.repositories.RoleRepository;
// import com.springleaf_restaurant_backend.user.entities.Role;

// @RestController
// @RequestMapping("/api")
// public class RoleRestController {
//     @Autowired
//     private RoleRepository roleRepository;

//     @GetMapping("/roles")
//     public List<Role> getRoles() {
//         return roleRepository.findAll();
//     }

// }
