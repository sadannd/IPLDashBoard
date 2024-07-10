package io.java.ipldashboard.data;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import io.java.ipldashboard.meta.Meta;
import io.java.ipldashboard.model.Match;
import io.java.ipldashboard.model.Team;
import jakarta.persistence.EntityManager;

@Component
public class JobCompletionNotificationListener implements JobExecutionListener {

  private static final Logger log = LoggerFactory.getLogger(JobCompletionNotificationListener.class);

  private final EntityManager em;

  public JobCompletionNotificationListener(EntityManager em) {
    this.em = em;
  }

  @Override
  @Transactional
  public void afterJob(@SuppressWarnings("null") JobExecution jobExecution) {
    if(jobExecution.getStatus() == BatchStatus.COMPLETED) {
      log.info("!!! JOB FINISHED! Time to verify the results");

      //em.createQuery("SELECT team1,team2 FROM match", (rs,row)->"Team1"+rs.getString(1)).forEach(str->System.out.println(str));
      Map<String, Team> teamData = new HashMap<>();
           
      
      List<Match> matches = em.createQuery("SELECT m FROM Match m",Match.class).getResultList();
      for (Match match : matches) {
        System.out.println("id is:-"+match.getId());
        System.out.println("date is:-"+match.getDate());
        System.out.println("venue is:-"+match.getVenue());
        System.out.println("team 1 is:-"+match.getTeam1());
        System.out.println("team 2 is:-"+match.getTeam2());

    }     
      em.createQuery("select m.team1, count(*) from Match m group by m.team1", Object[].class)
        .getResultList()
        .stream()
        .map(e -> new Team((String) e[0], (long) e[1]))
        .forEach(team -> teamData.put(team.getTeamName(), team));
    

        em.createQuery("select m.team2, count(*) from Match m group by m.team2", Object[].class)
        .getResultList()
        .stream()
        .forEach(e -> {
            Team team = teamData.get((String) e[0]);
            if(team!=null)
              {
                team.setTotalMatches(team.getTotalMatches() + (long) e[1]);
              }
        });

        em.createQuery("select m.matchWinner, count(*) from Match m group by m.matchWinner", Object[].class)
        .getResultList()
        .stream()
        .forEach(e -> {
            Team team = teamData.get((String) e[0]);
            if (team != null) 
              {team.setTotalWins((long) e[1]);}
        });

        //teamData.values().forEach(team -> em.merge(team));
        Meta.setTeamData(teamData);
        teamData.values().forEach(team -> {
          Team existingTeam = em.find(Team.class, team.getId());
          if (existingTeam != null) {
              em.detach(existingTeam);
              em.merge(team);
              System.out.println("merge");

          } else {
            System.out.println("persists");
              em.persist(team);
          }
      });
            

      
        //teamData.values().forEach(team -> em.persist(team));

        /*for (Team team : teamData.values()) {
          Team existingTeam = em.find(Team.class, team.getId());
          if (existingTeam == null) {
              em.persist(team);
          } else {
              em.merge(team);
          }
    }*/
    //teamData.values().forEach(team -> System.out.println(team));

  }
}
}