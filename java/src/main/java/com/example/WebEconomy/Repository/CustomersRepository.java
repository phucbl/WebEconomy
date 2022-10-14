package com.example.WebEconomy.Repository;
import com.example.WebEconomy.Entities.*;
import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(basePackages = "com.example.WebEconomy")
@SpringBootApplication
@EntityScan("com.example.WebEconomy.*")
public interface CustomersRepository extends CrudRepository<Customers, Long> {

    List<Customers> findByAccountid(int accountid);
    List<Customers> findByName (String name);
    
}
