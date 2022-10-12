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
      // repository.save(new Accounts("0928105223","123",1));
      // repository.save(new Accounts("0928105224","123",2));
      // repository.save(new Accounts("0928105225","124",2));

      System.out.println("------Find All");
      for (Accounts accounts : repository.findAll()) {
        System.out.println(accounts.toString()+"\n");
      }
      

      System.out.println("------Find phonenumber");
      Accounts accounts = repository.findByPhoneNumber("0928105220");

      System.out.println(accounts.toString()+"\n");

      System.out.println("------Find role 2");
      repository.findByRoleId(2).forEach(bauer -> {
        System.out.println(bauer.toString()+"\n");
      });
      
      
    };
    }
}