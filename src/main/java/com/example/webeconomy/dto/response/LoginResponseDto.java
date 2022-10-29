package com.example.webeconomy.dto.response;
import java.util.Date;

import com.example.webeconomy.constants.Erole;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginResponseDto {
    private String name;
    private Long customerId;
    private Erole role;
    private String token;
    private Date expirationTimestamp;    
}
