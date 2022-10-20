package com.example.webeconomy.data.repositories;
import java.util.Optional;

import org.springframework.data.jdbc.repository.query.Query;
// import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.webeconomy.data.entities.*;

public interface AccountRepository extends JpaRepository<Account, Long> {

    // List<Accounts> findByRoleid(int roleid);
    // @Query(value="Select * from customer c join account a on a.id=c.accountid")
    Optional<Account> findByPhoneNumber (String phoneNumber);

}
