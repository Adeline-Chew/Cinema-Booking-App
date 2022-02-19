import { Paper, Typography } from "@mui/material";
import React from "react";

const BookingFailed = () => {
  return (
    <Paper sx={{ bgcolor: "#F0F0F0", color: "#000" }}>
      <Typography variant="body1" sx={{ pb: 2 }}>
        Sorry, your booking is unsuccessful, please try again later!
      </Typography>
    </Paper>
  );
};

export default BookingFailed;
