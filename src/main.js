import { createApp } from "vue";
import App from "@/App.vue";

import router from './router';
import store from './store';

// setup Shippert API
const CONFIG = require("@/config.json");
const API = `${CONFIG.host ? CONFIG.host + '/': ''}${CONFIG.group ? CONFIG.group + '/' : ''}api/`;

// Simulate identifier that is included in watch
import { saveToStorage, loadFromStorage } from "@/assets/js/data-connector/local-storage-abstractor";
saveToStorage('userId', "T-1");

const TOKEN = loadFromStorage('userId');


// Create and setup app
const app = createApp(App);
app.use(router);
app.use(store);
app.mount("#app");

export { API, TOKEN };
