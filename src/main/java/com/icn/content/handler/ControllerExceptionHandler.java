package com.icn.content.handler;

import com.icn.content.exception.DeleteContentException;
import com.icn.content.exception.RegisterContentException;
import com.icn.content.response.ResponseMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Date;

@ControllerAdvice
public class ControllerExceptionHandler {

    private final Logger logger = LoggerFactory.getLogger(ControllerExceptionHandler.class);

    @ExceptionHandler(RegisterContentException.class)
    public ResponseEntity<ResponseMessage> handleRegisterContentException(RegisterContentException e){
        ResponseMessage responseMessage = ResponseMessage.builder()
                .responseTime(new Date())
                .data("게시물 등록 중 에러가 발생하였습니다.")
                .build();

        return new ResponseEntity<ResponseMessage>(responseMessage, HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(DeleteContentException.class)
    public ResponseEntity<ResponseMessage> handleDeleteContentException(DeleteContentException e){
        ResponseMessage responseMessage = ResponseMessage.builder()
                .responseTime(new Date())
                .data("게시물 삭제 중 에러가 발생하였습니다.")
                .build();

        return new ResponseEntity<ResponseMessage>(responseMessage, HttpStatus.FORBIDDEN);
    }
}
