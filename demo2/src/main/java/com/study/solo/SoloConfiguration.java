package com.study.solo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SoloConfiguration {

    @Bean
    public Solo solo()
    {
        Solo solo = new Solo();
        solo.setHowLong(5);
        solo.setName("js");
        return solo;
    }
    //원래는 설정하는 대상이 되는 클래스는 다른 프로젝트에 있는 것이 흔함.
    //다른 프로젝트에 있는 어떤 라이브러리에 대한 자동 설정 파일을 또 다른 프로젝트에서 만드는것이 보통
}