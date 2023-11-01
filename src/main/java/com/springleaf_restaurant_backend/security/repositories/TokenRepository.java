// package com.springleaf_restaurant_backend.security.repositories;

// import java.util.List;
// import java.util.Optional;

// import org.springframework.data.jpa.repository.JpaRepository;
// import org.springframework.data.jpa.repository.Query;
// import org.springframework.data.repository.query.Param;

// import com.springleaf_restaurant_backend.security.token.Token;

// public interface TokenRepository extends JpaRepository<Token, Integer> {

//   @Query("SELECT t FROM Token t JOIN t.user u WHERE u.userId = :user_id AND (t.expired = false OR t.revoked = false)")
//   List<Token> findAllValidTokensByUserId(@Param("user_id") Long userId);
  
//   Optional<Token> findByToken(String token);
// }
