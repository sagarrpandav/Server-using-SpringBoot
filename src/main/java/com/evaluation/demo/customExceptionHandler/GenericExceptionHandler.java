package com.evaluation.demo.customExceptionHandler;

import com.evaluation.demo.customException.BlankFieldException;
import com.evaluation.demo.dto.ResponseDTO;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GenericExceptionHandler extends ResponseEntityExceptionHandler
{
    @ExceptionHandler(BlankFieldException.class)
    public final ResponseEntity<ResponseDTO> handleException(BlankFieldException e)
    {
        ResponseDTO responseDTO=new ResponseDTO();
        responseDTO.setStatus("FAILURE");
        responseDTO.setErrorMessage("Validation failure "+e.getMessage());
        return new ResponseEntity<ResponseDTO>(responseDTO,new HttpHeaders(), HttpStatus.EXPECTATION_FAILED);
    }

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<ResponseDTO> handleAnyException(Exception e)
    {
        ResponseDTO responseDTO=new ResponseDTO();
        responseDTO.setStatus("FAILURE");
        responseDTO.setErrorMessage(e.getMessage());

        return new ResponseEntity<ResponseDTO>(responseDTO,new HttpHeaders(),HttpStatus.EXPECTATION_FAILED);
    }
}
