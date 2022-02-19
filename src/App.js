// routes
import { Toaster } from "react-hot-toast";
import Router from "./routes";
// theme
import ThemeConfig from "./theme";
import GlobalStyles from "./theme/globalStyles";

function App() {
  return (
    <ThemeConfig>
      <GlobalStyles />
      <Toaster />
      <Router />
    </ThemeConfig>
  );
}

export default App;
