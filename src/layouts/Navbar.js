import PropTypes from "prop-types";
import React, { useEffect, useState } from "react";

// material
import { styled } from "@mui/material/styles";
import {
  AppBar,
  Toolbar,
  Grid,
  Button,
  Typography,
  Stack,
  FormControl,
  Select,
  InputLabel,
  MenuItem,
  Container,
} from "@mui/material";
import MenuIcon from "@mui/icons-material/Menu";
import MHidden from "../components/@material-extend/MHidden";
import { useNavigate } from "react-router-dom";
import logo from "../images/cinemalogo.png";

// ----------------------------------------------------------------------

const APPBAR_MOBILE = 64;
const APPBAR_DESKTOP = 92;

const RootStyle = styled(AppBar)(({ theme }) => ({
  boxShadow: "none",
  backdropFilter: "blur(6px)",
  backgroundColor: "#000",
  borderBottom: "1px solid #000 ",
  [theme.breakpoints.up("lg")]: {
    width: `100%`,
    alignItems: "center",
  },
}));

const ToolbarStyle = styled(Toolbar)(({ theme }) => ({
  minHeight: APPBAR_MOBILE,
  width: "100%",
  display: "flex",
  justifyContent: "space-between",
  marginLeft: "3%",
  [theme.breakpoints.up("lg")]: {
    minHeight: APPBAR_DESKTOP,
    padding: theme.spacing(0, 5),
  },
}));

// ----------------------------------------------------------------------

Navbar.propTypes = {
  onOpenSidebar: PropTypes.func,
};

export default function Navbar({
  onOpenSidebar,
  hidden,
  handleMovieChange,
  movieLoaded,
  loaded,
  movieList,
  selectedMovie,
  selectedMovieId,
}) {
  const navigate = useNavigate();

  return (
    <RootStyle>
      <ToolbarStyle>
        <MHidden width="lgUp">
          <Button onClick={onOpenSidebar}>
            <MenuIcon sx={{ mr: 1, color: "text.primary" }} />
          </Button>
        </MHidden>
        {!hidden && (
          <>
            <Grid
              container
              spacing={3}
              direction="row"
              alignItems="center"
              sx={{
                position: "absolute",
                bottom: 10,
              }}
            >
              <Grid item md={2}>
                <img
                  onClick={() => {
                    navigate("/index");
                  }}
                  src={logo}
                  alt="logo"
                  style={{
                    width: "70px",
                    height: "70px",
                    cursor: "pointer",
                  }}
                />
              </Grid>
              {movieLoaded && (
                <Grid item md={7}>
                  <Typography variant="h4">{selectedMovie.name}</Typography>
                </Grid>
              )}
              {loaded && (
                <Grid item md={3}>
                  <Stack direction="row" spacing={2} alignItems="center">
                    <Typography variant="subtitle1">Movie:</Typography>
                    <FormControl fullWidth>
                      {/* <InputLabel>Movie</InputLabel> */}
                      <Select
                        value={selectedMovieId}
                        onChange={handleMovieChange}
                        size="small"
                      >
                        {movieList.map((movie) => (
                          <MenuItem value={movie.id} key={movie.id}>
                            {movie.name}
                          </MenuItem>
                        ))}
                      </Select>
                    </FormControl>
                  </Stack>
                </Grid>
              )}
            </Grid>
          </>
        )}
      </ToolbarStyle>
    </RootStyle>
  );
}