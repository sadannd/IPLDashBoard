package io.java.ipldashboard.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import io.java.ipldashboard.model.Match;

public interface MatchRepository extends CrudRepository<Match,Long>{
    
List<Match> getByTeam1OrTeam2OrderByDateDesc(String teamName1,String teamName2);

@Query("select m from Match m where (m.team1=:teamName or m.team2=:teamName) and m.date between :dateStart and :dateEnd order by date desc")
List<Match> getMatchesByTeamBetweenDates(
    @Param("teamName") String teamName,
    @Param("dateStart") LocalDate dateStart, 
    @Param("dateEnd") LocalDate dateEnd);
    
}
