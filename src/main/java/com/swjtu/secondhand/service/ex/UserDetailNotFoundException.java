package com.swjtu.secondhand.service.ex;

public class UserDetailNotFoundException extends ServiceException{
    public UserDetailNotFoundException() {
        super();
    }

    public UserDetailNotFoundException(String message) {
        super(message);
    }

    public UserDetailNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public UserDetailNotFoundException(Throwable cause) {
        super(cause);
    }

    protected UserDetailNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
