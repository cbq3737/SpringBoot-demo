package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
//@component가 Bean으로 만드는 행위인듯? ->ㅇㅇ 내가 작성한 class일때만 

@Component //Application을 실행하고 난 후, 뭔가를 실행하고 싶을때 사용한다.
@Order(1) // 만일 ApplicationRunner가 여러개인 경우, @Order로 순서를 지정해줄 수 있다.
public class AppRunner implements ApplicationRunner{
    
    // @Override //ApplicationRunner는 ApplicationArguments라는 타입으로 메서드를 만들어준다.
    // public void run(ApplicationArguments args) throws Exception
    // {
    //     System.out.println("foo: "+args.containsOption("foo"));
    //     System.out.println("bar: "+args.containsOption("bar"));
    // }



    // @Value("${my.name}")//값을 참조하는 방법
    // String name;
    // @Value("${my.age}")
    // int age;
    // @Value("${my.fullname}")
    // String kim;
    // @Override
    // public void run(ApplicationArguments args) throws Exception
    // {
    //     System.out.println(name);
    //     System.out.println(age);
    //     System.out.println(kim);
    // }

    ////MyProperties에서 옴,    
    @Autowired//참조 매핑해주고 getter를 이용해 사용하면 된다.
    MyProperties myProperties;

    @Override
    public void run(ApplicationArguments ages) throws Exception{
        System.out.println(myProperties.getName());
        System.out.println(myProperties.getAge());
        System.out.println(myProperties.getfullName());
    } 
    //만일 이렇게 properties가 애플리케이션안에 있지않고, jar파일이나 다른 곳에 있는 경우에 클래스 위에 @component로 만들 수 없다면 -> DemoApplication
} 