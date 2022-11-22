import { createStore } from "vuex";

// modules
import user from "@/store/modules/user";
import notification from "@/store/modules/notification";
import sendItem from "@/store/modules/send-item";
import transporters from "@/store/modules/transporters";
import mapDestinations from "@/store/modules/map-destinations";


const store = createStore({
    modules: {
        user,
        notification,
        sendItem,
        transporters,
        mapDestinations
    }
});

export default store;
