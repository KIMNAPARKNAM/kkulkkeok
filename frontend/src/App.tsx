import React from "react";
import "./App.css";
import { Route, Routes } from "react-router-dom";
import LoginPage from "./pages/LoginPage";
import MainPage from "./pages/MainPage";
import SignUpPage from "./pages/SignUpPage";
import ProfilePage from "./pages/ProfilePage";
import CreatePostPage from "./pages/CreatePostPage";

function App() {
  return (
    <Routes>
      <Route index element={<MainPage />} />
      <Route path="/login" element={<LoginPage />} />
      <Route path="/signup" element={<SignUpPage />} />
      <Route path="/profile" element={<ProfilePage />} />
      <Route path="/new" element={<CreatePostPage />} />
    </Routes>
  );
}

export default App;
