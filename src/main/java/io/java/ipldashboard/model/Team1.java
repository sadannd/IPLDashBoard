package io.java.ipldashboard.model;

import java.util.List;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;



import jakarta.persistence.Id;

public class Team1 {

    private List<Match> matches;
    private long id;
    
    public Team1( long id, String teamName, long totalMatches, long totalWins,List<Match> matches) {
        this.matches = matches;
        this.id = id;
        this.teamName = teamName;
        this.totalMatches = totalMatches;
        this.totalWins = totalWins;
    }

    private String teamName;
    private long totalMatches;
    private long totalWins;
    

    public List<Match> getMatches() {
        return matches;
    }
    public void setMatches(List<Match> matches) {
        this.matches = matches;
    }
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public String getTeamName() {
        return teamName;
    }
    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }
    public long getTotalMatches() {
        return totalMatches;
    }
    public void setTotalMatches(long totalMatches) {
        this.totalMatches = totalMatches;
    }
    public long getTotalWins() {
        return totalWins;
    }
    public void setTotalWins(long totalWins) {
        this.totalWins = totalWins;
    }

    @Override
    public String toString() {
        return "Team [teamName=" + teamName + ", totalMatches=" + totalMatches + ", totalWins=" + totalWins + "]";
    }

}
