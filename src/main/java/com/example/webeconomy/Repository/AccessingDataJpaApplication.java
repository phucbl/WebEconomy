package com.example.webeconomy.Repository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

import com.example.webeconomy.Controllers.*;
import com.example.webeconomy.Entities.*;

@ComponentScan(basePackages = "com.example.webeconomy")
@SpringBootApplication
@EntityScan("com.example.webeconomy.*")
public class AccessingDataJpaApplication {

  

  public static void main(String[] args) {
    SpringApplication.run(AccessingDataJpaApplication.class, args);
  }
  
  @Bean
  public CommandLineRunner demo(AccountsRepository accountsRepository) {
    return (args) -> {
      
      // save a few customers
      // repository.save(new Accounts("0928105223","123",1));
      // repository.save(new Accounts("0928105224","123",2));
      // repository.save(new Accounts("0928105225","124",2));

      // System.out.println("------Find All");
      // for (Accounts accounts : repository.findAll()) {
      //   System.out.println(accounts.toString()+"\n");
      // }
      

      // System.out.println("------Find phonenumber");
      // Accounts accounts = repository.findByPhoneNumber("0928105220");

      // System.out.println(accounts.toString()+"\n");

      // System.out.println("------Find role 2");
      // repository.findByRoleid(2).forEach(bauer -> {
      //   System.out.println(bauer.toString()+"\n");
      // });
    };
    }
    @Bean
    public CommandLineRunner demo1(CustomersRepository customersRepository){
      return (args) -> {
        //Save some customers

        // repository.save(new Customers(2,"ABC","ABCD"));
        // repository.save(new Customers(3,"AFC","ABCDE"));
        // System.out.println("------Find All Customer");
        // for (Customers customers : repository.findAll()) {
        // System.out.println(customers.toString()+"\n");
        // } 

        // System.out.println("------Find Customer with name");
        // for (Customers customers :repository.findByName("AFC")){
        //   System.out.println(customers.toString()+"\n");
        // }          
        
      };
    }
}