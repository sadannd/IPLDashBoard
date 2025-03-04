import React from 'react'
import {Link} from 'react-router-dom'
import './MatchDetailCard.scss';

const MatchDetailCard = ({match,teamName}) => {
    if(!match) return null;
    const otherTeam=teamName===match.team1 ? match.team2 : match.team1;
    const otherTeamRoute=`/teams/${otherTeam}`;
    const isMatchWon=teamName===match.matchWinner;
    return (
    <div className={isMatchWon? 'MatchDetailCard won-card':'MatchDetailCard lost-card'}>
      <div>
      <span className='vs'>vs</span>
      <h1>vs <Link to={otherTeamRoute}>{otherTeam}</Link></h1>
      <h2 className='match-date'>{match.date}</h2>
      <h2 className='match-venue'>at {match.venue}</h2>
      <h2 className='match-result'>{match.matchWinner} won by {match.resultMargin} {match.result}</h2>
      </div>
      
      <div className='additional-details'>
      <h3>First Innings</h3>
      <p>{match.team1}</p>

      <h3>Second Innings</h3>
      <p>{match.team2}</p>

      <h3>Man of the match</h3>
      <p>{match.playerOfMatch}</p>

      <h3>Umpires</h3>
      <p>{match.umpire1}, {match.umpire2}</p>

      </div>
      
    </div>
  )
}

export default MatchDetailCard