package com.excercise.code.domain;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RepositoryInfo {
  private String html_url;
  private String watchers_count;
  private String language;
  private String description;
  private String name;
}
