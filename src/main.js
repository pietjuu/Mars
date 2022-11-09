import { createApp } from "vue";
import App from "@/App.vue";

import "@/assets/css/reset.css"

let API

fetch("config.json")
    .then(resp => resp.json())
    .then(config => { API = `${config.host ? config.host + '/': ''}${config.group ? config.group + '/' : ''}api/`; });


createApp(App).mount("#app");

export { API }
