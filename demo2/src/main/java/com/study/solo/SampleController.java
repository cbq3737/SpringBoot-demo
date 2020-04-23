package com.study.solo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SampleController {
   
    @Autowired
    private SampleService sampleService;

    @GetMapping("/hello!")
    public String hello()
    {
        return "hello " + sampleService.getName();
    }

} //SampleController생성한 후 -> command+N키 눌러서 Test파일생성->test폴더 아래의 sample폴