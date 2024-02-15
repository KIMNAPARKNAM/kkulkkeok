import * as React from "react";
import Button from "@mui/material/Button";
import TextField from "@mui/material/TextField";
import Box from "@mui/material/Box";
import Typography from "@mui/material/Typography";
import Container from "@mui/material/Container";
import { Link, useNavigate } from "react-router-dom";
import {
  FormControl,
  InputLabel,
  MenuItem,
  Select,
  SelectChangeEvent,
} from "@mui/material";
import axios, { HttpStatusCode } from "axios";

const SignUpPage = () => {
  const navigate = useNavigate();
  const [gender, setGender] = React.useState("");

  const handleSubmit = async (event: React.FormEvent<HTMLFormElement>) => {
    event.preventDefault();
    const data = new FormData(event.currentTarget);

    try {
      const response = await axios.post(
        "http://localhost:8080/api/v1/users/signup",
        {
          email: data.get("email"),
          gender: data.get("gender"),
          nickname: data.get("nickname"),
          username: data.get("username"),
          password: data.get("password"),
        }
      );

      if (response.status === HttpStatusCode.Created) {
        navigate("/");
      }
    } catch (e) {
      console.log(e);
    }
  };

  return (
    <Container component="main" style={{ width: "25%" }}>
      <Box
        sx={{
          marginTop: 20,
          display: "flex",
          flexDirection: "column",
          alignItems: "center",
        }}
      >
        <Typography component="h1" variant="h5">
          Sign Up
        </Typography>
        <Box component="form" onSubmit={handleSubmit} noValidate sx={{ mt: 1 }}>
          <TextField
            margin="normal"
            required
            fullWidth
            name="username"
            label="이름"
            type="text"
            id="username"
          />

          <TextField
            margin="normal"
            required
            fullWidth
            name="nickname"
            label="닉네임"
            type="text"
            id="nickname"
          />
          <FormControl fullWidth>
            <InputLabel>성별</InputLabel>
            <Select
              name="gender"
              value={gender}
              label="성별"
              onChange={(event: SelectChangeEvent) =>
                setGender(event.target.value as string)
              }
            >
              <MenuItem value={"MALE"}>남</MenuItem>
              <MenuItem value={"FEMALE"}>여</MenuItem>
            </Select>
          </FormControl>
          <Box display={"flex"}>
            <TextField
              margin="normal"
              required
              fullWidth
              id="email"
              label="Email"
              name="email"
            />
            <Button
              type="submit"
              fullWidth
              variant="contained"
              color="info"
              sx={{
                width: "30%",
                mt: 2,
                ml: "10px",
                fontSize: "14px",
              }}
            >
              중복 확인
            </Button>
          </Box>

          <TextField
            margin="normal"
            required
            fullWidth
            name="password"
            label="Password"
            type="password"
            id="password"
          />
          <TextField
            margin="normal"
            required
            fullWidth
            name="passwordConfirm"
            label="password 확인"
            type="password"
            id="passwordConfirm"
          />

          <Button type="submit" fullWidth variant="contained" sx={{ mt: 2 }}>
            회원가입
          </Button>
        </Box>
      </Box>
    </Container>
  );
};

export default SignUpPage;
