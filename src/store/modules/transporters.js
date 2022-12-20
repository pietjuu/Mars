import { get } from "@/assets/js/data-connector/api-communication-abstractor";

function requestFailed(commit, error) {
    commit('setTransportersRequest', {
        error: true,
        message: error.statusText
    });
}

const state = {
    transporters: []
};

const getters = {
    transporters: (state) => state.transporters
};

const actions = {
    async fetchTransporters({ commit }) {
        await get(`transporters`).then(transporters => commit('setTransporters', transporters));
    }
};

const mutations = {
    setTransporters: (state, transporters) => (state.transporters = transporters)
};

export default {
    state,
    getters,
    actions,
    mutations
};
