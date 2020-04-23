package com.example.demo.sample;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

//RestTemplate이란?? 스프링에서 제공하는 http통신에 유용하게 쓸 수 있는 템플릿이며,HTTP서버와의 통신을 단순화하고 RESTful원칙을 지킨다.
//REST:웹에 존재하는 모든자원(이미지,동영상,DB자원)에 고유한 URI를 부여해 활용하는것으로,자원을 정의하고 자원에 대한 주소를 지정하는 방법론을 의미한다.

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)//SpringBootTest어노테이션에는 webEnvironment라는 속성이 있음.기본값은 MOCK임.RANDOM_PORT는 호스트가 사용하지 않는 랜덤 포트를 사용하겠다는 의미다.
//@AutoConfigureMockMvc //MockMvc를 사용하기 위한 어노테이션 
//@WebMvcTest(SampleController.class)
public class SampleControllerTest {
       //MOCK타입 테스트 : Mock타입은 서블릿 컨테이너(톰캣같은 것)을 테스트용으로 띄우지않고 Mock을 해서 서블릿을 Mocking한 것을 띄워준다. *mockup : 실제품 만들기전 프로토타입을 만든것.요청을 수행하고 응답을 만들어내느 Servlet API모조(mock)객체를 사용
        //DISTPATCHER서블릿은 만들어지긴하는데 MOCKing이 되어 distpatchServlet애 요청을 보내는것처럼 실험을 할 수 있게된다. 그래서 이때 mockup 된 서블릿과 상호작요을 하려면 MockMVC라는 클라이언트를 사용해야한다.
        // @Autowired
        // private MockMvc mockMvc; //-> SpringBootTest.WebEnvironment.MOCK

        // @Test
        // public void hello() throws Exception{
        //     mockMvc.perform(MockMvcRequestBuilders.get("/hello!")) //hello라는 url로 ->sampleController와 맞는지 봐야한다.
        //     .andExpect(MockMvcResultMatchers.status().isOk()).//위 요청에 따라 결과가 status는 200이며
        //     andExpect(MockMvcResultMatchers.content().string("hello beomkyu")).//response body에 "hello beomkyu"가 있는지 검증 -> SampleController의 mapping과 잘 맞아야한다.
        //     andDo(MockMvcResultHandlers.print());
        // }   //perform(get())은 get요청을 보내는것이고, andExpect는 ~하길 기대한것 즉 응답을 검증하는 역할,마지막으로 andDo는 ~을 해달라는것이다.()
        


        //TestRestTemplate을 주입 받음. -> 안됨.
        // @Autowired
        // TestRestTemplate testRestTemplate; // -> SpringBootTest.WebEnvironment.RANDOM_PORT
        
        // @MockBean //만일 테스트를 SampleService까지 가지말고,Samplecontroller까지만 테스트 하고싶을때는 @MockBean어노테이션을 사용해 Service를 만들어서 콘트롤 할 수 있음.
        // SampleService mockSampleService;//이러면 application context안에 들어있는 이 SampleService Bean을 @MockBean으로 교체해준다. 그래서 실질적으로 SampleService는 MockSampleService를 사용하게 된다.

        // @Test
        // public void hello() throws Exception
        // {
        //     when(mockSampleService.getName()).thenReturn("bk");//위 MockBean을 사용한다면 이렇게 코드를 작성해 줄 수 있다.

        //     String result = testRestTemplate.getForObject("/hello!", String.class);//테스트를 작성해주는데,url을 주고, 원하는 바디 타입을 준 후,assertThat으로 확인해준다.
        //     //assertThat(result).isEqualTo("hello beomkyu");//이걸로 동등한 string이 반환
        //     assertThat(result).isEqualTo("hello bk");//MockBean으로 인해 service가 bk로 반환이 되는걸로 바뀌면서 테스트를 돌리면 hello bk가 되야되는데 localhost가 안맞다고 안됨.
        // }


        //WebClient는 Rest client중에 하나이다.
        //기존에 사용하던 Rest client는 syncronous(동기)이다. 즉 요청 하나 보내고,끝날때까지 기다린후, 요청을 보낼 수 있는데,WebClient는 asynchronos(비동기)이다.요청을 보내고, 기다리지않고, 응답이 오면 call back이 오는데, 그때 call back을 실행할 수 있는 것이다.
        //WebClient를 사용하려면 spring web flux의존성이 들어와 있어야한다.
        // @Autowired
        // WebTestClient webTestClient;

        // @MockBean
        // SampleService mockSampleService;

        // @Test
        // public void hello() throws Exception
        // {
        //     when(mockSampleService.getName()).thenReturn("bk");
        //     webTestClient.get().uri("/hello!").exchange() //get요청을 보내고
        //             .expectStatus().isOk()  //status와 body를 체크해준다.
        //             .expectBody(String.class).isEqualTo("hello bk");
        // }
        


        //테스트는 모든 Bean을 등록하기때문에 테스트 할 Bean만 등록하고 싶을 때 사용하는 것이 슬라이스 테스트다.
        //즉 레이어 별로 짤라서 사용, @WebMvcTest어노테이션으로 테스해볼 수 있고 웹 레이어 만 Bean으로 등록해준다는 특징있다.
        //따라서 테스트시 의존성이 깨지기 때문에 위의 방법처럼 @MockBean으로 받아와서 사용한다.
        // @Autowired
        // MockMvc mockMvc;

        // @MockBean
        // SampleService mockSampleService;

        // @Test
        // public void hello() throws Exception
        // {
        //     when(mockSampleService.getName()).thenReturn("bk");
        //     mockMvc.perform(MockMvcRequestBuilders.get("/hello!")).andExpect(MockMvcResultMatchers.content().string("hello bk"));
        // }
    }
