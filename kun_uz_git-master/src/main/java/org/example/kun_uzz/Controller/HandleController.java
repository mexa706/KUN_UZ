package org.example.kun_uzz.Controller;

import org.example.kun_uzz.exp.AppBadException;
import org.example.kun_uzz.exp.AppForbiddenException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

public class HandleController {

    @ExceptionHandler(AppBadException.class)
    public ResponseEntity<String> handler(AppBadException e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }

    @ExceptionHandler(AppForbiddenException.class)
    public ResponseEntity<String> handler(AppForbiddenException e) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(e.getMessage());
    }


}
