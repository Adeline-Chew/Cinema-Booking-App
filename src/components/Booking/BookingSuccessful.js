import { Paper, Typography, Grid, Box } from "@mui/material";
import React from "react";
import { parseCurrency, parseDateTime } from "../../utils/helpers";

const BookingSuccessful = ({ booking }) => {
  return (
    <Paper sx={{ bgcolor: "#F0F0F0", color: "#000" }}>
      <Typography variant="overline">
        {booking.firstName}, your booking is successful!
      </Typography>
      <Grid container sx={{ mt: 2 }}>
        <Grid item md={3}>
          <Typography variant="subtitle2">Your name</Typography>
        </Grid>
        <Grid item md={1}>
          :
        </Grid>
        <Grid item md={8}>
          {booking.firstName} {booking.lastName}
        </Grid>

        <Grid item md={3}>
          <Typography variant="subtitle2">Your email</Typography>
        </Grid>
        <Grid item md={1}>
          :
        </Grid>
        <Grid item md={8}>
          {booking.email}
        </Grid>

        <Grid item md={3}>
          <Typography variant="subtitle2">Movie name</Typography>
        </Grid>
        <Grid item md={1}>
          :
        </Grid>
        <Grid item md={8}>
          {booking.movie.name}
        </Grid>

        <Grid item md={3}>
          <Typography variant="subtitle2">Showtime</Typography>
        </Grid>
        <Grid item md={1}>
          :
        </Grid>
        <Grid item md={8}>
          {parseDateTime(booking.movie.showtime)}
        </Grid>

        <Grid item md={3}>
          <Typography variant="subtitle2">Hall</Typography>
        </Grid>
        <Grid item md={1}>
          :
        </Grid>
        <Grid item md={8}>
          {booking.movie.hall}
        </Grid>

        <Grid item md={3}>
          <Typography variant="subtitle2">Seat NO.</Typography>
        </Grid>
        <Grid item md={1}>
          :
        </Grid>
        <Grid item md={8}>
          {booking.seats
            .map((elem) => `${elem.rowName}${elem.colNumber}`)
            .join(", ")}
        </Grid>

        <Grid item md={3}>
          <Typography variant="subtitle2">Total amount paid</Typography>
        </Grid>
        <Grid item md={1}>
          :
        </Grid>
        <Grid item md={8}>
          $ {parseCurrency(booking.total)}
        </Grid>
      </Grid>
      <Box sx={{ my: 2 }} />
      <Typography variant="body2">
        A confirmation email has been sent to your email address&nbsp;
        {booking.email}.
      </Typography>
    </Paper>
  );
};

export default BookingSuccessful;
