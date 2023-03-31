package com.bilgeadam.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
@AllArgsConstructor
@NoArgsConstructor
@Getter
public enum ErrorType {
    USER_NOT_FOUND(2004,"Kitap adı bulunamadı",HttpStatus.NOT_FOUND),
    BAD_REQUEST(4000,"Geçersiz istek veya parametre",HttpStatus.BAD_REQUEST),
    ERROR_INVALID_TOKEN(3000,"Geçersiz token bilgisi",HttpStatus.UNAUTHORIZED),
    ERROR(9000,"Beklenmeyen bir hata oluştu. Lütfen daha sonra tekrar deneyiniz...",HttpStatus.INTERNAL_SERVER_ERROR);
    int code;
    String message;
    HttpStatus httpStatus;

}
