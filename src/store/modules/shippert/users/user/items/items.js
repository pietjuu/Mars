import { USER_ID } from "@/main";
import { get } from "@/assets/js/data-connector/api-communication-abstractor";

const state = {
    userItems: undefined
};

const getters = {
    userItems: (state) => state.userItems,
};

const actions = {
    async fetchUserItems({ commit }) {
        await get(`users/${USER_ID}/items`,userItems => commit('setUserItems', userItems));
    }
};

const mutations = {
    setUserItems: (state, userItems) => (state.userItems = userItems)
};

export default {
    state,
    getters,
    actions,
    mutations
};
