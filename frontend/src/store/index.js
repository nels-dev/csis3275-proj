import { createStore } from "vuex";
import { auth } from "./auth.module";
import { alert } from "./alert.module";
import { account } from "./account.module";
const store = createStore({
  modules: {
    auth,
    alert,
    account,
  },
});

export default store;
