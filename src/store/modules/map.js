
const state = {
    clickedLocation: undefined,

};

const getters = {
    clickedLocation: (state) => state.clickedLocation
};

const actions = {
    setClickedLocation({ commit }, location) {
        commit("setClickedLocation", location);
    }
};

const mutations = {
    setClickedLocation: (state, location) => (state.clickedLocation = location)
};

export default {
    state,
    getters,
    actions,
    mutations
};
