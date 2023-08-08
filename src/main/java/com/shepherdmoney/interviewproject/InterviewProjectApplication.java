package com.shepherdmoney.interviewproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/*
questions:
when I run localhost:8080, I get
{
  "_links" : {
    "users" : {
      "href" : "http://localhost:8080/users{?page,size,sort}",
      "templated" : true
    },
    "creditCards" : {
      "href" : "http://localhost:8080/creditCards{?page,size,sort}",
      "templated" : true
    },
    "profile" : {
      "href" : "http://localhost:8080/profile"
    }
  }
}
why are "users" and "creditCards" in plural?
 */

@SpringBootApplication
@EnableJpaRepositories
public class InterviewProjectApplication {
    public static void main(String[] args) {
        SpringApplication.run(InterviewProjectApplication.class, args);
    }
}
