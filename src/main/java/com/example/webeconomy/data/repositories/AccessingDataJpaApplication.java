package com.example.webeconomy.data.repositories;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

import com.example.webeconomy.controllers.*;
import com.example.webeconomy.data.entities.*;

@ComponentScan(basePackages = "com.example.webeconomy")
@SpringBootApplication
@EntityScan("com.example.webeconomy.*")
public class AccessingDataJpaApplication {

  

  public static void main(String[] args) {
    SpringApplication.run(AccessingDataJpaApplication.class, args);
  }
  
  @Bean
  public CommandLineRunner demo(AccountRepository accountRepository) {
    return (args) -> {

      // if (accountRepository.findAll().isEmpty()){
      //   accountRepository.save(new Account("0928105220","123",1));
      //   accountRepository.save(new Account("0928105221","123",2));
      //   accountRepository.save(new Account("0928105222","124",2));
      //   accountRepository.save(new Account("0928105223","123",2));
      //   accountRepository.save(new Account("0928105224","123",2));
      //   accountRepository.save(new Account("0928105225","124",2));
      // }
  //     // save a few customers
  //     // repository.save(new Accounts("0928105223","123",1));
  //     // repository.save(new Accounts("0928105224","123",2));
  //     // repository.save(new Accounts("0928105225","124",2));

  //     // System.out.println("------Find All");
  //     // for (Accounts accounts : repository.findAll()) {
  //     //   System.out.println(accounts.toString()+"\n");
  //     // }
      

  //     // System.out.println("------Find phonenumber");
  //     // Accounts accounts = repository.findByPhoneNumber("0928105220");

  //     // System.out.println(accounts.toString()+"\n");

  //     // System.out.println("------Find role 2");
  //     // repository.findByRoleid(2).forEach(bauer -> {
  //     //   System.out.println(bauer.toString()+"\n");
  //     // });
    };
    }
    @Bean
    public CommandLineRunner demo1(CustomerRepository customerRepository){
      return (args) -> {
        //Save some customers
        // customerRepository.save(new Customer(5,"AFC","ABCDE"));
        // if (customerRepository.findAll().isEmpty()){
        //   customerRepository.save(new Customer(2,"ABC","AGDW"));
        //   customerRepository.save(new Customer(3,"AFC","ABCDE"));
        //   customerRepository.save(new Customer(4,"ABC","ABCD"));
        //   customerRepository.save(new Customer(5,"AFC","ABCDE"));
        //   }
    //     // 
    //     // System.out.println("------Find All Customer");
    //     // for (Customers customers : repository.findAll()) {
    //     // System.out.println(customers.toString()+"\n");
    //     // } 

    //     // System.out.println("------Find Customer with name");
    //     // for (Customers customers :repository.findByName("AFC")){
    //     //   System.out.println(customers.toString()+"\n");
    //     // }          
        
      };
    }
}