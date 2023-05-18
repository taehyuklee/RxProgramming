package com.manage.reactive.apis.common.response;

import org.springframework.http.HttpStatus;

import lombok.Getter;

@Getter
public enum StatusEnums {
    
    //RestFul API 표준 에러
    SUCCESS("S", 200, "Beast-정상처리 되었습니다.", HttpStatus.OK),
    BAD_REQUEST("F", 400, "Beast-잘못된 요청입니다.", HttpStatus.BAD_REQUEST),
    UNAUTHORIZE_ERROR("F",401, "Beast-인증에러입니다.", HttpStatus.UNAUTHORIZED),
    FORBIDDEN("F",403, "Beast-권한이 없습니다.", HttpStatus.FORBIDDEN),
    DATA_NOT_FOUND("F",404, "Beast-해당 데이터를 찾을수 없습니다.", HttpStatus.NOT_FOUND),
    INTERNAL_SERVER_ERROR("F",500, "Beast-서버 에러입니다.", HttpStatus.INTERNAL_SERVER_ERROR),

    //BEAST Custom 공통 에러
    MISSING_VALUE_ERROR("F",1000, "Beast-필수값이 누락되었습니다."),
    UPDATE_DATA_NOT_FOUND("F",1001, "Beast-수정대상이 존재하지 않습니다."),
    DELETE_DATA_NOT_FOUND("F",1002, "Beast-삭제대상이 존재하지 않습니다."),

    //BEAST Custom System 에러
    OUT_OF_FORM_ERROR("F",1003, "Beast-형식에 맞지 않는 파라미터가 있습니다."),
    WRONG_SYSTEM_ID("F",1004, "Beast-시스템 ID 형식이 맞지 않습니다."),
    ALREADY_EXIST_SYSTEM_ID("F",1005, "Beast-이미 등록되어 있는 시스템 ID입니다."),
    NOT_SUPPORTED_PROTOCOL_ERROR("F",1006, "Beast-지원하지 않는 프로토콜 입니다."),
    VIOLATE_URL_PATTERN_ERROR("F",1007, "Beast-URL 패턴에 위배됩니다."),
    SYSTEM_IN_USE("F",1008, "사용중인 시스템입니다."),
    
    //BEAST Custom 핸들러 에러
    ALREADY_EXIST_HANDLER_ID("F",1009, "Beast-이미 등록되어 있는 핸들러 ID입니다."),
    WRONG_HANDLER_ID("F",1010, "Beast-핸들러 ID 형식이 맞지 않습니다."),
    OUT_OF_FORM_CLASS_NAME("F",1011, "Beast-클래스명이 형식에 맞지 않습니다."),
    ALREADY_EXIST_CLASS_NAME("F",1012, "Beast-이미 등록되어 있는 클래스 명입니다."),
    FAIL_COMPILE_ERROR("F",1013, "Beast-핸들러 코드 컴파일에 실패했습니다."),
    HANDLER_IN_USE("F",1014, "사용중인 핸들러입니다."),

    //BEAST Custom API 에러
    ALREADY_EXIST_API_ID("F",1015, "Beast-이미 등록되어 있는 API ID입니다."),
    WRONG_API_ID("F",1016, "Beast-API ID 형식이 맞지 않습니다."),
    NOT_SUPPORTED_METHOD_ERROR("F",1017, "Beast-지원하지 않는 메소드가 있습니다."),
    ALREADY_EXIST_URI_IN("F",1018, "Beast-이미 등록되어 있는 URI IN입니다."),
    VIOLATE_URL_IN_PATTERN("F",1019, "Beast-URI IN 패턴 형식을 위배했습니다."),
    VIOLATE_URL_OUT_PATTERN("F",1020, "Beast-URI OUT 패턴 형식을 위배했습니다."),
    NOT_REGISTERED_REQ_HANDLER("F",1021, "Beast-요청 핸들러중 등록되지 않는 핸들러가 존재합니다."),
    NOT_REGISTERED_RES_HANDLER("F",1022, "Beast-응답 핸들러중 등록되지 않는 핸들러가 존재합니다."),
    NOT_REGISTERED_ERR_HANDLER("F",1023, "Beast-등록되지 않은 에러 핸들러입니다."),
    OUT_OF_RANGE_TIME_OUT("F",1024, "Beast-타임아웃 범위 밖으로 설정했습니다."),
    API_IN_USE("F",1025, "Beast-사용중인 API입니다."),

    //BEAST Custom 서비스 에러
    ALREADY_EXIST_SERVICE_ID("F",1009, "Beast-이미 등록되어 있는 서비스 ID입니다."),
    WRONG_SERVICE_ID("F",1010, "Beast-서비스 ID 형식이 맞지 않습니다."),
    OUT_OF_FORM_DATE("F",1011, "Beast-날짜형식이 잘못 기입되었습니다."),
    WRONG_SETTING_PERIOD("F",1012, "Beast-날짜형식이 잘못 기입되었습니다."),
    NOT_EXIST_API_FOR_AUTH("F",1013, "Beast-존재하지 않는 API를 권한설정할수 없습니다."),
    VIOLATE_IP_PATTERN("F",1019, "Beast-허용, 차단 IP형식이 맞지 않습니다."),
    NOT_SETTING_API_AUTH("F",1020, "Beast-API 권한 설정을 하지 않았습니다."),
    ;

    //초기 enum type 생성자
    private final String returnCode;

    private final int code;

    private final String message;

    private HttpStatus status;

    StatusEnums(String returnCode, int code, String message) {
        this.returnCode = returnCode;
        this.code = code;
        this.message = message;
    }

    StatusEnums(String returnCode, int code, String message, HttpStatus status) {
        this.returnCode = returnCode;
        this.code = code;
        this.message = message;
        this.status = status;
    }
}
