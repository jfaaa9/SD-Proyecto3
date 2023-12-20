// Importa Vue y Vue Router
import { createRouter, createWebHistory } from 'vue-router';

// Importa tus componentes
import LoginComponent from '../components/Login.vue';
import ChatComponent from '../components/ChatComponent.vue';
import HomePage from '../components/Home.vue';

// Define las rutas
const routes = [
    { path: '/', component: HomePage }, 
    { path: '/login', component: LoginComponent },
    {
        path: '/chat',
        component: ChatComponent,
        beforeEnter: (to, from, next) => {
          if (localStorage.getItem('userLoggedIn')) {
            next();
          } else {
            next('/login');
          }
        }
      }
    //{ path: '/chat', component: ChatComponent }
];

// Crea el enrutador
const router = createRouter({
  history: createWebHistory(),
  routes,
});

export default router;
