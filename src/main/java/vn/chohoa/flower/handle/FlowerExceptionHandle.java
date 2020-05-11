package vn.chohoa.flower.handle;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import vn.chohoa.flower.dto.ActionDTO;
import vn.chohoa.flower.dto.ResponseDTO;
import vn.chohoa.flower.exception.DataEmptyException;
import vn.chohoa.flower.exception.DataExistsException;
import vn.chohoa.flower.exception.GeneralException;
import vn.chohoa.flower.exception.NotFoundException;
import vn.chohoa.flower.util.Constant;

@ControllerAdvice
@Slf4j
public class FlowerExceptionHandle {

    @ExceptionHandler(value = {DataEmptyException.class})
    protected ResponseEntity<ResponseDTO> dataEmpty(DataEmptyException ex, WebRequest rq){
        final ResponseDTO responseDTO = ResponseDTO.builder()
                .code(Constant.RESPONSE.CODE.C404)
                .message(Constant.RESPONSE.MESSAGE.C404)
                .build();
        log.error("[FlowerExceptionHandle.dataEmpty:{}]",ex.getMessage());
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);

    }

    @ExceptionHandler(value = {NotFoundException.class})
    protected ResponseEntity<ResponseDTO> dataNotFound(NotFoundException ex, WebRequest rq){

        final ResponseDTO responseDTO = ResponseDTO.builder()
                .code(Constant.RESPONSE.CODE.C404)
                .message(Constant.RESPONSE.MESSAGE.C404)
                .build();
        log.error("[FlowerExceptionHandle.dataEmpty:{}]",ex.getMessage());
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);

    }

    @ExceptionHandler(value = {GeneralException.class})
    protected ResponseEntity<ResponseDTO> generalException(GeneralException ex,WebRequest re){
        final  ResponseDTO responseDTO = ResponseDTO.builder()
                .map(ex.getValue())
                .code(ex.getCode())
                .message(ex.getMessage())
                .build();
        log.error("[FlowerExceptionControlHandler.GeneralException: {}]", ex.getMessage());
        return  new ResponseEntity<>(responseDTO,HttpStatus.OK);
    }

    @ExceptionHandler(value = {DataExistsException.class})
    protected ResponseEntity<ResponseDTO> dataExistsException(DataExistsException ex, WebRequest re){
        final  ResponseDTO responseDTO = ResponseDTO.builder()
                .code(Constant.RESPONSE.CODE.C409)
                .message(Constant.RESPONSE.MESSAGE.C409_FLOWER)
                .build();
        log.error("[FlowerExceptionControlHandler.DataExistsException: {}]", ex.getMessage());
        return  new ResponseEntity<>(responseDTO,HttpStatus.OK);
    }



}
