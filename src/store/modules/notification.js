const state = {
    content: "",
    type: "info",
    show: false
};

const getters = {
    notificationContent: (state) => state.content,
    notificationType: (state) => state.type,
    notificationShow: (state) => state.show
};

const actions = {
    createNotification({ commit }, options){
        const { content, type="info" } = options;

        commit('setNotificationContent', content);
        commit('setNotificationType', type);
        commit('setNotificationShow', true);
    },
    removeNotification({ commit }) {
        commit('setNotificationContent', "");
        commit('setNotificationType', "info");
        commit('setNotificationShow', false);
    }
};

const mutations = {
    setNotificationContent: (state, content) => (state.content = content),
    setNotificationType: (state, type) => (state.type = type),
    setNotificationShow: (state, show) => (state.show = show)
};

export default {
    state,
    getters,
    actions,
    mutations
};
