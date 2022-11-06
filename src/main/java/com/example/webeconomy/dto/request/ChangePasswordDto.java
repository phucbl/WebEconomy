package com.example.webeconomy.dto.request;

import lombok.Data;

@Data
public class ChangePasswordDto {
    private String password;
    private String newPassword;
}
