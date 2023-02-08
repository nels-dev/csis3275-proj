import { createApp } from "vue";
import App from "./App.vue";
import vuetify from "./plugins/vuetify";
import router from "./router";
import store from "./store";
import { loadFonts } from "./plugins/webfontloader";

//from fontawesome
import { library } from "@fortawesome/fontawesome-svg-core";
import { FontAwesomeIcon } from "@fortawesome/vue-fontawesome";
import { faShop } from "@fortawesome/free-solid-svg-icons";
library.add(faShop);

// end of fontawesome
loadFonts();

createApp(App)
  .use(vuetify)
  .use(router)
  .use(store)
  .component("font-awesome-icon", FontAwesomeIcon)
  .mount("#app");
