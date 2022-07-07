package com.example.project.school.Repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.project.school.Model.UserModel;

@Repository
public interface UserRepository extends JpaRepository<UserModel,String>{
    Optional<UserModel> findByEmail(String email);
}
