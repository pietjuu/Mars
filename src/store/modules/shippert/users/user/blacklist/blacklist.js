import { USER_ID } from "@/main";
import { get, remove, post } from "@/assets/js/data-connector/api-communication-abstractor";

const state = {
    userBlacklist: {
        items: []
    }
};

const getters = {
    userBlacklist: (state) => state.userBlacklist
};

const actions = {
    async fetchUserBlacklist({ commit }) {
        await get(`users/${USER_ID}/blacklist`,(blacklist) => commit('setUserBlacklist', blacklist));
    },
    async createUserBlacklistItem({ commit }, item) {
        post(`users/${USER_ID}/blacklist`, {itemName: item}).then(commit('createItemInUserBlacklist', item));
    },
    async deleteUserBlacklistItem({ commit }, item) {
        remove(`users/${USER_ID}/blacklist/${item}`).then(commit('deleteItemFromUserBlacklist', item));
    }
};

const mutations = {
    setUserBlacklist: (state, blacklist) => {
        state.userBlacklist = blacklist;
    },
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
