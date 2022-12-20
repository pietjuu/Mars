import { get } from "@/assets/js/data-connector/api-communication-abstractor";

const state = {
    transporter: undefined
};

const getters = {
    transporter: (state) => state.transporter
};

const actions = {
    async fetchTransporter({ commit }, transporterId) {
        await get(`transporters/${transporterId}`, transporter => commit('setTransporter', transporter));
    }
};

const mutations = {
    setTransporter: (state, transporter) => (state.transporter = transporter)
};

export default {
    state,
    getters,
    actions,
    mutations
};
