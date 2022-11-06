package com.example.webeconomy.services.impl;
import java.util.Optional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.example.webeconomy.services.AccountService;
import com.example.webeconomy.constants.Erole;
import com.example.webeconomy.data.entities.*;
import com.example.webeconomy.data.repositories.*;
import com.example.webeconomy.dto.request.AccountUpdateDto;
import com.example.webeconomy.dto.request.NewAccountDto;
import com.example.webeconomy.dto.response.AccountResponseDto;
import com.example.webeconomy.dto.response.ResponseDto;
import com.example.webeconomy.exceptions.*;

@Service
public class AccountServiceImpl implements AccountService{
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private CustomerRepository customerRepositoy;
    @Autowired
    private ModelMapper modelMapper;

    private Customer customer;

    @Autowired
    public AccountServiceImpl (AccountRepository accountRepository){
        this.accountRepository = accountRepository;
    }

    // @Override
    // public List<Account> getAllAccounts() {
    //     return accountRepository.findAll();
    // }
    
    @Override
    public AccountResponseDto getAccountById(Long id){
        Optional<Account> accountOptional = this.accountRepository.findById(id);
        if (accountOptional.isEmpty()){
            throw new ResourceNotFoundException("Account Not Found with Controller Advice");
        }        
        Account account= accountOptional.get();
        return modelMapper.map(account, AccountResponseDto.class);
    }
    @Override
    public AccountResponseDto createAdminAccount(AccountUpdateDto dto){
        if (accountRepository.existsByPhoneNumber(dto.getPhoneNumber())){
            throw new ItemExistException("Phone number is already signed up");
        }
        Account account = modelMapper.map(dto,Account.class);
        account.setRoleId(Erole.ROLE_ADMIN);
        Account savedAccount = accountRepository.save(account);
        return modelMapper.map(savedAccount, AccountResponseDto.class);
    }

    @Override
    public AccountResponseDto createAccount (NewAccountDto dto){
        
        Optional<Account> accountOptional = accountRepository.findByPhoneNumber(dto.getPhoneNumber());
        if (accountOptional.isPresent()){
            throw new ItemExistException("Phone number is already signed up");
        }
        Account account = new Account(dto.getPhoneNumber(), dto.getPassword(), null);
        PasswordEncoder encoder = new BCryptPasswordEncoder();
        account.setPassword(encoder.encode(account.getPassword()));
        account.setRoleId(Erole.ROLE_USER);
        account.setStatus(true);
        account = accountRepository.save(account);
        customer = new Customer(account.getId(),dto.getName(),dto.getAddress());
        customerRepositoy.save(customer);
        return modelMapper.map(account, AccountResponseDto.class);
    }

    @Override
    public AccountResponseDto updateAccount(Long id, AccountUpdateDto dto){
        Optional<Account> accountOptional = accountRepository.findById(id);
        if (accountOptional.isEmpty()){
            throw new ResourceNotFoundException("Account Not Found");
        }
        Account account = accountOptional.get();
        modelMapper.map(dto,account);
        account = accountRepository.save(account);
        return modelMapper.map(account, AccountResponseDto.class);
    }
    @Override
    public AccountResponseDto getAccountByPhoneNumber(String phoneNumber){
        Optional<Account> accountOptional =  this.accountRepository.findByPhoneNumber(phoneNumber);
        if (accountOptional.isEmpty()){
            throw new ResourceNotFoundException("Account Not Found");
        }
        Account account= accountOptional.get();
        return modelMapper.map(account, AccountResponseDto.class);
    }

    @Override
    public ResponseEntity<ResponseDto>  deactiveAccount (Long id){
        Optional<Account> accountOptional = accountRepository.findById(id);
        if (accountOptional.isEmpty()){
            throw new ResourceNotFoundException("Account Not Found");
        }
        Account account = accountOptional.get();
        account.setStatus(!account.isStatus());
        account = accountRepository.save(account);
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseDto(null, "Deactive Successfully!","200"));
    }
    

}
