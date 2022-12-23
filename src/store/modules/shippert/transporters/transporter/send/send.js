import {post, put} from "@/assets/js/data-connector/api-communication-abstractor";
import store from "@/store";

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

    ],
    sendChecks: undefined
};

const getters = {
    stepsToSendItem: (state) => state.stepsToSendItem,
    origin: (state) => state.origin,
    itemName: (state) => state.itemName,
    destination: (state) => state.destination,
    receiver: (state) => state.receiver,
    sendChecks: (state) => state.sendChecks
};

const actions = {
    async initSend({ commit }, origin) {
        commit('setCalculatedPrice', undefined);
        commit('setAllSendItemState', undefined);

        await post(`transporters/${origin.id}/init`, {}, json => {
            commit('setOrigin', origin);
            commit('setCalculatedPrice', json.price);
            commit('setLinkId', json.linkID);
            commit('setContinueToSendItemStep', 2);
        });
    },
    async finalizeLink({ commit }) {
        const body = {
            destination: state.destination.id,
            receiver: state.receiver.id,
            itemName: state.itemName
        };

        await put(`transporters/${state.origin.id}/link/${state.linkId}`, body,
            () => {
                // should be response of server but no time...
                const checks = [
                    { check: "Item allowed by Shippert?",  value: true },
                    { check: "Item allowed by recipient?", value: true },
                    { check: "Destination available?", value: true },
                    { check: "Does item fit destination?", value: true }
                ];
                commit('setSendChecks', checks);
                commit('setContinueToSendItemStep', 4);
            },
            (response) => {
                response.json().then(error => {
                    // should be response of server but no time...
                    const checks = [
                        { check: "Item allowed by Shippert?",  value: true },
                        { check: "Item allowed by recipient?", value: true },
                        { check: "Destination available?", value: true },
                        { check: "Does item fit destination?", value: false }
                    ];
                    commit('setSendChecks', checks);
                    commit('setContinueToSendItemStep', 4);
                });
            });

    },
    async sendItem({ commit }) {
        await post(`transporters/${state.origin.id}/link/${state.linkId}/send`, {}, json => {
            store.dispatch('createNotification', {content: 'Item has been sent', type: `success`});
            commit('setCalculatedPrice', undefined);
            commit('setAllSendItemState', undefined);
        });
    },
    saveItemName({ commit }, name) {
        commit('setItemName', name);
    },
    saveReceiver({ commit }, id) {
        commit('setReceiver', id);
    },
    saveDestination({ commit }, id) {
        commit('setDestination', id);
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
      state.sendChecks = value;
    },
    setLinkId: (state, linkId) => (state.linkId = linkId),
    setOrigin: (state, origin) => (state.origin = origin),
    setItemName: (state, name) => (state.itemName = name),
    setDestination: (state, destination) => (state.destination = destination),
    setReceiver: (state, receiver) => (state.receiver = receiver),
    setSendChecks: (state, checks) => (state.sendChecks = checks),
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
