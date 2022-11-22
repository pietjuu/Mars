import { get } from "@/assets/js/data-connector/api-communication-abstractor";

function requestStarted(commit) {
    commit('setTransportersRequest', {
        error: false,
        message: "Loading transporters..."
    });
}

function requestSuccess(commit, transporters) {
    commit('setTransporters', transporters);
    commit('setTransportersRequest', {
        error: false,
        message: "Transporters received"
    });
}

function requestFailed(commit, error) {
    commit('setTransportersRequest', {
        error: true,
        message: error.statusText
    });
}

const state = {
    transporters: [],
    request: {
        error: false,
        message: ""
    }
};

const getters = {
    transporters: (state) => state.transporters,
    transportersRequest: (state) => state.request
};

const actions = {
    async fetchTransporters({ commit }) {
        requestStarted(commit);
        await get(`transporters`,(transporters) => requestSuccess(commit, transporters),(error) => requestFailed(commit, error));
    }
};

const mutations = {
    setTransporters: (state, transporters) => (state.transporters = transporters),
    setTransportersRequest: (state, request) => (state.request = request)
};

export default {
    state,
    getters,
    actions,
    mutations
};
