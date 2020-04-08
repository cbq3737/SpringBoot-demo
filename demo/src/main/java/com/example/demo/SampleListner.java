package com.example.demo;

//import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.boot.context.event.ApplicationStartingEvent;
import org.springframework.context.ApplicationListener;
//import org.springframework.stereotype.Component;
//Event리스너를 만들때는 ApplicationListener<타입>를 implements해서 만든다.
//이때 타입을 ApplicationStratingEvent로 주면 이 이벤트가 발생하면(애플리케이션이 시작될 때)해당 리스너가 실행된다.
//이때 리스너가 Bean이면,등록되어잇는 Bean중에 해당하는 이벤트에 대한 리스너는 알아서 실행해줌.
//그런데 application Context가 만들어진 다음에 발생하는 이벤트들은 그 이벤트들의 리스너가 Bean이면 알아서 호출해주는데 만일 application context가 만들어지기 전에 발생한 이벤트(ex:ApplicationStratingEvent같은 이벤트)는 Bean으로 등록한다하더라도 리스너가 작동하지않는다.
//그래서 그때는 직접 메인의 SpringApplication의 addListeners를 사용해서 리스너를 등록해준다. -> demoApplication

public class SampleListner implements ApplicationListener<ApplicationStartingEvent>{
    @Override
    public void onApplicationEvent(ApplicationStartingEvent applicationstartingevent)
    {
        System.out.println("=====================");
        System.out.println("Application starting");
        System.out.println("=====================");
    }
}

// @Component //이 어노테이션 없으면 반드시 main에서 listener를 직접 등록해줘야 됌.
// public class SampleListner implements ApplicationListener<ApplicationStartedEvent>{ //이렇게 started인 경우는 application context가 만들어진 이후이기에 Bean으로만 등록해주면 리스너 동작함,굳이 main에 add를 안해줘도 된다는 의미.
//     @Override
//     public void onApplicationEvent(ApplicationStartedEvent applicationStartedevent)
//     {
//         System.out.println("=====================");
//         System.out.println("Application started");
//         System.out.println("=====================");
//     }
// }