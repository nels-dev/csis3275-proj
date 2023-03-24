// Styles
import "@mdi/font/css/materialdesignicons.css";
import "vuetify/styles";

// Vuetify
import { createVuetify } from "vuetify";

const seasellTheme = {
  dark: false,
  colors: {
    background: "#FDFDFD",
    surface: "#FDFDFD",
    "on-background": "1a2749",
    "on-surface": "1a2749",
    //Main brand color: This color should be eye-catching but not harsh. It can be liberally applied to your layout as its main identity.
    primary: "#3D6DB0",
    //Dark shades: Use as the text color for dark-on-light designs, or as the background for inverted designs.
    "primary-darken-1": "#253A72",
    //Light accent: Accent colors can be used to bring attention to design elements by contrasting with the rest of the palette.
    secondary: "#74ADD6",
    "secondary-darken-1": "#5085BD",
    error: "#f44336",
    info: "#253a72",
    success: "#489b6d",
    warning: "#c58b35",
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
