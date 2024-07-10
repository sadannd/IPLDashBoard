import './App.scss';
import MatchPage from './pages/MatchPage';
import TeamPage from './pages/TeamPage';
import HomePage from './pages/Homepage';

import {HashRouter as Router,Route, Routes, Switch} from 'react-router-dom';
function App() {
  return (
    <div className="App">
      <Router>
        <switch>

        <Routes>
        <Route path="/teams/:teamName/matches/:year" element={<MatchPage />} />

        <Route path="/teams/:teamName" element={<TeamPage />} />

        <Route path="/" element={<HomePage />} />

        </Routes>
        </switch>
        
        
      </Router>
      
    </div>
  );
}

export default App;
