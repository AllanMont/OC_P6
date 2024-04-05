package com.openclassrooms.mddapi.dto;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PostDto {
  private Integer id;
  private String title;
  private String content;
  private Integer topicId;
  private Integer authorId;
  private LocalDateTime createdAt;
  private LocalDateTime updatedAt;
}