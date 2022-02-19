import api from "./api";
import { removeUser } from "./token.service";

export const login = async (email, password) => {
  const res = await api
    .post("/auth/jwt/create/", {
      email,
      password,
    })
    .then((response) => {
      if (response.data.access) {
        localStorage.setItem("user", JSON.stringify(response.data));
        localStorage.setItem("email", email);
        window.location.replace("/app/index");
      } else {
        window.location.replace("/login");
      }
    })
    .catch((err) => {
      return "401";
    });
  if (res === "401") return "401";
};

export const apiLogout = () => {
  removeUser();
  window.location.replace("/login");
};

export const getCurrentUser = () => {
  return JSON.parse(localStorage.getItem("user"));
};
