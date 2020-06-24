package com.excercise.code.mapper;

import com.excercise.code.domain.RepositoriesInfo;
import com.excercise.code.domain.RepositoryInfo;
import com.excercise.code.domain.Users;
import com.excercise.code.dto.Repo;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class RepoMapper {

  public static Repo toRepo(String response) {
    Gson gson = new Gson();
    log.info("Converting Git Object to Repo class");
    return gson.fromJson(response, Repo.class);
  }

  public static Users toUsers(String response) {
    Gson gson = new Gson();
    log.info("Converting Git Object to Repo class");
    return gson.fromJson(response, Users.class);
  }

  public static RepositoriesInfo toRepositoryInfo(Repo repo) {
    List<RepositoryInfo> repositoryInfos = new ArrayList<>();
    repo.getItems().stream().forEach(re -> {
      repositoryInfos.add(RepositoryInfo.builder().name(re.getName())
              .description(re.getDescription())
              .html_url(re.getHtml_url())
              .language(re.getLanguage()).build());

    } );
    RepositoriesInfo repositoriesInfo = RepositoriesInfo.builder().repositoriesInfoList(repositoryInfos).build();
    return repositoriesInfo;
  }
}
