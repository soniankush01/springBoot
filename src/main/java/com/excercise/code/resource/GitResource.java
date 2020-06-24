package com.excercise.code.resource;

import com.excercise.code.domain.RepositoriesInfo;
import com.excercise.code.domain.Users;
import com.excercise.code.exception.CTSBusinessException;
import com.excercise.code.service.GitService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@Slf4j
public class GitResource {

  @Autowired
  GitService gitService;

  @RequestMapping(method = RequestMethod.GET, path="/topRepositories")
  public ResponseEntity getTopRepositories(@RequestParam(value = "numberOfRepo", required = true)
                                                     int numberOfRepo) throws CTSBusinessException {
    log.info("Get request for top repositories");
    if (numberOfRepo<0) {
      throw new CTSBusinessException(400,"Input cant be negative");
    }
    ResponseEntity<RepositoriesInfo> responseEntity = new ResponseEntity<>(gitService.getTopRepo(numberOfRepo),
            HttpStatus.OK);
    log.debug("Response top repositories", responseEntity);
    return responseEntity;
  }

  @RequestMapping(method = RequestMethod.GET, path="/zeroFollowers")
  public ResponseEntity getZeroFollowersRepo(@RequestParam(value = "numberOfRepo", required = true)
                                                   int numberOfRepo) throws CTSBusinessException {
    log.info("Get request for zero followers repositories");
    ResponseEntity<Users> responseEntity =
            new ResponseEntity<>(gitService.getUserWithZeroFllower(numberOfRepo),
            HttpStatus.OK);
    log.debug("Response top zero followers repositories", responseEntity);
    return responseEntity;
  }
}
