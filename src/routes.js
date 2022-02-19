import { Navigate, useRoutes } from "react-router-dom";
import MainLayout from "./layouts/MainLayout";
import SeatLayout from "./pages/SeatLayout";

export default function Router() {
  return useRoutes([
    {
      path: "/",
      element: <MainLayout />,
      children: [
        { path: "/", element: <Navigate to="/index" /> },
        { path: "index", element: <SeatLayout /> },
      ],
    },

    { path: "*", element: <Navigate to="/404" replace /> },
  ]);
}
