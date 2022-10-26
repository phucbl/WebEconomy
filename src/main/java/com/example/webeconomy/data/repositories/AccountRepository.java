package com.example.webeconomy.data.repositories;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.webeconomy.data.entities.*;

public interface AccountRepository extends JpaRepository<Account, Long> {
    Optional<Account> findByPhoneNumber (String phoneNumber);
    Boolean existsByPhoneNumber (String phoneNumber);
}
