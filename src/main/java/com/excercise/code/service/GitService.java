package com.excercise.code.service;

import com.excercise.code.domain.RepositoriesInfo;
import com.excercise.code.domain.Users;
import com.excercise.code.exception.CTSBusinessException;

public interface GitService {

   RepositoriesInfo getTopRepo (int numberOfRepo) throws CTSBusinessException;

   Users getUserWithZeroFllower(int numberOfRepo) throws CTSBusinessException;
}
