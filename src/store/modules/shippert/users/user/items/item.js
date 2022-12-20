import { USER_ID } from "@/main";
import { get } from "@/assets/js/data-connector/api-communication-abstractor";

const state = {
    userItem: undefined
};

const getters = {
    userItem: (state) => state.userItem,
};

const actions = {
    async fetchUserItem({ commit }, itemId) {
        await get(`users/${USER_ID}/items/${itemId}`,userItem => commit('setUserItem', userItem));
    }
};

const mutations = {
    setUserItem: (state, userItem) => (state.userItem = userItem)
};

export default {
    state,
    getters,
    actions,
    mutations
};
