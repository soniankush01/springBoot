package com.excercise.code.util;

import com.excercise.code.domain.User;
import com.excercise.code.domain.Users;
import com.excercise.code.dto.Item;
import com.excercise.code.dto.Repo;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class GitUtil {


  public static Repo getRequiredNumber(Repo repo, int numberOfRepo) {
    List<Item> items = new ArrayList<>();
    for (int i=0; i<numberOfRepo; i++) {
      items.add(Item.builder().name(repo.getItems().get(i).getName())
              .description(repo.getItems().get(i).getDescription())
              .html_url(repo.getItems().get(i).getHtml_url())
              .language(repo.getItems().get(i).getLanguage()).build());
    }
    repo.setItems(items);
    return repo;
  }

  public static Users getRequiredNumberOFUser(Users users, int numberOfRepo) {
    List<User> userList = new ArrayList<>();
    for (int i=0; i<numberOfRepo; i++) {
      userList.add(User.builder().html_url(users.getItems().get(i).getHtml_url())
              .id(users.getItems().get(i).getId())
              .login(users.getItems().get(i).getOwner().getLogin()).build());
    }
    users.setItems(userList);
    return users;
  }

  public static String getDateRange(int numbarofDays) {
    Date today=new Date();
    long ltime=today.getTime()-numbarofDays*24*60*60*1000;
    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
    String isoDatePattern = "yyyy-MM-dd'T'HH:mm:ss'Z'";
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat(isoDatePattern);
    String dateString = simpleDateFormat.format(new Date(ltime));
    return "created:<".concat(dateString);
  }
}
