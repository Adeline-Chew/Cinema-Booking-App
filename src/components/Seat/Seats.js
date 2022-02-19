import React, { useEffect, useState } from "react";
import { Box, Button, Container, Grid, Typography } from "@mui/material";
import Seat from "./Seat";
import { makeStyles } from "@mui/styles";
import BookingDialog from "../Booking/BookingDialog";
import { checkSeatStatus, getMovieById } from "../../services/services";
import BookingWarningDialog from "../Booking/BookingWarningDialog";

const useStyles = makeStyles({
  button: {
    backgroundImage:
      "linear-gradient(to right, #02AAB0 0%, #00CDAC  51%, #02AAB0  100%)",
    padding: "0px 45px",
    textAlign: "center",
    textTransform: "uppercase",
    transition: "0.5s",
    backgroundSize: "200% auto",
    color: "white",
    boxShadow: "0 0 5px #eee",
    borderRadius: "10px",
    display: "block",
    "&:hover": {
      backgroundPosition:
        "right center" /* change the direction of the change here */,
      color: "#fff",
      textDecoration: "none",
    },
  },
});

const Seats = ({ totalSeats, selectedMovie, setSelectedMovie }) => {
  const classes = useStyles();
  const [submitted, setSubmitted] = useState(false);
  const [selectedSeats, setSelectedSeats] = useState([]);
  const [openBookingDialog, setOpenBookingDialog] = useState(false);
  const [openBookedWarningDialog, setOpenBookedWarningDialog] = useState(false);

  const handleBookingDialogClose = () => {
    setOpenBookingDialog(false);
    // Only reload the screen when the booking is submitted
    if (submitted) {
      window.location.reload();
    }
  };

  const handleWarningDialogClose = () => {
    setOpenBookedWarningDialog(false);
    window.location.reload();
  };

  const handleBookBtnClick = async () => {
    let seatStatus = true;
    for (let i = 0; i < selectedSeats.length; i++) {
      // Some of the seats are booked
      let isBooked = await checkSeatStatus(selectedSeats[i]);
      console.log(isBooked);
      if (isBooked) {
        setOpenBookedWarningDialog(true);
        seatStatus = false;
        break;
      }
    }
    if (seatStatus) setOpenBookingDialog(true);
  };

  const handleAddSeat = (seat) => {
    const selectedIndex = selectedSeats.indexOf(seat.id);
    let newSelectedSeat = [];

    if (selectedIndex === -1)
      newSelectedSeat = newSelectedSeat.concat(selectedSeats, seat.id);
    else if (selectedIndex === 0)
      newSelectedSeat = newSelectedSeat.concat(selectedSeats.slice(1));
    else
      newSelectedSeat = newSelectedSeat.concat(
        selectedSeats.slice(0, selectedIndex),
        selectedSeats.slice(selectedIndex + 1)
      );

    setSelectedSeats(newSelectedSeat);
  };

  return (
    <Container sx={{ my: 2 }}>
      <Grid container columns={10} columnSpacing={5} rowSpacing={3}>
        {totalSeats.length > 0 &&
          totalSeats.map((seat) => (
            <Grid item md={1} key={seat.id}>
              <Seat
                setSelectedSeats={setSelectedSeats}
                handleAddSeat={() => handleAddSeat(seat)}
                seat={seat}
              ></Seat>
              <Typography
                sx={{
                  fontSize: "12px",
                }}
              >{`${seat.rowName}${seat.colNumber}`}</Typography>
            </Grid>
          ))}
      </Grid>
      <Box sx={{ display: "flex", justifyContent: "center", mt: 7 }}>
        <Button
          variant="contained"
          size="large"
          className={classes.button}
          // Check selectedSeat before open booking dialog, and check if the selectedSeat is taken
          onClick={handleBookBtnClick}
        >
          Book
        </Button>
      </Box>
      <BookingDialog
        open={openBookingDialog}
        handleDialogClose={handleBookingDialogClose}
        selectedSeatsIds={selectedSeats}
        movie={selectedMovie}
        submitted={submitted}
        setSubmitted={setSubmitted}
      />
      <BookingWarningDialog
        open={openBookedWarningDialog}
        handleDialogClose={handleWarningDialogClose}
      />
    </Container>
  );
};

export default Seats;
