import React, { useEffect, useState } from "react";
import styled from "styled-components";
import Post from "../components/Post";
import PrimarySearchAppBar from "../components/PrimarySearchAppBar";
import { Fab } from "@mui/material";
import { Add, PlusOne } from "@mui/icons-material";
import { Link, useNavigate } from "react-router-dom";
import axios, { HttpStatusCode } from "axios";

const MainPage = () => {
  const navigate = useNavigate();
  const [posts, setPosts] = useState<any>([]);

  useEffect(() => {
    const fetchData = async () => {
      try {
        const response = await axios.get("http://localhost:8080/api/v1/posts");

        if (response.status === HttpStatusCode.Ok) {
          setPosts(response.data["data"]);
        }
      } catch (e) {
        console.log(e);
      }
    };
    fetchData();
  }, []);

  return (
    <Container>
      <PrimarySearchAppBar />

      <Main>
        {posts.map((post: any, index: number) => (
          <Post post={post} index={index} />
        ))}
      </Main>
      <Link to="/new">
        <Fab
          color="primary"
          aria-label="add"
          sx={{ position: "fixed", bottom: 30, right: 30 }}
        >
          <Add />
        </Fab>
      </Link>
    </Container>
  );
};

const Container = styled.div``;
const Main = styled.div`
  display: flex;
  flex-direction: column;
  min-height: 100vh;
  padding-top: 30px;
  align-items: center;
  overflow-y: auto; /* 세로 방향 오버플로우 발생 시 스크롤바 표시 */
`;
export default MainPage;
