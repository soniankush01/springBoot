package com.excercise.code.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;



@FeignClient(name= "gitClient", url = "https://api.github.com/search/repositories")
public interface GitClient {

  @RequestMapping(method = RequestMethod.GET)
  String getAllRepo(@RequestParam(value="order") String order, @RequestParam(value="sort") String sort,
                  @RequestParam(value="q") String q);
}
