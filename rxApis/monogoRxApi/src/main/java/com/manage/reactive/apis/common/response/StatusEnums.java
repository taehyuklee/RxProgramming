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
    CONFLICT(409, "재원 충돌입니다.", HttpStatus.CONFLICT),
    INTERNAL_SERVER_ERROR(500, "서버 에러입니다.", HttpStatus.INTERNAL_SERVER_ERROR),


    //Custom 공통 에러 
    WRONG_TYPE(1000, "{}" + "변수 타입을 잘못 기입했습니다."),
    MISSING_REQUIRED(1001, "{} " + "필수값이 비어있습니다."),
    OUT_OF_RANGE_SIZE(1002, "{} " + "값의 길이가 범위 밖입니다."),
    ALREADY_EXIST(1003, "이미 등록되어 있는" + " {} " + "입니다."),
    VIOLATE_PATTERN(1004, "{} " + "형식이 맞지않습니다."),
    IN_USE_ERROR(1005, "사용중인" + " {} " + "입니다."),
    UPDATE_DATA_NOT_FOUND(1006, "수정대상이 존재하지 않습니다."),
    DELETE_DATA_NOT_FOUND(1007, "삭제대상이 존재하지 않습니다."),
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
