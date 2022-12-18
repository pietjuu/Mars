import { createStore } from "vuex";

// modules
import user from "@/store/modules/user";
import notification from "@/store/modules/notification";
import sendItem from "@/store/modules/send-item";
import transporters from "@/store/modules/transporters";
import map from "@/store/modules/map";
import blacklist from "@/store/modules/blacklist";


const store = createStore({
    modules: {
        user,
        notification,
        sendItem,
        transporters,
        map,
        blacklist
    }
});

export default store;
