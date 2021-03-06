// src-main-java : 자바소스 코드
// src-main-resources: 자바코드를 제외한 모든것.
// src-main-test: 테스트 코드
// resources 디렉토리를 root라고 생각하고 자바 application에서 resources하위 항목들 모두 참조 가능
//spring bean과 자바의 일반 객체와의 차이점은 없고 스프링 컨테이너에 의해 만들어진 객체를 스프링 빈이라고 부를뿐
// 스프링 빈으로 등록하는 가장 쉬운 방법은 클래스 선언부 위에 Component Annotation을 사용하는 것이다.  @Component가 붙은 클래스는 스프링 빈 객체로 등록이 되어 객체 생성/삭제를 스프링에서 관리할 수 있다.
//@Bean어노테이션과 @Component어노테이션 둘다 Spring(IOC) Container에 Bean을 등록하도록 하는 메타데이터를 기입하는 어노테이션인데 둘의 용도가 다름
//@Bean어노테이션의 경우 개발자가 직접 제어가 불가능한 외부 라이브러리등을 Bean으로 만들려할 때 사용된다.(ex:ArrayList같은)
//@Component 어노테이션의 경우 개발자가 직접 작성한 Class를 Bean으로 등록하기 위한 어노테이션이다. 

//오토와이어링은 자동으로 스프링 빈 객체를 특정 참조변수에 매핑해주는 것을 뜻하며 @Autowired라는 어노테이션을 사용한다.
// @Component를 사용한 Bean의 의존성 주입은 @AutoWired 어노테이션을 이용하여 할 수 있다. 

//@SpringBootApplication = @SpringBootConfiguration + @ComponentScan + @EnableAutoConfiguration
//@SpringBootApplication은 Bean을 2번 등록하는데 처음에 ComponentScan으로 등록하고, 그후 EnableAutoConfiguration으로 추가적인 Bean들을 읽어 등록함.
//@ComponentScan : @Component 어노테이션 가진 Bean(반복적인 작업을 효율적으로 하기위해 사용하여 java의 데이터와 기능(메소드)으로 이루어진 클래스를 의미,spring의 Dao임)들을 스캔해서 등록, ex: Configuration,Repository,Controller...
//@EnableAutoConfiguration: 결국 Configuration임 즉 Bean을 등록하는 자바 설정 파일,그래서 이 어노테이션에 의해 수많은 자동 설정들이 조건에 따라 적용이 되어 Bean들이 생성됨.
//프로젝트 > 패키지 > 클래스
//DEMO:프로젝트명, src.main.java는 공통 PATH, 패키지이름은 com.xx..., ex.xx... 그래서 다른 프로젝트에 잇는 패키지도 갖고와서 쓸 수 잇음.

//spring boot는 spring과 다르게 내부 webserver가 있기에 외부의 tomcat서버에 배포가 될 수 있어야한다.
//spring boot maven plugin에 관한 내용,스프링 부트를 개발한 뒤 배포 or 도커 이미지로 만들때는 jar패키지로 패키징하여 jar파일을 실행하는 방법이 유용하게 사용됨.
//jar파일 실행하는 방법: mvn clean(target 하위를 비우는것) -> mvn packaging ? mvn install-> 그럼 target 폴더 아래에 java -jar "jar파일이름"으로 생김. cmd에서 이용할때 target폴더로 이동하여 실행
//처음에 jar를 만들려고 하면 Java HOME not found였는데 환경변수의 시스템변수의 PATH가 java home이 안잡혀서 있어서 그랬음.그래서 JAVA_HOME 변수 만들어주고 그다음에 어차피 bin으로 path잡혀야하므로 생성한 %JAVA_HOME이용하여 기존에 잡혀있던 bin으로 잡아주면 됌.자바설치한 후에 실행이 안되면 이방법이 많이 사용됨.
//아래 터미널 창은 power shell보다는 cmd창이 더 호환이 많이 된다.

//Application은 3가지 타입이있는데 None/Reactive/Servlet인데 Spring MVC가 있으면 기본적으로 Servlet타입으로 돌고, Spring WebFlux가있으면 Reactive타입으로 돈다.
//Application을 실행하고 난 후,뭔가를 실행하고 싶을때->AppRunner.java파일로 가보자

//스프링부트의 profile 설정 : 프로파일은 Bean들의 묶음이다.(테스트 환경에서는 어떤 묶음의 Bean들을 쓰겠다.실제 배포에서는 어떤 묶음의 Bean들을 쓰겠다는 것이다.)->BaseConfiguration,TestConfiguration
//특정할 프로파일에서만 특정한 Bean을 등록하고 싶다거나,애플리케이션의 동작이 특정 프로파일 일 때 Bean설정을 다르게 하고 싶다거나 할 때 사용
//application.properties를 이용할경우 => application-{profile}.properties형식으로 파일 생성하면 됨.

//스프링 부트 로깅(통신 소프트웨어 간의 메시지를 기록하는 로그를 하는 행위) 원리:실제로 로깅을 하는 것이 아니라,로거 API들을 추상화 해놓은 인터페이스 
//로깅이란: 프로그램 개발중이나 완료 후 발생할 수 있는 오류에 대해 디버깅하거나 운영중인 프로그램 상태를 모니터링 하기위해 필요한 정보를 기록하는것.
//데이터 로거: 일련의 데이터를 로그파일로 기록하는 소프트웨어 
//SLF4J(Simple Logging Facade for Java): 백엔드  Logger framework의 facade pattern(클래스 라이브러리 같은 어떤 소프트웨어의 다른 커다란 코드 부분에 대한 간략화된 인터페이스를 제공하는 객체, 즉 그래서 퍼사드 객체라고 부름, 복잡한 코드들을 간단한 통합 인터페이스(클래스?)를 제공해주는 역할을 함.)(즉 자바에서 사용하는 logging 프로그램을 제공하는 프레임워크)
//SLF4J는 추상 로깅 프레임워크이기때문에 단독으로 사용되지않는다. 퍼사드 객체가 생성이 될려면 복잡한 서브 클래스들이 존재해야 하기 때문에 
//스프링 부트는 기본적으로 commons logging을 사용함. 그렇지만 보통 SLF4j 사용. 
//logger Facade를 사용하면 로거를 바꾸어 낄 수 있다는 장점 -> LogBack(SLF4J와 같은 backend logger framework)이 SLF4J의 구현체이다.
//로그의 기본 포맷 : 날짜 | 시간 | 로그레벨 | PID | 쓰레드 이름 | 풀 패키지 경로 & 클래스 이름 | 메세지
//컬로 로그 -> application.properties


//테스트 파일이란?? @SpringBootTest라는 어노테이션이 붙어있는데, @SpringBootTest 어노테이션은 @SpringBootApplication 어노테이션이 붙어있는 스프링 메인 애플리케이션을 찾아간다.그리고 메인에서부터 시작하는 모든 Bean스캔을 한다. 그 Bean들을 스캔한 후 테스트용 context를 만들면서 모든 Bean들을 등록.그 후 MockBean을 찾아서 그 Bean만 교체해준다.->sample폴더->SampleController.java, SampleService ->맨 아래탭에 있는 Test에서 돌아가는걸 실험가능함.->SampleController의 맵핑과 test의 맵핑이 서로잘 맞아야함.아니면 main함수에 잇는걸 인식해서 받아옴.
//RANDOM-PORT/DEFINED-PORT 타입:테스트용 rest template이나 웹 클라이언트를 사용해야하며 RANDOM-PORT는 랜덤한 포트를 배정받고,DEFINED-PORT는 포트를 정해줄 수 있다. -> SampleControllerTest.java -> localhost에러뜸.다 안됨 ㅅㅂ


//spring-boot-Devtools란? 스프링 부트가 제공하는 optical한 tool이다.pom.xml에 의존성 추가를 해줘야한다.의존성을 추가해주면,기본적으로 제공되는 properties들이 바뀌는 것이 있다.대표적으로 cash관련 항목들을 false로 바꿔줌.
//re-start:코드가 바뀌면 그때 그때 바로 스프링 애플리케이션이 재실행됨. 클래스 로더를 2개 사용하는데, base classloader:라이브러리들,우리가 바뀌지 않는 의존성을 읽어들이는 class loader, restart classloader:애플리케이션을 읽어들이는 클래스 로더로 코드 수정 후 빌드만 해주면 서버가 재 시작됨.

//spring 웹 MVC- HttpMessageConverters: 스프링부트는 기본설정에 의해 기본적으로 web mvc를 바로 사용할 수 있다.(자동 설정파일이 설정되었기 떄문??), sping MVC의 일부분이다.  HTTP 요청 본문을 객체로 변경하거나, 객체를 HTTP 응답 본문으로 변경할 때 사용한다. 그래서 @RequestBody/@ResponseBody 와 함께 사용된다.
//데이터가 요청으로 들어올 때(request) 요청의 본문에 그 데이터가 들어있을때, 그걸 객체로 받고 싶으면 파라미터로 @RequestBody를 붙혀 객체로 받을 수 있다.(스프링이 알아서 바꿔줌.)                                                                                              
//사용하는 HttpMessageConverters는 여러가지가 있고,어떤 요청을 받고 어떤 응답을 보내야하는지에 따라 사용하는 HttpMessageConverters가 달라진다.
//컴포지션 타입(객체 안에 여러가지 프로퍼티를 가질 수 있는 타입)일 경우 기본적으로 jsonMessageConverter가 사용된다. 컴포지션 타입이 아닌 string 객체나 int객체이면 StringMessageConverter가 사용된다.
//클래스에 @RestController가 붙어있으면 public뒤에 @ResponseBody를 생략할 수 있다. 하지만 일반 @Controller를 사용할 시 @ResponseBody를 써줘야한다. -> UserControllerTest -> 에러뜸


//ContentNegotiatingViewResolver : ViewResolver중의 하나로, 들어오는 요청의 accpet header(부라우저 또는 클라이언트가 어떠한 타입의 본문을 응답을 원한다고 서버에 알려주는 것)에 따라 응답이 달라진다. 어떠한 요청이 들어오면 그 요청의 응답을 만들 수 있는 모든 view를 찾아내고, 최종적으로 accept header의 viw타입과 비교하여 선택한다. accept header가 없는 요청인 경우 format이라는 매개변수를 사용한다.




package com.example.demo;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;
import org.springframework.test.context.junit4.SpringRunner;
import static org.assertj.core.api.Assertions.*;

@RunWith(SpringRunner.class)//RunWith가 없다고 에러떳는데 아래 PROBLEMS에서 의존성 더해줄 수 있다.
@SpringBootTest //서버 테스트용
public class DemoApplicationTests {

	@Autowired
	Environment environment;	//모든 property들은 기본적으로 Environment를 통해서 확인가능.따라서 Environment를 주입받아 getProperty로 프로퍼티를 받아옴.

	@Test
	void contextLoads() {
		//assertThat(environment.getProperty("my.name")).isEqualTo("[prod]beomkyu");//실행할때나 jar파일을 만들때 my.name의 값과 같지않으면 오류가 뜸.
	}

}
 