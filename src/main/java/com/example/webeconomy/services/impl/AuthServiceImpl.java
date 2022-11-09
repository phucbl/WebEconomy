package com.example.webeconomy.services.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.example.webeconomy.data.entities.Account;
import com.example.webeconomy.data.entities.Customer;
import com.example.webeconomy.data.repositories.AccountRepository;
import com.example.webeconomy.data.repositories.CustomerRepository;
import com.example.webeconomy.dto.request.LoginInputDto;
import com.example.webeconomy.dto.response.LoginResponseDto;
import com.example.webeconomy.dto.response.ResponseDto;
import com.example.webeconomy.exceptions.BadRequestException;
import com.example.webeconomy.exceptions.ResourceNotFoundException;
import com.example.webeconomy.security.JwtTokenProvider;
import com.example.webeconomy.services.AuthService;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Override
    public UserDetails loadUserByUsername(String phoneNumber) throws UsernameNotFoundException {
        Optional<Account> accountOptional = accountRepository.findByPhoneNumber(phoneNumber);
        if (accountOptional.isEmpty()) {
            throw new ResourceNotFoundException("Account not found");
        }
        Account account = accountOptional.get();
        if (account.isStatus() == false) {
            throw new BadRequestException("Account is disabled");
        }
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(account.getRoleId().name()));
        return new User(account.getPhoneNumber(), account.getPassword(), authorities);
    }

    @Override
    public ResponseEntity<ResponseDto> login(LoginInputDto dto) {
        String phoneNumber = dto.getPhoneNumber();
        Optional<Account> accountOptional = accountRepository.findByPhoneNumber(phoneNumber);
        if (accountOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ResponseDto(null, "Invalid phone number or password!", "400"));
        }
        Account account = accountOptional.get();
        if (account.isStatus() == false) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ResponseDto(null, "Account is disabled", "400"));
        }
        PasswordEncoder encoder = new BCryptPasswordEncoder();
        if (!encoder.matches(dto.getPassword(), account.getPassword())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ResponseDto(null, "Invalid phone number or password!", "400"));
        }
        String customerName = "Admin";
        Long customerId = 0l;
        Optional<Customer> customeroOptional = customerRepository.findByAccountId(account.getId());
        if (customeroOptional.isPresent()) {
            customerName = customeroOptional.get().getName();
            customerId = customeroOptional.get().getId();
        }
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                account.getPhoneNumber(), account.getPassword());
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        UserDetails userDetails = loadUserByUsername(phoneNumber);
        String jwt = jwtTokenProvider.generateJwtToken(userDetails);
        Date expriredDate = jwtTokenProvider.getExpirationDate(jwt);
        LoginResponseDto loginResponseDto = new LoginResponseDto(customerName, customerId, account.getRoleId(), jwt,
                expriredDate);
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseDto(loginResponseDto, "Login Suscess!", "200"));
    }

}
