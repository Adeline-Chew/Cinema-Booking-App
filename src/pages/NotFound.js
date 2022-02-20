import { Button, Container, Stack, Typography } from "@mui/material";
import React from "react";
import { useNavigate } from "react-router-dom";

const NotFound = () => {
  const navigate = useNavigate();
  return (
    <Container>
      <Stack spacing={3}>
        <Typography variant="overline">
          Uh Oh, you have entered the restricted area
        </Typography>
        <Button variant="contained" onClick={() => navigate("/index")}>
          Click here to go back
        </Button>
      </Stack>
    </Container>
  );
};

export default NotFound;
