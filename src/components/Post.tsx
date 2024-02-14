import {
  Avatar,
  Card,
  CardActions,
  CardContent,
  CardHeader,
  CardMedia,
  Collapse,
  IconButton,
  IconButtonProps,
  Typography,
} from "@mui/material";
import React, { useState } from "react";
import styled from "styled-components";
import FavoriteIcon from "@mui/icons-material/Favorite";
import ShareIcon from "@mui/icons-material/Share";
import ExpandMoreIcon from "@mui/icons-material/ExpandMore";
import MoreVertIcon from "@mui/icons-material/MoreVert";

interface IPost {
  postId: number;
  title: String;
  contents: string;
  categoryName: String;
  nickname: String;
}

const Post = (post: IPost & any, index: number) => {
  console.log(index);
  interface ExpandMoreProps extends IconButtonProps {
    expand: boolean;
  }

  const ExpandMore = styled((props: ExpandMoreProps) => {
    const { expand, ...other } = props;
    return <IconButton {...other} />;
  })(({ theme, expand }) => ({
    transform: !expand ? "rotate(0deg)" : "rotate(180deg)",
    marginLeft: "auto",
  }));

  const [expanded, setExpanded] = useState(false);

  const handleExpandClick = () => {
    setExpanded(!expanded);
  };

  return (
    <Card sx={{ maxWidth: 600, marginTop: "50px" }}>
      <CardHeader
        avatar={<Avatar aria-label="recipe">R</Avatar>}
        action={
          <IconButton aria-label="settings">
            <MoreVertIcon />
          </IconButton>
        }
        title={post.post.title}
        subheader="2024-02-14"
      />
      <CardMedia
        component="img"
        height="300"
        image="https://i.ytimg.com/vi/idjWwBesv3U/maxresdefault.jpg"
        alt="Paella dish"
      />
      <CardContent>
        <Typography paragraph>재료</Typography>
        <Typography variant="body1" color="text.secondary">
          {post.post.contents}
        </Typography>
      </CardContent>
      <CardActions disableSpacing>
        <IconButton aria-label="add to favorites">
          <FavoriteIcon />
        </IconButton>
        <IconButton aria-label="share">
          <ShareIcon />
        </IconButton>
        <ExpandMore
          expand={expanded}
          onClick={handleExpandClick}
          aria-expanded={expanded}
          aria-label="show more"
        >
          <ExpandMoreIcon />
        </ExpandMore>
      </CardActions>
      <Collapse in={expanded} timeout="auto" unmountOnExit>
        <CardContent>
          <Typography paragraph>레시피</Typography>
          <Typography paragraph>
            양파와 파를 채썰어 준비합니다. 청양고추는 얇게 슬라이스 합니다. 팬에
            물과 떡을 넣고 약한 불에서 조리합니다. 떡이 조리될 때까지 기다린 후,
            양파와 파를 넣고 함께 볶아줍니다. 고추장, 고춧가루, 설탕, 소금을
            넣고 고루 섞어줍니다. 물을 넣고 조금 더 끓입니다. 불을 끄고 치즈를
            뿌립니다. 뚜껑을 덮고 치즈가 녹을 때까지 기다립니다. 치즈가 녹으면
            완성입니다.
          </Typography>
        </CardContent>
      </Collapse>
    </Card>
  );
};

export default Post;
