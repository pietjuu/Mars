import { createStore } from "vuex";

// modules
import user from "@/store/modules/user";
import notification from "@/store/modules/notification";


const store = createStore({
    modules: {
        user,
        notification
    }
})

export default store;
