import { Paper, Typography, Grid, Box } from "@mui/material";
import React, { useEffect } from "react";
import { parseCurrency, parseDateTime } from "../../utils/helpers";
import QRGenerator from "../QRGenerator";
import emailjs from "@emailjs/browser";
// import GeneratePdf from "../Pdf/GeneratePdf";
// import { PDFDownloadLink } from "@react-pdf/renderer";

const BookingSuccessful = ({ booking }) => {
  useEffect(() => {
    emailjs
      .send(
        process.env.REACT_APP_EMAIL_SERVICE_ID,
        process.env.REACT_APP_EMAIL_TEMPLATE_ID,
        {
          user_email: booking.email,
          firstName: booking.firstName,
          lastName: booking.lastName,
          movieName: booking.movie.name,
          showTime: parseDateTime(booking.movie.showtime),
          hall: booking.movie.hall,
          seats: booking.seats
            .map((elem) => `${elem.rowName}${elem.colNumber}`)
            .join(", "),
          amountPaid: `$ ${parseCurrency(booking.total)}`,
        },
        process.env.REACT_APP_USER_ID
      )
      .then(
        (result) => {
          console.log(result.text);
        },
        (error) => {
          console.log(error.text);
        }
      );
  }, [booking]);

  return (
    <Paper sx={{ bgcolor: "#F0F0F0", color: "#000" }}>
      <div>
        {/* Change the value string to url later */}
        <QRGenerator valueString={booking.id} documentId={booking.id} />
      </div>
      <Typography variant="overline">
        {booking.firstName}, your booking is successful!
      </Typography>

      {/* <PDFDownloadLink
        document={<GeneratePdf dataUrl={booking.id} />}
        fileName={`Adeline_Cinema_Booking_${booking.id}`}
      ></PDFDownloadLink> */}

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
