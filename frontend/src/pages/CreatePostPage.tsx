import {
  Box,
  Button,
  Card,
  CardMedia,
  TextField,
  Typography,
} from "@mui/material";
import React, { useState } from "react";
import styled from "styled-components";
import axios, { HttpStatusCode } from "axios";
import { useNavigate } from "react-router-dom";
import { useCookies } from "react-cookie";

const CreatePostPage = () => {
  const navigate = useNavigate();
  const [cookies, setCookie] = useCookies(["authorization"]);
  const [image, setImage] = useState<any>(null);

  const handleFileChange = (event: any) => {
    const selectedFile = event.target.files[0];
    if (selectedFile) {
      const reader = new FileReader();
      reader.onloadend = () => {
        setImage(reader.result);
      };
      reader.readAsDataURL(selectedFile);
    }
  };

  const handleSubmit = async (event: React.FormEvent<HTMLFormElement>) => {
    event.preventDefault();
    const data = new FormData(event.currentTarget);
    try {
      const response = await axios.post(
        "http://localhost:8080/api/v1/posts",
        {
          title: data.get("title"),
          categoryName: data.get("categoryName"),
          contents: data.get("contents"),
        },
        {
          headers: { Authorization: `Bearer  ${cookies["authorization"]}` },
        }
      );

      if (response.status === HttpStatusCode.Ok) {
        navigate("/");
      }
    } catch (e) {
      console.log(e);
    }
  };

  return (
    <Container>
      <Main>
        <Box
          sx={{
            marginTop: 10,
            display: "flex",
            flexDirection: "column",
            alignItems: "center",
          }}
        >
          <Typography component="h1" variant="h5">
            New Posting
          </Typography>
          <Box
            component="form"
            onSubmit={handleSubmit}
            noValidate
            sx={{ mt: 1 }}
          >
            <TextField
              margin="normal"
              required
              fullWidth
              name="title"
              label="Title"
              type="text"
              id="title"
              autoFocus
            />

            <TextField
              margin="normal"
              required
              fullWidth
              name="categoryName"
              label="카테고리"
              type="text"
              id="categoryName"
            />

            <Card>
              <input type="file" accept="image/*" onChange={handleFileChange} />
              {image && (
                <CardMedia
                  component="img"
                  height="300px"
                  image={image}
                  alt="Your image"
                />
              )}
            </Card>

            <TextField
              label="포스트 내용 작성"
              name="contents"
              variant="outlined"
              fullWidth
              multiline
              rows={5}
              style={{ height: "100px" }}
              sx={{ mt: "20px" }}
            />

            <Button type="submit" fullWidth variant="contained" sx={{ mt: 10 }}>
              포스팅 등록
            </Button>
          </Box>
        </Box>
      </Main>
    </Container>
  );
};

const Container = styled.div``;
const Main = styled.div``;

export default CreatePostPage;
