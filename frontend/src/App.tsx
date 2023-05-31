import React from 'react';
import './App.css';
import TaskList from './components/TaskList';
import SearchBar from './components/SearchBar';

function App() {
  return (
    <div className="App-header">
        <div className="mt-10 grid grid-cols-1 gap-x-6 gap-y-8 sm:grid-cols-1">
          <SearchBar />

          <TaskList />
        </div>
    </div>
  );
}

export default App;
