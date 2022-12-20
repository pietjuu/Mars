import { get } from "@/assets/js/data-connector/api-communication-abstractor";

const state = {
    shippertBlacklist: undefined
};

const getters = {
    shippertBlacklist: (state) => state.shippertBlacklist
};

const actions = {
    async fetchShippertBlacklist({ commit }) {
        await get(`blacklist`,(blacklist) => commit('setShippertBlacklist', blacklist));
    }
};

const mutations = {
    setShippertBlacklist: (state, blacklist) => (state.shippertBlacklist = blacklist)
};

export default {
    state,
    getters,
    actions,
    mutations
};