import * as React from "react";
import Button from "@mui/material/Button";
import TextField from "@mui/material/TextField";
import Box from "@mui/material/Box";
import Typography from "@mui/material/Typography";
import Container from "@mui/material/Container";
import { Link, useNavigate } from "react-router-dom";
import axios, { HttpStatusCode } from "axios";
import { useCookies } from "react-cookie";

const LoginPage = () => {
  const navigate = useNavigate();
  const [cookies, setCookie] = useCookies(["authorization"]);
  const handleSubmit = async (event: React.FormEvent<HTMLFormElement>) => {
    event.preventDefault();
    const data = new FormData(event.currentTarget);
    try {
      const response = await axios.post(
        "http://localhost:8080/api/v1/users/login",
        {
          email: data.get("email"),
          password: data.get("password"),
        }
      );

      console.log(response.headers["authorization"]);

      if (response.status === HttpStatusCode.Ok) {
        const token = response.headers["authorization"].split(" ")[1];
        setCookie("authorization", token);
        navigate("/");
      }
    } catch (e) {
      console.log(e);
    }
  };

  return (
    <Container component="main" maxWidth="xs">
      <Box
        sx={{
          marginTop: 20,
          display: "flex",
          flexDirection: "column",
          alignItems: "center",
        }}
      >
        <Typography component="h1" variant="h5">
          Log In
        </Typography>
        <Box component="form" onSubmit={handleSubmit} noValidate sx={{ mt: 1 }}>
          <TextField
            margin="normal"
            required
            fullWidth
            id="email"
            label="Email Address"
            name="email"
            autoComplete="email"
            autoFocus
          />
          <TextField
            margin="normal"
            required
            fullWidth
            name="password"
            label="Password"
            type="password"
            id="password"
            autoComplete="current-password"
          />

          <Button
            type="submit"
            fullWidth
            variant="contained"
            color="info"
            sx={{ mt: 2, mr: "10px" }}
          >
            로그인
          </Button>
          <Link to="/signup">
            <Button type="button" fullWidth variant="contained" sx={{ mt: 2 }}>
              회원가입
            </Button>
          </Link>
        </Box>
      </Box>
    </Container>
  );
};

export default LoginPage;
