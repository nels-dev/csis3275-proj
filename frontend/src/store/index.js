import { createStore } from "vuex";
import { auth } from "./auth.module";
import { alert } from "./alert.module";
import { account } from "./account.module";
import { mystore } from "./mystore.module";
const store = createStore({
  modules: {
    auth,
    alert,
    account,
    mystore,
  },
});

export default store;
