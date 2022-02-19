import { Box, Container, Stack, Typography } from "@mui/material";
import { makeStyles } from "@mui/styles";
import React from "react";
import DoneIcon from "@mui/icons-material/Done";

const useStyles = makeStyles({
  seat: {
    width: 20,
    height: 20,
    textAlign: "center",
    alignItems: "baseline",
    borderRadius: 5,
    paddingY: 3,
    boxShadow: "0 2px 5px 2px rgba(255, 255, 255, .3)",
    background: "#FEFBF3",
  },
  selectedSeat: {
    width: 20,
    height: 20,
    textAlign: "center",
    alignItems: "baseline",
    borderRadius: 5,
    boxShadow: "0 2px 5px 2px rgba(73, 255, 0, .3)",
    background: "#548CFF",
  },
});

const SeatIndicator = () => {
  const classes = useStyles();
  return (
    <Container sx={{ my: 1 }}>
      <Stack direction="row" spacing={3} alignItems="center">
        <Box className={classes.seat}></Box>
        <Typography variant="subtitle1">Available</Typography>
        <Box className={classes.selectedSeat}></Box>
        <Typography variant="subtitle1">Sold</Typography>
      </Stack>
    </Container>
  );
};

export default SeatIndicator;
