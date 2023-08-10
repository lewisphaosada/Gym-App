import Vue from 'vue';
import Router from 'vue-router';
import Home from '../views/Home.vue';
import Login from '../views/Login.vue';
import Logout from '../views/Logout.vue';
import Register from '../views/Register.vue';
import Profile from '../views/Profile.vue';
import EditProfile from '../views/EditProfile.vue';
import MachineList from '../components/MachineList.vue'; 
import MachineDetails from '../components/MachineDetails.vue'; 
import store from '../store/index';

Vue.use(Router);

const router = new Router({
  mode: 'history',
  base: process.env.BASE_URL,
  routes: [
    {
      path: '/',
      name: 'home',
      component: Home,
      meta: {
        requiresAuth: true,
      },
    },
    {
      path: '/login',
      name: 'login',
      component: Login,
      meta: {
        requiresAuth: false,
      },
    },
    {
      path: '/logout',
      name: 'logout',
      component: Logout,
      meta: {
        requiresAuth: false,
      },
    },
    {
      path: '/register',
      name: 'register',
      component: Register,
      meta: {
        requiresAuth: false,
      },
    },
    {
      path: '/profile',
      name: 'profile',
      component: Profile,
      meta: {
        requiresAuth: true,
      },
    },
    {
      path: '/edit',
      name: 'editprofile',
      component: EditProfile,
      meta: {
        requiresAuth: true,
      },
    },
    {
      path: '/machines',
      name: 'MachineList', 
      component: MachineList,
      meta: {
        requiresAuth: true,
      },
    },
    {
      path: '/machine/:id',
      name: 'MachineDetails', 
      component: MachineDetails,
      meta: {
        requiresAuth: true,
      },
    },
  ],
});

router.beforeEach((to, from, next) => {
  const requiresAuth = to.matched.some(x => x.meta.requiresAuth);
  if (requiresAuth && store.state.token === '') {
    next('/login');
  } else {
    next();
  }
});

export default router;
