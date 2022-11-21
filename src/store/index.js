import { createStore } from "vuex";

// modules
import user from "@/store/modules/user";
import notification from "@/store/modules/notification";
import sendItem from "@/store/modules/send-item";
import transporters from "@/store/modules/transporters";


const store = createStore({
    modules: {
        user,
        notification,
        sendItem,
        transporters
    }
});

export default store;
