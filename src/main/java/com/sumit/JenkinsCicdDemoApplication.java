package com.sumit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class JenkinsCicdDemoApplication extends SpringBootServletInitializer{

	@GetMapping("/greetings/{name}")
    public String greetings(@PathVariable String name) {
       name= name.split(" ")[0];
        return "Hello " + name + " Congratulations you have successfully completed Jenkins CI/CD demo !";
    }
	@Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        // Point to your main Spring Boot class
        return builder.sources(JenkinsCicdDemoApplication.class);
    }
	public static void main(String[] args) {
		SpringApplication.run(JenkinsCicdDemoApplication.class, args);
	}
}
