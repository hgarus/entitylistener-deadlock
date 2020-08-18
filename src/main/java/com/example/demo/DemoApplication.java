package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.stereotype.Component;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Id;
import javax.persistence.PrePersist;

@SpringBootApplication
@EnableJpaAuditing
public class DemoApplication {


	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Component
	public static class MyBean { }

}


@Entity
@EntityListeners({MyEntityListener.class})
class MyEntity {

	@Id
	private Long id;
}

class MyEntityListener {

	private final DemoApplication.MyBean myBean;

	// It's my understanding that constructor injection on entity listeners is supported even though it's not
	// JPA compliant changing this to setter Injection still results in a deadlock
	public MyEntityListener(DemoApplication.MyBean myBean) {
		this.myBean = myBean;
	}

	@PrePersist
	public void prePersist(MyEntity myEntity) {
		// needs to have one annotated method otherwise it's ignored
	}
}