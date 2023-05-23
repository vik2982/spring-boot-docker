package com.va.spring.rest.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.va.spring.rest.model.FootballTeam;

@Repository
public interface FootballTeamRepository extends JpaRepository<FootballTeam, Long> {

  FootballTeam findByName(String name);

  List<FootballTeam> findByOrderByStadiumCapacityAsc();

  List<FootballTeam> findByOrderByStadiumCapacityDesc();
}
