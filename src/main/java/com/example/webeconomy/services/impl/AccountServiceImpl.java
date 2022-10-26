package com.example.webeconomy.services.impl;
import java.util.Optional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.example.webeconomy.services.AccountService;
import com.example.webeconomy.constants.Erole;
import com.example.webeconomy.data.entities.*;
import com.example.webeconomy.data.repositories.*;
import com.example.webeconomy.dto.request.AccountUpdateDto;
import com.example.webeconomy.dto.request.CustomerUpdateDto;
import com.example.webeconomy.dto.request.NewAccountDto;
import com.example.webeconomy.dto.response.AccountResponseDto;
import com.example.webeconomy.dto.response.ResponseDto;
import com.example.webeconomy.exceptions.*;

@Service
public class AccountServiceImpl implements AccountService{
    
    private AccountRepository accountRepository;
    private CustomerRepository customerRepositoy;
    @Autowired
    private ModelMapper modelMapper;

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
            throw new ValidationException("Phone number is already signed up");
        }
        Account account = modelMapper.map(dto,Account.class);
        account.setRoleId(Erole.ADMIN);
        Account savedAccount = accountRepository.save(account);
        return modelMapper.map(savedAccount, AccountResponseDto.class);
    }

    @Override
    public AccountResponseDto createAccount (NewAccountDto dto){
        AccountUpdateDto accountUpdateDto = dto.getAccountDto();
        CustomerUpdateDto customerUpdateDto = dto.getCustomerDto();
        if (accountRepository.existsByPhoneNumber(accountUpdateDto.getPhoneNumber())){
            throw new ValidationException("Phone number is already signed up");
        }
        Account account = modelMapper.map(accountUpdateDto,Account.class);
        PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        account.setPassword(encoder.encode(account.getPassword()));
        account.setRoleId(Erole.USER);
        account = accountRepository.save(account);
        Customer customer = modelMapper.map(customerUpdateDto,Customer.class);
        customer.setAccountId(account.getId());
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
        account.setStatus(false);
        account = accountRepository.save(account);
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseDto(null, "Deactive Successfully!","200"));
    }

}
