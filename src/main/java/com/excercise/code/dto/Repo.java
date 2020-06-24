package com.excercise.code.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class Repo {
  private List<Item> items;

}
