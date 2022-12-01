import { TOKEN } from "@/main";
import { get } from "@/assets/js/data-connector/api-communication-abstractor";

function requestStarted(commit) {
    commit('setUserRequest', {
        error: false,
        message: "Trying to identify user"
    });
}

function requestSuccess(commit, user) {
    commit('setUser', user);
    commit('setUserRequest', {
        error: false,
        message: "Successfully identified user"
    });
}

function requestFailed(commit, error) {
    commit('setUserRequest', {
        error: true,
        message: "Unable to identify user..."
    });
}

const state = {
    user: { },
    request: {
        error: false,
        message: ""
    }
};

const getters = {
    user: (state) => state.user,
    userRequest: (state) => state.request
};

const actions = {
    async fetchUser({ commit }) {

        requestStarted(commit);

        await get(`users/${TOKEN}`,(user) => requestSuccess(commit, user),(error) => requestFailed(commit, error));
    }
};

const mutations = {
    setUser: (state, user) => (state.user = user),
    setUserRequest: (state, request) => (state.request = request)
};

export default {
    state,
    getters,
    actions,
    mutations
};
