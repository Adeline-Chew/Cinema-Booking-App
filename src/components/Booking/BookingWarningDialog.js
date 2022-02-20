import { Box, Button, Dialog, DialogContent, Typography } from "@mui/material";
import React from "react";

const BookingWarningDialog = ({ open, handleDialogClose }) => {
  return (
    <Dialog open={open} onClose={handleDialogClose} fullWidth>
      <DialogContent dividers sx={{ bgcolor: "#F0F0F0", color: "#000" }}>
        <Typography variant="body1">
          Sorry, the seat chosen was booked!
        </Typography>
        <Box sx={{ mt: 3, display: "flex", justifyContent: "center" }}>
          <Button variant="contained" onClick={handleDialogClose}>
            Refresh seats
          </Button>
        </Box>
      </DialogContent>
    </Dialog>
  );
};

export default BookingWarningDialog;
