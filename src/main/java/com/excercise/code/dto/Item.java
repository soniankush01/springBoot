package com.excercise.code.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Item {
  private String html_url;
  private String watchers_count;
  private String language;
  private String description;
  private String name;
}
