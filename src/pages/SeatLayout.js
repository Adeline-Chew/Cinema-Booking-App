import { Backdrop, CircularProgress } from "@mui/material";
import { Box } from "@mui/system";
import React from "react";
import { useOutletContext } from "react-router-dom";
import SeatIndicator from "../components/Seat/SeatIndicator";
import Seats from "../components/Seat/Seats";
import screen from "../images/cinema-screen.png";

const SeatLayout = () => {
  const [selectedMovie, setSelectedMovie] = useOutletContext();

  return (
    <Box>
      <SeatIndicator />
      <Box sx={{ display: "flex", justifyContent: "center" }}>
        <img src={screen} alt="screen" />
      </Box>
      {selectedMovie ? (
        <Seats
          totalSeats={selectedMovie.seats}
          selectedMovie={selectedMovie}
          setSelectedMovie={setSelectedMovie}
        />
      ) : (
        <Backdrop
          sx={{ color: "#fff", zIndex: (theme) => theme.zIndex.drawer + 1 }}
          open={true}
        >
          <CircularProgress color="inherit" />
        </Backdrop>
      )}
    </Box>
  );
};

export default SeatLayout;
