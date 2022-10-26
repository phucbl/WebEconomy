package com.example.webeconomy.services.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.webeconomy.constants.Erole;
import com.example.webeconomy.data.entities.Account;
import com.example.webeconomy.data.repositories.AccountRepository;
import com.example.webeconomy.dto.request.AccountUpdateDto;
import com.example.webeconomy.dto.request.LoginInputDto;
import com.example.webeconomy.dto.response.LoginResponseDto;
import com.example.webeconomy.exceptions.ResourceNotFoundException;
import com.example.webeconomy.exceptions.ValidationException;
import com.example.webeconomy.security.JwtTokenProvider;
import com.example.webeconomy.services.AuthService;

@Service
public class AuthServiceImpl implements AuthService{

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private ModelMapper modelMapper;

    // @Autowired
    // PasswordEncoder encoder = new BCryptPasswordEncoder();

    // @Autowired
    // private PasswordEncoder encoder;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    // @Autowired
    // public AuthServiceImpl(AccountRepository accountRepository, JwtTokenProvider jwtTokenProvider) {
    //     this.accountRepository = accountRepository;
    //     // this.encoder=encoder;
    //     this.jwtTokenProvider = jwtTokenProvider;
    // }

    @Override
    public UserDetails loadUserByUsername (String phoneNumber) throws UsernameNotFoundException{
        Optional<Account> accountOptional = accountRepository.findByPhoneNumber(phoneNumber);
        if (accountOptional.isEmpty()){
            throw new ResourceNotFoundException("Account not found");
        }
        Account account = accountOptional.get();
        if (account.isStatus()==false){
            throw new ValidationException("Account is disabled");
        }
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(account.getRoleId().name()));
        return new User(account.getPhoneNumber(),account.getPassword(),authorities);
        
    }

    

    @Override
    public LoginResponseDto login (LoginInputDto dto){
        String phoneNumber = dto.getPhoneNumber();
        Optional<Account> accountOptional = accountRepository.findByPhoneNumber(phoneNumber);
        if (accountOptional.isEmpty()){
            throw new ResourceNotFoundException("Phone number not found");
        }
        Account account = accountOptional.get();
        if (account.isStatus()==false){
            throw new ValidationException("Account is disabled");
        }
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(account.getPhoneNumber(), account.getPassword());
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        UserDetails userDetails = loadUserByUsername(phoneNumber);
        String jwt = jwtTokenProvider.generateJwtToken(userDetails);
        Date expriredDate = jwtTokenProvider.getExpirationDate(jwt);
        LoginResponseDto loginResponseDto = new LoginResponseDto(jwt, expriredDate);
        return loginResponseDto;
    }
    
}
