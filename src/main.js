import { createApp } from "vue";
import App from "@/App.vue";
import router from './router'

const API = require("@/config.json");

createApp(App).use(router).mount("#app");

export { API }
