package com.excercise.code.service.impl;

import com.excercise.code.client.GitClient;
import com.excercise.code.domain.RepositoriesInfo;
import com.excercise.code.domain.Users;
import com.excercise.code.dto.Repo;
import com.excercise.code.exception.CTSBusinessException;
import com.excercise.code.mapper.RepoMapper;
import com.excercise.code.service.GitService;
import com.excercise.code.util.GitUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
@Slf4j
public class GitServiceImpl implements GitService {

  @Autowired
  GitClient gitClient;

  @Value("${cts.numbarofDays:7}")
  private int numbarofDays;

  @Override
  // Hystrix can be applied to use circuit breaker pattern
  public RepositoriesInfo getTopRepo(int numberOfRepo) throws CTSBusinessException {
    log.info("Top git repository retrival", numberOfRepo);
    String repoResponse="";
    try {
       repoResponse = gitClient.getAllRepo("asc", "joined", GitUtil.getDateRange(numbarofDays));
    } catch (Exception exception) {
      log.error("Exception in calling Git Client", exception.getCause());
      throw new CTSBusinessException(HttpStatus
              .SERVICE_UNAVAILABLE.value(), "No Response return from service");
    }
   if (StringUtils.isEmpty(repoResponse)) {
     log.error("No Response from git repository");
    throw new CTSBusinessException(HttpStatus
            .NOT_FOUND.value(), "No Response return from service");
   }
    Repo repo = GitUtil.getRequiredNumber(RepoMapper.toRepo(repoResponse), numberOfRepo);
    return RepoMapper.toRepositoryInfo(repo);
  }

  @Override
  public Users getUserWithZeroFllower(int numberOfRepo) throws CTSBusinessException {
    log.info("Top git repository retrival", numberOfRepo);
    String repoResponse="";
    try {
      repoResponse = gitClient.getAllRepo("asc", "joined","followers:0");
    } catch (Exception exception) {
      log.error("Exception in calling Git Client", exception.getCause());
      throw new CTSBusinessException(HttpStatus
              .SERVICE_UNAVAILABLE.value(), "No Response return from service");
    }
    if (StringUtils.isEmpty(repoResponse)) {
      log.error("No Response from git repository");
      throw new CTSBusinessException(HttpStatus
              .NOT_FOUND.value(), "No Response return from service");
    }
    Users users = GitUtil.getRequiredNumberOFUser(RepoMapper.toUsers(repoResponse), numberOfRepo);
    return users;
  }
}
