package com.va.spring.rest;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;

import com.va.spring.rest.model.FootballTeam;
import com.va.spring.rest.repository.FootballTeamRepository;

@SpringBootApplication
public class Application {

  @Autowired
  FootballTeamRepository footballTeamRepository;

  public static void main(String[] args) {
    SpringApplication.run(Application.class, args);
  }

  /*
   * Init db data. TODO init via sql - https://www.baeldung.com/spring-boot-h2-database
   */
  @EventListener
  public void onApplicationEvent(ContextRefreshedEvent event) throws ParseException {
    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    footballTeamRepository.save(new FootballTeam("spurs", "london", "daniel levy", 30000,
        "Premier League", 22, dateFormat.parse("20/07/1901")));
    footballTeamRepository.save(new FootballTeam("man utd", "manchester", "glazers", 60000,
        "Champions League", 18, dateFormat.parse("20/07/1955")));
    footballTeamRepository.save(new FootballTeam("chelsea", "london", "abramhovic", 40000,
        "Premier League", 30, dateFormat.parse("20/07/2004")));

  }
}
