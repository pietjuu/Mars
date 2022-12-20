import { TOKEN } from "@/main";
import { get, remove, post } from "@/assets/js/data-connector/api-communication-abstractor";

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
        await get(`users/${TOKEN}/blacklist`,(blacklist) => commit('setUserBlacklist', blacklist));
    },
    async fetchShippertBlacklist({ commit }) {
        await get(`blacklist`,(blacklist) => commit('setShippertBlacklist', blacklist));
    },
    async fetchAllBlacklists({ commit }) {
        await get(`users/${TOKEN}/blacklist`,(blacklist) => commit('setUserBlacklist', blacklist));
        await get(`blacklist`,(blacklist) => commit('setShippertBlacklist', blacklist));
    },
    async createUserBlacklistItem({ commit }, item) {
        post(`users/${TOKEN}/blacklist`, { itemName: item }).then(commit('createItemInUserBlacklist', item));
    },
    async deleteUserBlacklistItem({ commit }, item) {
        remove(`users/${TOKEN}/blacklist/${item}`).then(commit('deleteItemFromUserBlacklist', item));
    }
};

const mutations = {
    setUserBlacklist: (state, blacklist) => (state.userBlacklist = blacklist),
    setShippertBlacklist: (state, blacklist) => (state.shippertBlacklist = blacklist),
    deleteItemFromUserBlacklist: (state, item) => {
        const index = state.userBlacklist.items.indexOf(item);
        state.userBlacklist.items.splice(index, 1);
    },
    createItemInUserBlacklist: (state, item) => {
        state.userBlacklist.items.push(item);
    }
};

export default {
    state,
    getters,
    actions,
    mutations
};