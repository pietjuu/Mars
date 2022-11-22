
const state = {
    selectedTransporterOnMap: {}
};

const getters = {
    selectedTransporterOnMap: (state) => state.selectedTransporterOnMap
};

const actions = {
    setSelectedTransporterOnMap({ commit }, transporter) {
        commit("setSelectedTransporterOnMap", transporter);
    }
};

const mutations = {
    setSelectedTransporterOnMap: (state, transporter) => (state.selectedTransporterOnMap = transporter)
};

export default {
    state,
    getters,
    actions,
    mutations
};
