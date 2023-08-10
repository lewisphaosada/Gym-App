import Vue from 'vue'
import Router from 'vue-router'
import Home from '../views/Home.vue'
import Login from '../views/Login.vue'
import Logout from '../views/Logout.vue'
import Register from '../views/Register.vue'
import store from '../store/index'
import Profile from '../views/Profile.vue'
import EditProfile from '../views/EditProfile.vue'
<<<<<<< HEAD
import EmployeeLogin from '../views/EmployeeLogin.vue'
import EmployeePortal from '../views/EmployeePortal.vue'
=======
import ViewSessions from '../views/ViewSessions.vue'
import MachineList from  '../components/MachineList.vue'
import MachineDetails from '../components/MachineDetails.vue'
>>>>>>> f401c3aa63827ace69e6852833eb98c0d52ed89d
Vue.use(Router)
/**
 * The Vue Router is used to "direct" the browser to render a specific view component
 * inside of App.vue depending on the URL.
 *
 * It also is used to detect whether or not a route requires the user to have first authenticated.
 * If the user has not yet authenticated (and needs to) they are redirected to /login
 * If they have (or don't need to) they're allowed to go about their way.
 */
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
      path: "/profile/:id",
      name: "profile",
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
      path: '/sessions',
      name: 'sessions',
      component: ViewSessions,
      meta: {
        requiresAuth: true
      }
    },
    {
<<<<<<< HEAD
      path: '/employee',
      name: 'employeelogin',
      component: EmployeeLogin,
      meta: {
        requiresAuth: false
      }
    },
    {
      path: '/employee-portal',
      name: 'employeeportal',
      component: EmployeePortal,
      meta: {
        requiresAuth: false
      }
    }
  ]
})
=======
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

>>>>>>> f401c3aa63827ace69e6852833eb98c0d52ed89d
router.beforeEach((to, from, next) => {
  const requiresAuth = to.matched.some(x => x.meta.requiresAuth);
  if (requiresAuth && store.state.token === '') {
    next('/login');
  } else {
    next();
  }
});

export default router;
