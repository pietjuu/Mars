import { loadFromStorage } from "@/assets/js/data-connector/local-storage-abstractor";

const state = {
    user: { }
};

const getters = {
    user: (state) => state.user
};

const actions = {
    async fetchUser({ commit }) {
        const userId = loadFromStorage('userId');

        const response = await fetch(`http://localhost:3000/api/users/${userId}`);
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
