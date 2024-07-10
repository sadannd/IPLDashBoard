package io.java.ipldashboard.controller;

import org.springframework.web.bind.annotation.RestController;

import io.java.ipldashboard.model.Team;
import io.java.ipldashboard.model.Team1;
import io.java.ipldashboard.meta.Meta;
import io.java.ipldashboard.model.Match;

import io.java.ipldashboard.repository.MatchRepository;
import io.java.ipldashboard.repository.TeamRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@CrossOrigin
public class TeamController {
    @Autowired
    private TeamRepository teamRepository;
    @Autowired
    private MatchRepository matchRepository;
    

    public TeamController(TeamRepository teamRepository,MatchRepository matchRepository) {
        this.teamRepository = teamRepository;
        this.matchRepository=matchRepository;
    }


    @GetMapping("/team")
    public List<Team> getAllTeam()
    {
    Map<String, Team> teamData = Meta.getTeamData();
        
    List<Team> teamList = new ArrayList<>(teamData.values());        
    return teamList;
        
    }

    @GetMapping("/team/{teamName}")
    public Team1  getTeam(@PathVariable String teamName)
    {
        Map<String, Team> teamData=Meta.getTeamData();
        Team team=teamData.get(teamName);
        List<Match> teams=matchRepository.getByTeam1OrTeam2OrderByDateDesc(teamName, teamName);
        teams=teams.subList(0, 4);
        
        return new Team1(team.getId(),team.getTeamName(),team.getTotalMatches(),team.getTotalWins(),teams);       
        
    }

    @GetMapping("/team/{teamName}/matches")
    public List<Match> getMatchesForTeam(@PathVariable String teamName,@RequestParam int year) {

        LocalDate starDate=LocalDate.of(year, 1, 1);
        LocalDate endDate=LocalDate.of(year+1, 1, 1);
        return matchRepository.getMatchesByTeamBetweenDates(teamName,starDate,endDate);
        
    }
    
}
