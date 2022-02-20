import React, { useEffect, useState } from "react";
import { Outlet } from "react-router-dom";
import { styled } from "@mui/material/styles";
import Navbar from "./Navbar";
import useMediaQuery from "@mui/material/useMediaQuery";
import { getMovieById, getMovies } from "../services/services";

const APP_BAR_MOBILE = 64;
const APP_BAR_DESKTOP = 92;

const RootStyle = styled("div")({
  display: "flex",
  minHeight: "100vh",
  overflow: "hidden",
});

const MainStyle = styled("div")(({ theme }) => ({
  flexGrow: 1,
  overflow: "auto",
  paddingTop: APP_BAR_MOBILE + 24,
  paddingBottom: theme.spacing(10),
  [theme.breakpoints.up("lg")]: {
    paddingTop: APP_BAR_DESKTOP + 24,
    paddingLeft: theme.spacing(2),
    paddingRight: theme.spacing(2),
  },
}));

const MainLayout = () => {
  const hidden = useMediaQuery((theme) => theme.breakpoints.up("md"));
  const [movieList, setMovieList] = useState([]);
  const [loaded, setLoaded] = useState(false);
  const [selectedMovie, setSelectedMovie] = useState();
  const [selectedMovieId, setSelectedMovieId] = useState(1);
  const [movieLoaded, setMovieLoaded] = useState(false);

  useEffect(() => {
    getMovies(setMovieList, setLoaded);
  }, []);

  useEffect(() => {
    getMovieById(selectedMovieId, setSelectedMovie, setMovieLoaded);
  }, [selectedMovieId]);

  const handleMovieChange = (e) => setSelectedMovieId(e.target.value);

  return (
    <RootStyle>
      <Navbar
        hidden={!hidden}
        handleMovieChange={handleMovieChange}
        movieLoaded={movieLoaded}
        loaded={loaded}
        movieList={movieList}
        selectedMovie={selectedMovie}
        selectedMovieId={selectedMovieId}
      />
      <MainStyle>
        <Outlet context={[selectedMovie, setSelectedMovie]} />
      </MainStyle>
    </RootStyle>
  );
};

export default MainLayout;
