import api from "./api";

export async function getMovies(setList, setLoaded) {
  const url = "/movies";
  await api
    .get(url)
    .then((res) => setList(res.data))
    .finally(() => setLoaded(true))
    .catch((err) => {
      console.error("Error getting data: ", err);
    });
}

export async function getMovieById(id, setItem, setLoaded) {
  const url = `/movies/${id}`;
  await api
    .get(url)
    .then((res) => {
      setItem(res.data);
    })
    .finally(() => setLoaded(true))
    .catch((err) => {
      console.error("Error getting data: ", err);
    });
}

export async function getSeatById(id, setItem) {
  const url = `/seats/${id}`;
  await api
    .get(url)
    .then((res) => setItem((prev) => [...prev, res.data]))
    .catch((err) => {
      console.error("Error getting data: ", err);
    });
}

export async function checkSeatStatus(id) {
  const url = `/seats/${id}/status`;
  const status = await api
    .get(url)
    .then((res) => res.data.booked)
    .catch((err) => {
      console.error("Error getting data: ", err);
    });
  console.log(status);
  return status;
}

export async function makeBooking(
  firstName,
  lastName,
  email,
  movieId,
  seatIds,
  setBookingDetails,
  setBookingStatus,
  setSubmitted
) {
  const url = `/booking/create?firstName=${firstName}&lastName=${lastName}&email=${email}&movieId=${movieId}&seatIds=${seatIds}`;
  await api
    .post(url)
    .then((res) => {
      if (res.status === 200) {
        setBookingStatus(true);
        setBookingDetails(res.data);
      } else setBookingStatus(false);
    })
    .catch((err) => {
      console.error("Error posting data: ", err);
    })
    .finally(() => setSubmitted(true));
}

export async function getBookingById(id, setItem) {
  const url = `/booking/${id}`;
  await api
    .get(url)
    .then((res) => setItem(res.data))
    .catch((err) => {
      console.error("Error posting data: ", err);
    });
}
