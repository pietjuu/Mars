import { loadFromStorage } from "@/assets/js/data-connector/local-storage-abstractor";
import {API} from "@/main";

const state = {
    user: { }
};

const getters = {
    user: (state) => state.user
};

const actions = {
    async fetchUser({ commit }) {
        const userId = loadFromStorage('userId');

        const response = await fetch(`${API}users/${userId}`);
        const user = await response.json();
        commit('setUser', user)
    }
};

const mutations = {
    setUser: (state, user) => (state.user = user)
};

export default {
    state,
    getters,
    actions,
    mutations
};
