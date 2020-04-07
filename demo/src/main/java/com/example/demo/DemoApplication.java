// src-main-java : 자바소스 코드
// src-main-resources: 자바코드를 제외한 모든것.
// src-main-test: 테스트 코드
// resources 디렉토리를 root라고 생각하고 자바 application에서 resources하위 항목들 모두 참조 가능
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
package com.example.demo;

import org.apache.catalina.connector.Connector;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication // 가장 최상위 패키지 하위에 두는 것을 추천
@RestController
public class DemoApplication {
	@GetMapping("/hello")
	public String hello() {
		return "hello Spring!";
	}
	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);

	}
	@Bean
	public ServletWebServerFactory serverFactory() {
		TomcatServletWebServerFactory tomcat = new TomcatServletWebServerFactory();
		tomcat.addAdditionalTomcatConnectors(createStandardConnector()); // 톰캣에 Connector 추가
		return tomcat;
	}

	private Connector createStandardConnector() {
		Connector connector = new Connector("org.apache.coyote.http11.Http11NioProtocol");
		connector.setPort(8080);//포트 설정: https랑 달라야함. 
		return connector;//http포트인 8080과  https포트인 8090으로 모두 접속 가능
	}
}
