package com.example.webeconomy.Repository;
import org.springframework.data.jdbc.repository.query.Query;
// import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.webeconomy.Entities.*;

public interface AccountsRepository extends JpaRepository<Accounts, Long> {

    // List<Accounts> findByRoleid(int roleid);
    @Query(value="Select * from customer c join account a on a.id=c.accountid")
    Accounts findByPhonenumber (String phonenumber);

}
