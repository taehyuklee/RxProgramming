package com.manage.reactive.apis.common.response;

import org.springframework.http.HttpStatus;

import lombok.Getter;

@Getter
public enum StatusEnums {
    
    //RestFul API 표준 에러
    SUCCESS(200, "정상처리 되었습니다.", HttpStatus.OK),
    BAD_REQUEST( 400, "잘못된 요청입니다.", HttpStatus.BAD_REQUEST),
    UNAUTHORIZE_ERROR(401, "인증에러입니다.", HttpStatus.UNAUTHORIZED),
    FORBIDDEN(403, "권한이 없습니다.", HttpStatus.FORBIDDEN),
    DATA_NOT_FOUND(404, "해당 데이터를 찾을수 없습니다.", HttpStatus.NOT_FOUND),
    INTERNAL_SERVER_ERROR(500, "서버 에러입니다.", HttpStatus.INTERNAL_SERVER_ERROR),
    ;

    //초기 enum type 생성자
    private final int code;

    private final String message;

    private HttpStatus status;

    StatusEnums(int code, String message) {
        this.code = code;
        this.message = message;
    }

    StatusEnums(int code, String message, HttpStatus status) {
        this.code = code;
        this.message = message;
        this.status = status;
    }
}
