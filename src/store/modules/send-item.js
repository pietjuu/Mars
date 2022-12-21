const state = {
    steps: [
        {
            number: 1,
            text: `STEP 1: Connect to Transporter`,
            inProgress: false
        },
        {
            number: 2,
            text: `STEP 2: Item Details`,
            inProgress: false
        },
        {
            number: 3,
            text: `STEP 3: Destination Details`,
            inProgress: false
        },
        {
            number: 4,
            text: `STEP 4: Destination Check`,
            inProgress: false
        },
        {
            number: 5,
            text: `STEP 5: Confirm`,
            inProgress: false
        }

    ]
};

const getters = {
    sendSteps: (state) => state.steps
};

const actions = {

};

const mutations = {

};

export default {
    state,
    getters,
    actions,
    mutations
};
