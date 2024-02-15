import {
  Box,
  Button,
  Card,
  CardMedia,
  InputLabel,
  TextField,
  Typography,
} from "@mui/material";
import React from "react";
import styled from "styled-components";

const ProfilePage = () => {
  return (
    <Container>
      <TopContainer>
        <TopLeftContainer>
          <Card>
            <CardMedia
              component="img"
              height="160px"
              image="https://wimg.mk.co.kr/meet/neds/2022/06/image_readtop_2022_556367_16561184505086722.jpeg"
              alt="Your image"
            />
          </Card>
        </TopLeftContainer>
        <TopRightContainer>
          <UserInfoWrapper>
            <InputLabel>이름</InputLabel>
            <Label>김나박남</Label>
          </UserInfoWrapper>
          <UserInfoWrapper>
            <InputLabel>성별</InputLabel>
            <Label>남</Label>
          </UserInfoWrapper>
          <UserInfoWrapper>
            <InputLabel>닉네임</InputLabel>
            <Label>Kkulkkeok</Label>
          </UserInfoWrapper>
        </TopRightContainer>
      </TopContainer>
      <BottomContainer>
        <AbountMeContainer>
          <h1>About Me</h1>
          <TextField rows={8} multiline sx={{ width: "600px" }}></TextField>
        </AbountMeContainer>
        <Button type="button" fullWidth variant="contained" sx={{ mt: 10 }}>
          포스팅 등록
        </Button>
      </BottomContainer>
    </Container>
  );
};

const Container = styled.div`
  padding-top: 100px;
  justify-content: center;
  alignitems: center;
  height: 70vh;
`;

const TopContainer = styled.div`
  display: flex;
  align-items: center;
  justify-content: center;
  height: 40%;
`;
const TopLeftContainer = styled.div``;
const TopRightContainer = styled.div`
  padding-left: 50px;
`;
const UserInfoWrapper = styled.div`
  display: flex;
  margin-top: 20px;
  margin-bottom: 30px;
  justify-content: space-between;
  width: 200px;
`;

const BottomContainer = styled.div`
  margin: 30px auto;
  width: 50%;
  height: 60%;
`;

const AbountMeContainer = styled.div`
  text-align: center;
`;

const Label = styled.span`
  font-weight: bold;
`;

export default ProfilePage;
