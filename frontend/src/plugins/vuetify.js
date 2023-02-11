// Styles
import "@mdi/font/css/materialdesignicons.css";
import "vuetify/styles";

// Vuetify
import { createVuetify } from "vuetify";

const seasellTheme = {
  dark: false,
  colors: {
    background: "#f8fdff",
    surface: "#f8fdff",
    primary: "#006874",
    "primary-darken-1": "#001f24",
    secondary: "#4a6267",
    "secondary-darken-1": "#051f23",
    error: "#ba1a1a",
    info: "#4a6267",
    success: "#4CAF50",
    warning: "#FB8C00",
  },
};

export default createVuetify({
  theme: {
    defaultTheme: "seasellTheme",
    themes: {
      seasellTheme,
    },
  },
});
// https://vuetifyjs.com/en/introduction/why-vuetify/#feature-guides
