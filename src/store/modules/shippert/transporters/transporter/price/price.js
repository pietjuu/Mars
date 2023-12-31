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
    ],
    priceRequestState: {
        inProgress: false
    }
};

const getters = {
    calculatedPrice: (state) => state.calculatedPrice,
    stepsToCalculatePrice: (state) => state.stepsToCalculatePrice,
    priceRequestState: (state) => state.priceRequestState
};

const actions = {
    async calculatePrice({ commit }, transporterId) {
        commit('setCalculatedPrice', undefined);
        commit('setPriceRequestState', { inProgress: true });
        await get(`transporters/${transporterId}/price`, calculatedPrice => {
            commit('setCalculatedPrice', calculatedPrice.price);
            commit('setContinueToCalculatedPriceStep', 2);
        }).then(() => commit('setPriceRequestState', { inProgress: false }));
    },
    continueToCalculatedPriceStep({ commit }, number) {
        commit('setContinueToCalculatedPriceStep', number);
    },
    resetCalculatedPrice({ commit }) {
        commit('setCalculatedPrice', undefined);
    }
};

const mutations = {
    setCalculatedPrice: (state, calculatedPrice) => (state.calculatedPrice = calculatedPrice),
    setContinueToCalculatedPriceStep: (state, number) => {
        state.stepsToCalculatePrice.forEach(step => {
           step.inProgress = step.number === number;
        });
    },
    setPriceRequestState: (state, priceRequestState) => (state.priceRequestState = priceRequestState)
};

export default {
    state,
    getters,
    actions,
    mutations
};
