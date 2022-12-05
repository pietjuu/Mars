import { createStore } from "vuex";

// modules
import user from "@/store/modules/user";
import notification from "@/store/modules/notification";
import sendItem from "@/store/modules/send-item";
import transporters from "@/store/modules/transporters";
import map from "@/store/modules/map";


const store = createStore({
    modules: {
        user,
        notification,
        sendItem,
        transporters,
        map
    }
});

export default store;
