const initialState = {
  message: null,
  timestamp: new Date(),
};

export const alert = {
  namespaced: true,
  state: initialState,
  actions: {
    push({ commit }, message) {
      commit("showAlert", message);
    },
  },
  mutations: {
    showAlert(state, message) {
      state.message = message;
      state.timestamp = new Date();
    },
  },
};
