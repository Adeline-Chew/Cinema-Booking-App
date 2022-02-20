import React, { useState } from "react";
import DoneIcon from "@mui/icons-material/Done";
import { makeStyles } from "@mui/styles";
import { Box } from "@mui/system";

const useStyles = makeStyles({
  seat: {
    width: 40,
    height: 40,
    textAlign: "center",
    alignItems: "baseline",
    borderRadius: 5,
    paddingY: 3,
    boxShadow: "0 2px 5px 2px rgba(255, 255, 255, .3)",
    cursor: (props) => !props.seat.booked && "pointer",
    background: (props) =>
      !props.seat.booked
        ? "#FEFBF3"
        : props.seat.booked
        ? "#548CFF"
        : "#FEFBF3",
  },
});

const useClickedStyles = makeStyles({
  seat: {
    width: 40,
    height: 40,
    textAlign: "center",
    alignItems: "baseline",
    borderRadius: 5,
    boxShadow: "0 2px 5px 2px rgba(73, 255, 0, .3)",
    cursor: (props) => !props.seat.booked && "pointer",
    background: "#49FF00",
  },
});
const Seat = (props) => {
  const { handleAddSeat, seat } = props;
  const [checked, setChecked] = useState(false);
  const classes = useStyles(props);
  const clickedClasses = useClickedStyles(props);

  const handleClick = () => {
    if (!seat.booked) {
      handleAddSeat();
      setChecked((prev) => !prev);
    }
  };
  return (
    <Box
      className={checked ? clickedClasses.seat : classes.seat}
      onClick={handleClick}
    >
      {checked && !seat.booked && <DoneIcon color="white" fontSize="large" />}
    </Box>
  );
};

export default Seat;
