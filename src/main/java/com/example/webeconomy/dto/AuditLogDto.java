package com.example.webeconomy.dto;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class AuditLogDto {
	private String message;
	private LocalDateTime datetime;
}
