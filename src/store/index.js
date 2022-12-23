import { createStore } from "vuex";

// modules
import shippertBlacklist from "@/store/modules/shippert/blacklist/blacklist";

import users from "@/store/modules/shippert/users/users";
import user from "@/store/modules/shippert/users/user/user";
import userItems from "@/store/modules/shippert/users/user/items/items";
import userItem from "@/store/modules/shippert/users/user/items/item";
import userBlacklist from "@/store/modules/shippert/users/user/blacklist/blacklist";

import transporters from "@/store/modules/shippert/transporters/transporters";
import transporter from "@/store/modules/shippert/transporters/transporter/transporter";
import price from "@/store/modules/shippert/transporters/transporter/price/price";
import send from "@/store/modules/shippert/transporters/transporter/send/send";

import notification from "@/store/modules/notification";
import map from "@/store/modules/map";


const store = createStore({
    modules: {
        users,
        user,
        userItems,
        userItem,
        userBlacklist,
        notification,
        transporters,
        transporter,
        price,
        send,
        map,
        shippertBlacklist
    }
});

export default store;
