package com.serivce.goodService.controller;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@RestController //화면을 갖고있지 않고, RestAPI 형태로만 신호가 왔다 갔다 할거임. 
@RequestMapping("/test") //사용자로부터 요청되는 URI값을 지정해두는 것.
@Slf4j // log로 바로 출력해서 볼수 있도록 한다.
public class SecondServiceController {
    @GetMapping("/welcome")
    public String welcome(HttpServletRequest request, HttpServletResponse response){

        Enumeration<String> headerNm = request.getHeaderNames();
        while (headerNm.hasMoreElements()) {
            String headerName = headerNm.nextElement();
            String headerValue = request.getHeader(headerName);
            
            // 각 헤더를 응답에 추가
            response.setHeader(headerName, headerValue);
        }

        return "Jinsol's Test 중입니다";
    }

}
