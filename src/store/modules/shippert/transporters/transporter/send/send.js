import { post } from "@/assets/js/data-connector/api-communication-abstractor";

const state = {
    linkId: undefined,
    origin: undefined,
    destination: undefined,
    receiver: undefined,
    itemName: undefined,
    stepsToSendItem: [
        {
            number: 1,
            text: `STEP 1: Connect to Transporter`,
            inProgress: false,
            info: 'To send your item, we need to know which Transporter you’re sending from.'
                + ' To connect to your transporter, whom you’re sending from, you can either scan the QR-code on the Transporter or enter the Transporter ID manually.'
                + ' Afterwards you can click “Link”.'
        },
        {
            number: 2,
            text: `STEP 2: Item Details`,
            inProgress: false,
            info: undefined
        },
        {
            number: 3,
            text: `STEP 3: Destination Details`,
            inProgress: false,
            info: undefined
        },
        {
            number: 4,
            text: `STEP 4: Destination Check`,
            inProgress: false,
            info: undefined
        },
        {
            number: 5,
            text: `STEP 5: Confirm`,
            inProgress: false,
            info: undefined
        }

    ]
};

const getters = {
    stepsToSendItem: (state) => state.stepsToSendItem
};

const actions = {
    async initSend({ commit }, transporterId) {
        commit('setCalculatedPrice', undefined);
        commit('setAllSendItemState', undefined);

        await post(`transporters/${transporterId}/init`, {}, json => {
            commit('setOrigin', transporterId);
            commit('setCalculatedPrice', json.price);
            commit('setLinkId', json.linkID);
            commit('setContinueToSendItemStep', 2);
        });
    },
    saveItemName({ commit }, name) {
      commit('setItemName', name);
    },
    continueToSendItemStep({ commit }, number) {
        commit('setContinueToSendItemStep', number);
    }
};

const mutations = {
    setAllSendItemState: (state, value) => {
      state.linkId = value;
      state.itemName = value;
      state.origin = value;
      state.destination = value;
      state.receiver = value;
    },
    setLinkId: (state, linkId) => (state.linkId = linkId),
    setOrigin: (state, transporterId) => (state.origin = transporterId),
    setItemName: (state, name) => (state.itemName = name),
    setDestination: (state, transporterId) => (state.destination = transporterId),
    setReceiver: (state, userId) => (state.receiver = userId),
    setContinueToSendItemStep: (state, number) => {
        state.stepsToSendItem.forEach(step => {
           step.inProgress = step.number === number;
        });
    }
};

export default {
    state,
    getters,
    actions,
    mutations
};
