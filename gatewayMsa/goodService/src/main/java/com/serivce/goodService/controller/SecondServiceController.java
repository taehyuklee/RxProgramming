package com.serivce.goodService.controller;

import java.util.*;
import java.util.Map.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.util.StringBuilders;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@RestController //화면을 갖고있지 않고, RestAPI 형태로만 신호가 왔다 갔다 할거임. 
@RequestMapping("/test") //사용자로부터 요청되는 URI값을 지정해두는 것.
@Slf4j // log로 바로 출력해서 볼수 있도록 한다.
public class SecondServiceController {
    @GetMapping("/welcome")
    public String welcome(HttpServletRequest request, HttpServletResponse response) throws JsonProcessingException{

        log.info("null");
        Map<String, String> responseHeader = new HashMap<>();

        Enumeration<String> headerNm = request.getHeaderNames();
        while (headerNm.hasMoreElements()) {
            String headerName = headerNm.nextElement();
            String headerValue = request.getHeader(headerName);
            responseHeader.put(headerName, headerValue);

            // 각 헤더를 응답에 추가
            response.setHeader(headerName, headerValue);
        }

        // JSON 문자열로 변환


        return  makeJson(responseHeader);
    }

    String makeJson(Map<String, String> responseHeader ){
        StringBuilder sb = new StringBuilder();
        sb.append("[request에서 response에 추가된 header들 목록들] \n\n");
        for(Entry<String, String> entry : responseHeader.entrySet()){
            sb.append(entry.getKey()).append(" : ").append(entry.getValue()).append(",").append("\n");
        }
       
        return sb.toString();
    }

}
