import {
  Button,
  Card,
  Dialog,
  DialogContent,
  DialogTitle,
  Grid,
  Stack,
  TextField,
  Typography,
} from "@mui/material";
import { LoadingButton } from "@mui/lab";
import { Form, Formik } from "formik";
import * as Yup from "yup";
import React, { useEffect, useRef, useState } from "react";
import { getSeatById, makeBooking } from "../../services/services";
import { parseCurrency, parseDateTime } from "../../utils/helpers";
import BookingSuccessful from "./BookingSuccessful";
import BookingFailed from "./BookingFailed";

const BookingDialog = ({
  open,
  handleDialogClose,
  selectedSeatsIds,
  movie,
  submitted,
  setSubmitted,
}) => {
  const [selectedSeats, setSelectedSeats] = useState([]);
  const [bookingDetails, setBookingDetails] = useState();
  const [bookingStatus, setBookingStatus] = useState(false);
  const form = useRef();

  useEffect(() => {
    setSelectedSeats([]);
    for (let i = 0; i < selectedSeatsIds.length; i++) {
      getSeatById(selectedSeatsIds[i], setSelectedSeats);
    }
  }, [selectedSeatsIds]);

  const handleSubmit = async (values, { setSubmitting }) => {
    const { firstName, lastName, user_email } = values;

    await makeBooking(
      firstName,
      lastName,
      user_email,
      movie.id,
      selectedSeatsIds,
      setBookingDetails,
      setBookingStatus,
      setSubmitted
    );
    setSubmitting(false);
  };

  const handleClose = () => {
    handleDialogClose();
    setBookingDetails();
    setSubmitted(false);
    setBookingStatus(false);
  };

  return (
    <Dialog open={open} onClose={handleClose} fullWidth>
      <Stack
        direction="row"
        sx={{
          display: "flex",
          justifyContent: "space-between",
          pr: 2,
          alignItems: "center",
          backgroundImage:
            "linear-gradient(to right, #00CDAC 0%, #02AAB0  71%, #02AAB0  100%)",
        }}
      >
        <DialogTitle>Book Your Movie</DialogTitle>
      </Stack>
      <DialogContent
        dividers
        sx={{ bgcolor: "#F0F0F0", input: { color: "#000" } }}
      >
        {!submitted ? (
          <>
            <Card
              sx={{ px: 2, py: 3, mb: 2, bgcolor: "#D3DEDC", color: "#000" }}
            >
              <Typography variant="subtitle1" sx={{ pb: 2 }}>
                Booking Details
              </Typography>
              <Grid container>
                <Grid item md={3}>
                  <Typography variant="subtitle2">Movie name</Typography>
                </Grid>
                <Grid item md={1}>
                  :
                </Grid>
                <Grid item md={8}>
                  {movie.name}
                </Grid>

                <Grid item md={3}>
                  <Typography variant="subtitle2">Showtime</Typography>
                </Grid>
                <Grid item md={1}>
                  :
                </Grid>
                <Grid item md={8}>
                  {parseDateTime(movie.showtime)}
                </Grid>

                <Grid item md={3}>
                  <Typography variant="subtitle2">Hall</Typography>
                </Grid>
                <Grid item md={1}>
                  :
                </Grid>
                <Grid item md={8}>
                  {movie.hall}
                </Grid>

                <Grid item md={3}>
                  <Typography variant="subtitle2">Selected seat(s)</Typography>
                </Grid>
                <Grid item md={1}>
                  :
                </Grid>
                <Grid item md={8}>
                  {selectedSeats
                    .map((elem) => `${elem.rowName}${elem.colNumber}`)
                    .join(", ")}
                </Grid>

                <Grid item md={3}>
                  <Typography variant="subtitle2">Price per ticket</Typography>
                </Grid>
                <Grid item md={1}>
                  :
                </Grid>
                <Grid item md={8}>
                  $ {parseCurrency(movie.price)}
                </Grid>

                <Grid item md={3}>
                  <Typography variant="subtitle2">Total amount</Typography>
                </Grid>
                <Grid item md={1}>
                  :
                </Grid>
                <Grid item md={8}>
                  $ {parseCurrency(movie.price * selectedSeats.length)}
                </Grid>
              </Grid>
            </Card>
            <Formik
              initialValues={{ firstName: "", lastName: "", user_email: "" }}
              onSubmit={handleSubmit}
              validationSchema={Yup.object().shape({
                firstName: Yup.string().required("First name is required"),
                lastName: Yup.string().required("Last name is required"),
                user_email: Yup.string()
                  .email("Email must be a valid email address")
                  .required("Email is required"),
              })}
            >
              {(props) => {
                const {
                  values,
                  touched,
                  errors,
                  isSubmitting,
                  handleChange,
                  handleBlur,
                  handleSubmit,
                } = props;
                return (
                  <Form
                    autoComplete="off"
                    noValidate
                    onSubmit={handleSubmit}
                    ref={form}
                  >
                    <Grid container spacing={2} sx={{ alignItems: "center" }}>
                      <Grid item md={6} xs={6}>
                        <TextField
                          id="firstName"
                          margin="normal"
                          required
                          label="First Name"
                          name="firstName"
                          size="small"
                          variant="outlined"
                          fullWidth
                          value={values.firstName}
                          onChange={(e) => {
                            handleChange(e);
                          }}
                          onBlur={handleBlur}
                          error={Boolean(touched.firstName && errors.firstName)}
                          helperText={touched.firstName && errors.firstName}
                        />
                      </Grid>
                      <Grid item md={6} xs={6}>
                        <TextField
                          id="lastName"
                          margin="normal"
                          required
                          label="Last Name"
                          name="lastName"
                          size="small"
                          variant="outlined"
                          fullWidth
                          value={values.lastName}
                          onChange={(e) => {
                            handleChange(e);
                          }}
                          onBlur={handleBlur}
                          error={Boolean(touched.lastName && errors.lastName)}
                          helperText={touched.lastName && errors.lastName}
                        />
                      </Grid>

                      <Grid item md={12} xs={12}>
                        <TextField
                          id="user_email"
                          type="email"
                          margin="normal"
                          required
                          label="Email"
                          name="user_email"
                          size="small"
                          variant="outlined"
                          fullWidth
                          value={values.user_email}
                          onChange={(e) => {
                            handleChange(e);
                          }}
                          onBlur={handleBlur}
                          error={Boolean(
                            touched.user_email && errors.user_email
                          )}
                          helperText={touched.user_email && errors.user_email}
                        />
                      </Grid>
                    </Grid>

                    <Stack
                      direction="row"
                      spacing={2}
                      sx={{
                        my: 3,
                        display: "flex",
                        justifyContent: "center",
                      }}
                    >
                      <Button onClick={handleDialogClose}>Cancel</Button>
                      <LoadingButton
                        type="submit"
                        fullWidth
                        variant="contained"
                        sx={{ mt: 3, mb: 2, bgcolor: "#00CDAC" }}
                        loading={isSubmitting}
                      >
                        Book Now!
                      </LoadingButton>
                    </Stack>
                  </Form>
                );
              }}
            </Formik>
          </>
        ) : bookingStatus ? (
          <BookingSuccessful booking={bookingDetails} />
        ) : (
          <BookingFailed />
        )}
      </DialogContent>
    </Dialog>
  );
};

export default BookingDialog;
