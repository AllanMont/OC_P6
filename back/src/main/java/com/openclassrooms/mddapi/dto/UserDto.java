package com.openclassrooms.mddapi.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
  private Integer id;
  private String name;
  private String email;
  private String password;
  private LocalDateTime created_at;
}