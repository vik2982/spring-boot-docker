package com.va.spring.rest.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.va.spring.rest.exception.ErrorResponse;
import com.va.spring.rest.exception.FootballTeamException;
import com.va.spring.rest.exception.FootballTeamNotFoundException;
import com.va.spring.rest.model.FootballTeam;
import com.va.spring.rest.repository.FootballTeamRepository;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.web.server.ResponseStatusException;

@RestController
// @RequestMapping("/footballApi")
public class FootballTeamController {

  private static final Logger logger = LoggerFactory.getLogger(FootballTeamController.class);
  private static final String ASC = "asc";
  private static final String DESC = "desc";

  @Autowired
  FootballTeamRepository footballTeamRepository;

  @GetMapping
  public ResponseEntity<List<FootballTeam>> allTeams() {
    return new ResponseEntity<List<FootballTeam>>(footballTeamRepository.findAll(), HttpStatus.OK);
  }

  @Operation(summary = "Get a team by its name")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Found the team",
          content = {@Content(mediaType = "application/json",
              schema = @Schema(implementation = FootballTeam.class))}),
      @ApiResponse(responseCode = "404", description = "Team not found",
          content = {@Content(mediaType = "application/json",
              schema = @Schema(implementation = ErrorResponse.class))})})
  @GetMapping("/{team}")
  public ResponseEntity<FootballTeam> getTeam(@PathVariable("team") String team)
      throws FootballTeamNotFoundException {

    FootballTeam returnTeam = footballTeamRepository.findByName(team);

    if (returnTeam != null) {
      return new ResponseEntity<FootballTeam>(returnTeam, HttpStatus.OK);
    }

    logger.error("Team not found for provided path variable");
    throw new FootballTeamNotFoundException("No Team found");

    // throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Team Not Found",
    // new Exception("some random ex"));

  }

  @GetMapping("/capacity")
  public ResponseEntity<List<FootballTeam>> sortByCapacity(@RequestParam("sort") String sort)
      throws FootballTeamException {

    if (sort.equals(ASC)) {
      return new ResponseEntity<List<FootballTeam>>(
          footballTeamRepository.findByOrderByStadiumCapacityAsc(), HttpStatus.OK);
    } else if (sort.equals(DESC)) {
      return new ResponseEntity<List<FootballTeam>>(
          footballTeamRepository.findByOrderByStadiumCapacityDesc(), HttpStatus.OK);
    }

    logger.error("Invalid query param");
    throw new FootballTeamException("Invalid query param");
  }

  @PostMapping("/create")
  public ResponseEntity<List<FootballTeam>> createTeam(@RequestBody FootballTeam team)
      throws FootballTeamException {

    List<FootballTeam> footballTeams = footballTeamRepository.findAll();
    if (footballTeams.contains(team)) {
      logger.error("Cannot create team as it already exists");
      throw new FootballTeamException("Team already exists");
    }

    footballTeamRepository.save(team);
    footballTeamRepository.flush();

    return new ResponseEntity<List<FootballTeam>>(footballTeamRepository.findAll(), HttpStatus.OK);
    // return ResponseEntity.ok(footballTeamRepository.findAll());
  }

}
