import { get } from "@/assets/js/data-connector/api-communication-abstractor";

const state = {
    calculatedPrice: undefined
};

const getters = {
    calculatedPrice: (state) => state.calculatedPrice
};

const actions = {
    async fetchTransporterCalculatePrice({ commit }, transporterId) {
        await get(`transporters/${transporterId}/price`, calculatedPrice => commit('setCalculatedPrice', calculatedPrice));
    }
};

const mutations = {
    setCalculatedPrice: (state, calculatedPrice) => (state.calculatedPrice = calculatedPrice)
};

export default {
    state,
    getters,
    actions,
    mutations
};
