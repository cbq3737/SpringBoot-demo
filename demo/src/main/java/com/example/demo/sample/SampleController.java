package com.example.demo.sample;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SampleController {
   
    @Autowired
    private SampleService sampleService;

    @GetMapping("/hello!")
    public @ResponseBody String hello()
    {
        return "hello " + sampleService.getName();
    }
    @PostMapping("/user/create") //SampleControllerTest.java
    public User create(@RequestBody User user)
    {
        return user;
    }
} //SampleController생성한 후 -> command+N키 눌러서 Test파일생성->test폴더 아래의 sample폴더->SampleControllerTest.java