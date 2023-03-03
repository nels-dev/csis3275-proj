import { createApp } from "vue";
import App from "./App.vue";
import vuetify from "./plugins/vuetify";
import router from "./router";
import store from "./store";
import { loadFonts } from "./plugins/webfontloader";
//from fontawesome
import { library } from "@fortawesome/fontawesome-svg-core";
import { FontAwesomeIcon } from "@fortawesome/vue-fontawesome";
import { faShop, faTrashCan, faGear } from "@fortawesome/free-solid-svg-icons";
import moment from "moment";
library.add(faShop, faTrashCan, faGear);

// end of fontawesome
loadFonts();
createApp(App)
  .use(vuetify)
  .use(router)
  .use(store)
  .provide("moment", moment)
  .component("font-awesome-icon", FontAwesomeIcon)
  .mount("#app");
