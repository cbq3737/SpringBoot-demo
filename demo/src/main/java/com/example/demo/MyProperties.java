package com.example.demo;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component //Component를 붙혀줌으로써 Bean으로 등록 -> 여기 있는 값들을 사용하기 위해서 ->AppRunner
@ConfigurationProperties("my")
//만약 여기에 @Validated 어노테이션을 붙혀주면 프로퍼티값 유효성 검사를 할 수 있고, 그러면 변수에 @NotEmpty,@Size등 붙여서 유효성 체크를 해줄수도 있음.
public class MyProperties { //여기서 properties파일에 있던 name,age,fullName을 받을 변수를 정의하고,getter,setter를 만들어준다.
    //이렇게 하면 이 ConfigurationProperties어노테이션을 처리하는 애가 멤버 변수에 값을 바인딩 할 수 있는 상태로 만들 수 있게 한 것이고,아직 사용은 하지 못함.()
    //이것을 사용하려면,main에 @EnableConfigurationProperties 어노테이션을 사용해 사용할 프로퍼티 클래스 값으로 줘서 사용해야한다.그러면 만들어준 프로퍼티 클래스도 Bean으로 등록해주고,@ConfigurationProperties어노테이션도 처리해줌. ->DemoApplication.java
    
    
    private String name;

    private int age;

    private String fullname;

    public String getName()
    {
        return name;
    }
    public void setName(String name)
    {
        this.name =name; 
    }

    public int getAge()
    {
        return age;
    }

    public void setAge(int age)
    {
        this.age = age;
    }

    public String getfullName()
    {
        return fullname;
    }
    public void setfullName(String fullname)
    {
        this.fullname = fullname;
    }
}