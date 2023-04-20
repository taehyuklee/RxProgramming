package com.serivce.goodService.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

//Controller
@RestController //화면을 갖고있지 않고, RestAPI 형태로만 신호가 왔다 갔다 할거임. 
@RequestMapping("/first-service") //사용자로부터 요청되는 URI값을 지정해두는 것.
@Slf4j // log로 바로 출력해서 볼수 있도록 한다.
public class FirstServiceController {
    @GetMapping("/welcome")
    public String welcome(){
        return "Welcome to the First service";
    }

    @GetMapping("/message") //first-request header를 받는다.
    public String message(@RequestHeader("first-request") String header){
        //@RequestHeader header값을 받아 갈 것이다.
        System.out.println(header);
        log.info(header);
        return "Hello world in First Service.";
    }

}
//Controller와 RestContorller의 차이는 RequestBody와 ResponseBody를 구현하느냐 아니냐의 차이점이다.