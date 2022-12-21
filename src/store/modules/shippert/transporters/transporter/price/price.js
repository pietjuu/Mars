import { get } from "@/assets/js/data-connector/api-communication-abstractor";

const state = {
    calculatedPrice: undefined,
    stepsToCalculatePrice: [
        {
            number: 1,
            text: `STEP 1: Connect to Transporter`,
            inProgress: true,
            info: `To calculate how much it would cost to send your item, you need to connect to a Transporter first.`
                + `To connect to your transporter, whom you’re calculating the send price from, `
                + `you can either scan the QR-code on the Transporter or enter the Transporter ID manually. `
                + `Afterwards you can click “Calculate Price”.`
        },
        {
            number: 2,
            text: `STEP 2: Calculate Price`,
            inProgress: false,
            info: undefined
        }
    ]
};

const getters = {
    calculatedPrice: (state) => state.calculatedPrice,
    stepsToCalculatePrice: (state) => state.stepsToCalculatePrice
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
