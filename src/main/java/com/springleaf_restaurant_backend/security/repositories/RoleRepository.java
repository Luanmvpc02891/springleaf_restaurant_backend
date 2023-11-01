// package com.springleaf_restaurant_backend.security.repositories;

// import java.util.List;

// import org.springframework.data.jpa.repository.JpaRepository;
// import org.springframework.data.jpa.repository.Query;
// import org.springframework.data.repository.query.Param;

// import com.springleaf_restaurant_backend.user.entities.Role;

// public interface RoleRepository extends JpaRepository<Role, Integer> {
//     Role findByRoleId(Integer roleId);
//     List<Role> findAll();
//     @Query("SELECT r.roleSa FROM Role r WHERE r.roleId = :roleId")
//     String findRoleSaByRoleId(@Param("roleId") Integer roleId);
// }
