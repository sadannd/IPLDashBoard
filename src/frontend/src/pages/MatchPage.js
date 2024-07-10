import {React,useEffect, useState} from 'react'
import MatchDetailCard from '../components/MatchDetailCard'
import { useParams } from 'react-router-dom';
import './MatchPage.scss'
import YearSelector from '../components/YearSelector';
const MatchPage = () => {
const[matches,setMatches]=useState({});
const { teamName,year } = useParams();

useEffect(
    () => {
     const fetchMatches = async () => {
      
        const response = await fetch(`${process.env.REACT_APP_API_ROOT_URL}/team/${teamName}/matches?year=${year}`);
        const data = await response.json();
        setMatches(data);
        console.log(data);


     };
     fetchMatches();
        

    },[teamName,year]
);

  return (
    <div className='MatchPage'>
      <div className='year-selector'>
        <h3>Select Year</h3>
        <YearSelector teamName={teamName}/>
      </div>
      <div>    
        <h2 className='page-heading'>{teamName} matches in {year}</h2>
          {Array.isArray(matches) && matches.length > 0 ? (
        matches.map(match => (
          <MatchDetailCard key={match.id} match={match} teamName={teamName} />
        ))
      ) : (
        <p>No matches available</p>
      )}

     </div>
     </div>

  )
}

export default MatchPage
