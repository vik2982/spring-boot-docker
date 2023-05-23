package com.va.spring.rest.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.text.SimpleDateFormat;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.va.spring.rest.model.FootballTeam;
import com.va.spring.rest.repository.FootballTeamRepository;

@RunWith(SpringRunner.class)
@WebMvcTest(FootballTeamController.class)
public class FootballTeamControllerTest {

  @Autowired
  private MockMvc mockMvc;
  @MockBean
  FootballTeamRepository footballTeamRepository;

  private static final String TEAM = "spurs";

  @Test
  public void getTeam() throws Exception {
    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    FootballTeam footballTeam = new FootballTeam(TEAM, "london", "daniel levy", 30000,
        "Premier League", 22, dateFormat.parse("20/07/1901"));
    when(footballTeamRepository.findByName(TEAM)).thenReturn(footballTeam);
    ObjectMapper mapper = new ObjectMapper();
    String footballTeamJson = mapper.writeValueAsString(footballTeam);
    mockMvc.perform(get("/{team}", TEAM)).andExpect(status().isOk())
        .andExpect(content().json(footballTeamJson));
  }

}
