package vn.chohoa.flower.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DataEmptyException extends RuntimeException{

    String code;
    String message;

}
