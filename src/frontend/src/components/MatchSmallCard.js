import React from 'react'
import {Link} from 'react-router-dom'
import './MatchSmallCard.scss'
const MatchSmallCard = ({match,teamName}) => {
  const otherTeam = teamName===match.team1 ? match.team2 : match.team1;
  const otherTeamRoute=`/teams/${otherTeam}`;
  const isMatchWon=teamName===match.matchWinner;

  return (
    <div className={isMatchWon? 'MatchSmallCard won-card':'MatchSmallCard lost-card'}>
    <span className='vs'>vs</span>
<h1><Link to={otherTeamRoute}>{otherTeam}</Link></h1>
<p className='match-result'>{match.matchWinner} won by {match.resultMargin} {match.result}</p>
      
    </div>
  )
}

export default MatchSmallCard