package com.openclassrooms.mddapi.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TopicDto {
  private Integer id;
  private String name;
  private String description;
  private LocalDateTime created_at;
}