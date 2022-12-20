import * as React from 'react';
import './App.css';
import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import LoginScreen from './screens/LoginScreen';
import HomeScreen from './screens/HomeScreen';
import AnnouncementsScreen from './screens/AnnouncementsScreen'
import ApplicationScreen from './screens/ApplicationScreen'
import ChecklistScreen from './screens/Student/ChecklistScreen'
import ContactsScreen from './screens/ContactsScreen'
import CourseTransferScreen from './screens/CourseTransferScreen'
import EvaluationsScreen from './screens/EvaluationsScreen'
import ForumScreen from './screens/ForumScreen'
import LearningAgreementScreen from './screens/Student/LearningAgreementScreen'
import MessageScreen from './screens/MessageScreen'
import PreApprovalScreen from './screens/PreApprovalScreen'
import ToDoListScreen from './screens/ToDoListScreen'
import WishListScreen from './screens/Student/WishListScreen'
import PlacementScreen from './screens/Placements'
import UploadTranscript from './screens/ISO/UploadTranscript';
import UploadScoreTable from './screens/ISO/UploadScoreTable';
import CourseProposals from './screens/CourseCoord/CourseProposals';
import Deadlines from './screens/AdmCoordinator/Deadlines';
import CoordinatorsScreen from './screens/DepCoordinator/CoordinatorsDepCoordinator';
import TransferrableCoursesScreen from './screens/FacAdmCoordinator/TransferrableCourses';

function App() {
  return (
    <Router>
      <Routes>
        <Route index element={<LoginScreen />} />
        <Route path="login" element={<LoginScreen />} />
        <Route path="home" element={<HomeScreen />}>
          <Route index element={<ToDoListScreen />} />
          <Route path="todo" element={<ToDoListScreen />} />
          <Route path="announcements" element={<AnnouncementsScreen />} />
          <Route path="application" element={<ApplicationScreen />} />
          <Route path="checklist" element={<ChecklistScreen />} />
          <Route path="contacts" element={<ContactsScreen />} />
          <Route path="courseTransfer" element={<CourseTransferScreen />} />
          <Route path="placements" element={<PlacementScreen />} />
          <Route path="evaluations" element={<EvaluationsScreen />} />
          <Route path="forum" element={<ForumScreen />} />
          <Route path="learningAgreement" element={<LearningAgreementScreen />} />
          <Route path="message" element={<MessageScreen />} />
          <Route path="preapproval" element={<PreApprovalScreen />} />
          <Route path="wishlist" element={<WishListScreen />} />
          <Route path="scoreTable" element={<UploadScoreTable />} />
          <Route path="transcripts" element={<UploadTranscript />} />
          <Route path="courseProposals" element={<CourseProposals />} />
          <Route path="deadlines" element={<Deadlines />} />
          <Route path="coordinators" element={<CoordinatorsScreen />} />
          <Route path="transferrableCourses" element={<TransferrableCoursesScreen />} />
        </Route>
      </Routes>
    </Router>
  );
}

export default App;
