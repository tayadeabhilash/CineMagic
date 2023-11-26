package com.scrumandcoke.movietheaterclub.exception;

public class GlobalException extends Exception {
    public GlobalException(String errorMessage, Throwable e) {
        super(errorMessage, e);
    }

	public GlobalException(String errorMessage) {
        super(errorMessage);
	}
}
