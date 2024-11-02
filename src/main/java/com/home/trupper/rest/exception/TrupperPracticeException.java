package com.home.trupper.rest.exception;

import lombok.Data;
import java.io.Serial;

@Data
public class TrupperPracticeException extends RuntimeException{
    @Serial
    private static final long serialVersionUID = 1L;

    private String code;
    private String message;
    private String details;

    public TrupperPracticeException(String code, String message){
        super(message);
        this.code = code;
        this.message=message;
    }

    public TrupperPracticeException(String code, String message, String details){
        super(message);
        this.code = code;
        this.message=message;
        this.details=details;
    }
}
