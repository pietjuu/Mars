import { get } from "@/assets/js/data-connector/api-communication-abstractor";

const state = {
    users: []
};

const getters = {
    users: (state) => state.users
};

const actions = {
    async fetchUsers({ commit }) {
        await get(`users`,users => commit('setUsers', users));
    }
};

const mutations = {
    setUsers: (state, users) => (state.users = users)
};

export default {
    state,
    getters,
    actions,
    mutations
};
