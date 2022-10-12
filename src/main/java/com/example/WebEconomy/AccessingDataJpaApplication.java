package com.example.WebEconomy;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class AccessingDataJpaApplication {

  

  public static void main(String[] args) {
    SpringApplication.run(AccessingDataJpaApplication.class, args);
  }
  
  @Bean
  public CommandLineRunner demo(AccountRepository repository) {
    return (args) -> {
      // save a few customers
      repository.save(new Accounts("0928105220","123",1));
      repository.save(new Accounts("0928105221","123",2));
      repository.save(new Accounts("0928105222","123",2));

      
      for (Accounts accounts : repository.findAll()) {
        System.out.println(accounts.toString()+"\n");
      }
      

      // fetch an individual customer by ID
      Accounts accounts = repository.findByPhoneNumber("0928105220");

      System.out.println(accounts.toString()+"\n");

      
      repository.findByRoleID(2).forEach(bauer -> {
        System.out.println(bauer.toString()+"\n");
      });
      
      
    };
    }
}