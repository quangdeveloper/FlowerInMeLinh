package vn.chohoa.flower.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DataExistsException extends RuntimeException{

    private String code;

    private String message;


}
