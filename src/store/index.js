import { createStore } from "vuex";

// modules
import user from "@/store/modules/user";
import notification from "@/store/modules/notification";
import sendItem from "@/store/modules/send-item";


const store = createStore({
    modules: {
        user,
        notification,
        sendItem
    }
})

export default store;
