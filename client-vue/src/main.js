if (typeof global === "undefined") {
  window.global = window;
}

import { createApp } from 'vue';
import './style.css';
import App from './App.vue';
import router from './router/routes'; // Importa el router

createApp(App)
.use(router) // Utiliza el router
.mount('#app');
