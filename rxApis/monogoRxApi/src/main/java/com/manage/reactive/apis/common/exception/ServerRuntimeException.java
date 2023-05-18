package com.manage.reactive.apis.common.exception;

import com.manage.reactive.apis.common.response.StatusEnums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ServerRuntimeException extends RuntimeException {

    private StatusEnums statusEnums;


}
