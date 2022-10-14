package com.example.WebEconomy.Repository;
import com.example.WebEconomy.Entities.*;

import org.springframework.data.jdbc.repository.query.Query;
// import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountsRepository extends JpaRepository<Accounts, Long> {

    // List<Accounts> findByRoleid(int roleid);
    @Query(value="Select * from accounts a join a.customers c on a.id=c.accountid")
    Accounts findByPhonenumber (String phonenumber);

}
