const initialState = {
  message: null,
  timestamp: new Date(),
  isAlert: true,
};

export const alert = {
  namespaced: true,
  state: initialState,
  actions: {
    push({ commit }, message) {
      commit("showAlert", message);
    },
    pushInfo({ commit }, message) {
      commit("showInfo", message);
    },
  },
  mutations: {
    showAlert(state, message) {
      state.message = message;
      state.timestamp = new Date();
      state.isAlert = true;
    },
    showInfo(state, message) {
      state.message = message;
      (state.timestamp = new Date()), (state.isAlert = false);
    },
  },
};
