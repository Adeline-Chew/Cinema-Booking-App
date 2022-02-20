import PropTypes from "prop-types";
import React from "react";

// material
import { styled } from "@mui/material/styles";
import {
  AppBar,
  Toolbar,
  Grid,
  Typography,
  Stack,
  FormControl,
  Select,
  MenuItem,
} from "@mui/material";
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
  maxWidth: "1200px",
  width: "100%",
  display: "flex",
  justifyContent: "space-between",
  [theme.breakpoints.up("lg")]: {
    minHeight: APPBAR_DESKTOP,
    paddingLeft: "24px",
    paddingRight: "24px",
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
        <Grid
          container
          spacing={3}
          direction="row"
          alignItems="center"
          sx={{
            position: "absolute",
          }}
        >
          <Grid item md={2} xs={2}>
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
            <Grid item md={6} xs={6}>
              <Typography variant="h4">{selectedMovie.name}</Typography>
            </Grid>
          )}
          {loaded && (
            <Grid item md={4} xs={4}>
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
      </ToolbarStyle>
    </RootStyle>
  );
}
