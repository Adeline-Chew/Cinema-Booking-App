import { Navigate, useRoutes } from "react-router-dom";
import MainLayout from "./layouts/MainLayout";
import NotFound from "./pages/NotFound";
import SeatLayout from "./pages/SeatLayout";

export default function Router() {
  return useRoutes([
    {
      path: "/",
      element: <MainLayout />,
      children: [
        { path: "/", element: <Navigate to="/index" /> },
        { path: "index", element: <SeatLayout /> },
        { path: "404", element: <NotFound /> },
      ],
    },

    { path: "*", element: <Navigate to="/404" replace /> },
  ]);
}
