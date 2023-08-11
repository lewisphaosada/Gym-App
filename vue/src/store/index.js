import Vue from 'vue';
import Vuex from 'vuex';
import axios from 'axios';
import machinesModule from './machines';


Vue.use(Vuex);

const currentToken = localStorage.getItem('token');
const currentUser = JSON.parse(localStorage.getItem('user'));

if (currentToken != null) {
  axios.defaults.headers.common['Authorization'] = `Bearer ${currentToken}`;
}

export default new Vuex.Store({
  state: {
    token: currentToken || '',
    user: currentUser || {},
    // currentUser: {},
    isCheckedIn: false,
    sessionTimerStart: 0,
    sessionTimerEnd: 0,
},
  mutations: {
    SET_AUTH_TOKEN(state, token) {
      state.token = token;
      localStorage.setItem('token', token);
      axios.defaults.headers.common['Authorization'] = `Bearer ${token}`;
    },
    SET_USER(state, user) {
      state.user = user;
      localStorage.setItem('user',JSON.stringify(user));
      // state.currentUser = UserService.getUserByUsername(user.username);
    },
    LOGOUT(state) {
      localStorage.removeItem('token');
      localStorage.removeItem('user');
      state.token = '';
      state.user = {};
      axios.defaults.headers.common = {};
    },
    TOGGLE_CHECK(state) {
      state.isCheckedIn = !state.isCheckedIn;
    },
    START_TIMER(state) {
      state.sessionTimerStart = Date.now();
    },
    STOP_TIMER(state) {
      state.sessionTimerEnd = Date.now();
      // const sessionElapsed = state.sessionTimerEnd - state.sessionTimerStart;
      // SessionService.create({user_id: state.user.id, duration: sessionElapsed, date: state.sessionTimerStart});
      console.log(state.sessionTimerStart, state.sessionTimerEnd)
      state.sessionTimerStart = 0;
      state.sessionTimerEnd = 0;
    }
  },
  modules: {
    machines: machinesModule,
    
  }
});
