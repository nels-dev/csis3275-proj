const initialState = {
  lastRefresh: new Date(),
};

export const account = {
  namespaced: true,
  state: initialState,
  actions: {
    balanceChanged({ commit }) {
      commit("balanceChanged");
    },
  },
  mutations: {
    balanceChanged(state) {
      state.lastRefresh = new Date();
    },
  },
};
