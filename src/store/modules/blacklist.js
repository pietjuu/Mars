import { TOKEN } from "@/main";
import { get } from "@/assets/js/data-connector/api-communication-abstractor";

const state = {
    userBlacklist: undefined,
    shippertBlacklist: undefined
};

const getters = {
    userBlacklist: (state) => state.userBlacklist,
    shippertBlacklist: (state) => state.shippertBlacklist
};

const actions = {
    async fetchUserBlacklist({ commit }) {
        await get(`users/${TOKEN}/blacklist`,(blacklist) => {
            commit('setUserBlacklist', blacklist);
        });
    },
    async fetchShippertBlacklist({ commit }) {
        await get(`blacklist`,(blacklist) => {
            commit('setShippertBlacklist', blacklist);
        });
    },
    async fetchAllBlacklists({ commit }) {
        await get(`users/${TOKEN}/blacklist`,(blacklist) => {
            commit('setUserBlacklist', blacklist);
        });
        await get(`blacklist`,(blacklist) => {
            commit('setShippertBlacklist', blacklist);
        });
    }
};

const mutations = {
    setUserBlacklist: (state, blacklist) => (state.userBlacklist = blacklist),
    setShippertBlacklist: (state, blacklist) => (state.shippertBlacklist = blacklist)
};

export default {
    state,
    getters,
    actions,
    mutations
};