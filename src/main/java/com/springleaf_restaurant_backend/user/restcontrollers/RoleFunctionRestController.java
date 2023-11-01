// package com.springleaf_restaurant_backend.user.restcontrollers;

// import java.util.List;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RestController;

// import com.springleaf_restaurant_backend.security.repositories.RoleFunctionRepository;
// import com.springleaf_restaurant_backend.user.entities.RoleFunction;

// @RestController
// @RequestMapping("/api")
// public class RoleFunctionRestController {
//     @Autowired
//     private RoleFunctionRepository roleFunctionRepository;

//     @GetMapping("/roleFunctions")
//     public List<RoleFunction> getRoleFuns() {
//         return roleFunctionRepository.findAll();
//     }
// }
