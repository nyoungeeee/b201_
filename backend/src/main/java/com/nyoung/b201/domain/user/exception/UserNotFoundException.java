package com.nyoung.b201.domain.user.exception;

import com.nyoung.b201.common.BusinessException;
import com.nyoung.b201.common.ErrorCode;

public class UserNotFoundException extends BusinessException {
    public UserNotFoundException(Long id) {
        super(ErrorCode.USER_NOT_FOUND, "User not found. id=" + id);
    }
}