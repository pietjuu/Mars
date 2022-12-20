import { get } from "@/assets/js/data-connector/api-communication-abstractor";

const state = {
    transporters: []
};

const getters = {
    transporters: (state) => state.transporters
};

const actions = {
    async fetchTransporters({ commit }) {
        await get(`transporters`, transporters => commit('setTransporters', transporters));
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
