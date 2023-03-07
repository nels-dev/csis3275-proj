const initialState = {
  lastRefresh: new Date(),
};

export const mystore = {
  namespaced: true,
  state: initialState,
  actions: {
    productUpdated({ commit }) {
      commit("productUpdated");
    },
  },
  mutations: {
    productUpdated(state) {
      state.lastRefresh = new Date();
    },
  },
};
