import { createApp } from "vue";
import App from "@/App.vue";

import router from './router';
import store from './store';

// setup Shippert API
const CONFIG = require("@/config.json");
const API = `${CONFIG.host ? CONFIG.host + '/': ''}${CONFIG.group ? CONFIG.group + '/' : ''}api/`;

// Simulate identifier that is included in watch
import { saveToStorage, loadFromStorage } from "@/assets/js/data-connector/local-storage-abstractor";
saveToStorage('userId', "bcae138d-5057-4a51-98dd-772e55c781d3");

const TOKEN = loadFromStorage('userId');


// Create and setup app
const app = createApp(App);
app.use(router);
app.use(store);
app.mount("#app");

export { API, TOKEN }
