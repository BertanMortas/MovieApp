package com.bilgeadam.repository;


import com.bilgeadam.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface IUserRepository extends JpaRepository<User, Integer> {

    Optional<User> findByEmailAndPassword(String email, String password);

    /**
     * önce büyük sonra küçük harfler sıralanıyor, kontrol edilecek
     */
    List<User> findAllByOrderByName();

    List<User> findAllByNameContainsIgnoreCase(String value);


    boolean existsByNameIgnoreCase(String value);

    List<User> findByEmailIgnoreCase(String email);

    Optional<User> findByEmailEqualsIgnoreCase(String email);
    // JPQL
    @Query("select u from User u where length(u.password)> ?1")
    List<User> findPasswordByLength2(int length);
    // native query
    @Query(value = "select * from users as u where length(u.password)> :x",nativeQuery = true)
    List<User> findPasswordByLength3(@Param("x") int length);
}
