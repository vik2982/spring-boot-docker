package com.va.spring.rest.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class FootballTeam {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  private String name;

  private String city;

  private String owner;

  private int stadiumCapacity;

  private String competition;

  private int numberOfPlayers;

  @JsonFormat(pattern = "dd/MM/yyyy")
  private Date dateOfCreation;

  public FootballTeam(String name, String city, String owner, int stadiumCapacity,
      String competition, int numberOfPlayers, Date dateOfCreation) {
    this.name = name;
    this.city = city;
    this.owner = owner;
    this.stadiumCapacity = stadiumCapacity;
    this.competition = competition;
    this.numberOfPlayers = numberOfPlayers;
    this.dateOfCreation = dateOfCreation;
  }

  public boolean equals(Object footballTeam) {
    FootballTeam team = (FootballTeam) footballTeam;
    return name.equalsIgnoreCase(team.getName());
  }

  public int hashCode() {
    return name.toUpperCase().hashCode();
  }

}
