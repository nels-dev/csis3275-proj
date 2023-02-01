import authService from "@/services/auth.service";
const authentication = JSON.parse(localStorage.getItem('authentication'))
const initialState = authentication
    ? { status: { loggedIn: true }, token: authentication.token, user: authentication.user }
    : { status: { loggedIn: false } }

export const auth = {
    namespaced: true,
    state: initialState,
    actions: {
        login({ commit }, loginRequest) {
            return authService.login(loginRequest).then(
                response => {
                    commit('loginSuccess', response)
                    return Promise.resolve(response)
                },
                error => {
                    commit('loginFailure');
                    return Promise.reject(error);
                }
            )
        },
        logout({ commit }) {
            authService.logout();
            commit('logout');
        }
    },
    mutations: {
        loginSuccess(state, response) {
            state.status.loggedIn = true;
            state.user = response.user;
            state.token = response.token
        },
        loginFailure(state) {
            state.status.loggedIn = false;
            state.user = null;
            state.token = null
        },
        logout(state) {
            state.status.loggedIn = false;
            state.user = null;
            state.token = null
        },
    }
}