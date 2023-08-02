package com.serivce.testservice.controller;

import org.springframework.web.bind.annotation.*;

import lombok.extern.slf4j.Slf4j;

//Controller
@RestController //화면을 갖고있지 않고, RestAPI 형태로만 신호가 왔다 갔다 할거임. 
@RequestMapping("/test-service") //사용자로부터 요청되는 URI값을 지정해두는 것.
@Slf4j // log로 바로 출력해서 볼수 있도록 한다.
public class TestController {
    @GetMapping("/welcome")
    public String welcome(){
        return "Welcome to the Test service";
    }

    @GetMapping("/fail") //first-request header를 받는다.
    public String error(@RequestParam(required = false) String message){
        System.out.println(message);
        log.info("Fail");
        throw new IllegalArgumentException("You're request is fail");
    }

    @GetMapping("/ok") //first-request header를 받는다.
    public String ok(@RequestParam(required = false) String message){
        System.out.println(message);
        log.info("Success");
        return "You're request is success.";
    }

}
//Controller와 RestContorller의 차이는 RequestBody와 ResponseBody를 구현하느냐 아니냐의 차이점이다.