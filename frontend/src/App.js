import React from 'react';
import { BrowserRouter as Router, Route, Switch } from 'react-router-dom';
import Login from './Components/Login';
import Signup from './Components/Signup';
import MyCourses from './Components/MyCourses';
import ProtectedRoute from './Components/ProtectedRoute';

function App() {
  return (
    <Router>
      <Switch>
        <Route exact path="/login" component={Login} />
        <Route exact path="/signup" component={Signup} />
        <ProtectedRoute exact path="/my-courses" component={MyCourses} />
        {/* Các routes khác */}
      </Switch>
    </Router>
  );
}

export default App;
