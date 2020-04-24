package com.example.demo;

import org.apache.catalina.connector.Connector;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
//import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;




@SpringBootApplication // 가장 최상위 패키지 하위에 두는 것을 추천
@EnableConfigurationProperties(MyProperties.class) //스프링 어플리케이션 컨텍스트를 만들 때 자동으로 설정하는 기능을 켠다.이걸로 인해 Bean으로 등록됨.만들어준 MyProperties.java파일을 Bean으로 등록해주면 됨.->MyProperties.java
@RestController
public class DemoApplication {
	@GetMapping("/hello")
	public String hello() {
		return "hello Spring!";
	}


	//AppRunner에서 옴
	// @ConfigirationProperties("server") //이렇게 사용하고
	// @Bean //이런식으로 빈을 만들어야한다.
	// public ServerProperties ServerProperties()
	// {
	// 	return new ServerProperties();
	// }

//-----------------------------------Springboot실행하는 코드,3가지의 다른 방법--------------------------------------------------------------
	// public static void main(String[] args) { //static메서드: 커스터마이징 할 수 없다. 
	// 	SpringApplication.run(DemoApplication.class, args);
		
	// }

	public static void main(String[] args) { //이런식으로 위와 코드는 같지만 분해하여 커스터마이징 가능하게 해준다. 기존 초기는 위 코드였지만 Listener를 더하는 등 커스터마이징으로 인해 이 코드를 사용  
		SpringApplication app = new SpringApplication(DemoApplication.class);
		app.addListeners(new SampleListner()); //직접 application context가 만들어지기 전 이므로 리스너를 직접 추가해준다.
		app.run(args);	
	}

	// public static void main(String[] args) //SpringApplicationBuilder() 이 방법 역시 커스터마이징이 가능하다.
	// {
	// 	new SpringApplicationBuilder().sources(DemoApplication.class).run(args);
	// }
//--------------------------------------------------------------------------------------------------------------------------	
	@Bean //새로운 connector 생성
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
