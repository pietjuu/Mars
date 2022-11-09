import { createApp } from "vue";
import App from "@/App.vue";

const API = require("@/config.json");

createApp(App).mount("#app");

export { API }
