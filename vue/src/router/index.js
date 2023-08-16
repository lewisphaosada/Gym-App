import Vue from 'vue'
import Router from 'vue-router'
import Home from '../views/Home.vue'
import Login from '../views/Login.vue'
import Logout from '../views/Logout.vue'
import Register from '../views/Register.vue'
import store from '../store/index'
import Profile from '../views/Profile.vue'
import EditProfile from '../views/EditProfile.vue'
import ViewSessions from '../views/ViewSessions.vue'
import MachineList from '../components/MachineList.vue'
import MachineDetails from '../components/MachineDetails.vue'
import EmployeeLogin from '../components/EmployeeLogin.vue';
import EmployeePortal from '../views/EmployeePortal.vue'
import EmployeeRegister from '@/components/EmployeeRegister.vue'
import Schedule from '../views/Schedule.vue'
import GoalDetails from '../views/Goals.vue'
import SingleSession from '../views/SingleSession.vue'
import Workout from '../components/Workout.vue'

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
        redirectToEmployeePortal: true, // New meta property
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
      path: '/profile/:id/edit',
      name: 'editprofile',
      component: EditProfile,
      meta: {
        requiresAuth: true,
      }
    },
    {
      path: '/schedule',
      name: 'schedule',
      component: Schedule,
      meta: {
        requiresAuth: true,
      }
    },
    {
      path: '/sessions/:id',
      name: 'sessions',
      component: ViewSessions,
      meta: {
        requiresAuth: true
      }
    },
    {
      path: '/session/:id',
      name: 'session',
      component: SingleSession,
      meta: {
        requiresAuth: true
      }
    },
    {
      path: '/employee-login',
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
        requiresAuth: true
      }
    },
    {
      path: '/employee-register',
      name: 'employeeregister',
      component: EmployeeRegister,
      meta: {
        requiresAuth: true
      }
    },
    {
      path: '/machines',
      name: 'MachineList', 
      component: MachineList,
      props: true,
      meta: {
        requiresAuth: true,
      },
    },
    // {
    //   path: '/monthly-equipment-usage',
    //   name: 'monthlyEquipmentUsage',
    //   component: MonthlyEquipmentUsage,
    //   meta: {
    //     requiresAuth: true,
    //   },
    // },
    {
      path: '/exercises/:exerciseId',
      name: "MachineDetails",
      component: MachineDetails,
      props: true,
      meta: {
        requiresAuth: true,
      },
    },
    {
      path: "/workouts",
      name: "WorkoutPage",
      component: Workout,
    },
    {
      path:'/goals',
      name: 'goals',
      component: GoalDetails,
      meta:{
        requiresAuth: true,
      }
    },
  ]
})

router.beforeEach((to, from, next) => {
  const requiresAuth = to.matched.some(x => x.meta.requiresAuth);
  if (requiresAuth && store.state.token === '') {
    next('/login');
  } else if (to.matched.some(x => x.meta.redirectToEmployeePortal && store.state.token !== '')) {
    next('/employee-portal');
  } else {
    next();
  }
});

export default router;
