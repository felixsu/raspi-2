package com.felix.raspi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by felix on 23/04/2016.
 */

@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
public class SystemNotReadyException extends RuntimeException {
}
