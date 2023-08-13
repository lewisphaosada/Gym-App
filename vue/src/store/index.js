import Vue from 'vue'
import Vuex from 'vuex'
import axios from 'axios'
import SessionService from '../services/SessionService.js'

Vue.use(Vuex)

/*
 * The authorization header is set for axios when you login but what happens when you come back or
 * the page is refreshed. When that happens you need to check for the token in local storage and if it
 * exists you should set the header so that it will be attached to each request
 */
const currentToken = localStorage.getItem('token')
const currentUser = JSON.parse(localStorage.getItem('user'));

if(currentToken != null) {
  axios.defaults.headers.common['Authorization'] = `Bearer ${currentToken}`;
}

export default new Vuex.Store({
  state: {
    token: currentToken || '',
    user: currentUser || {},
    isCheckedIn: false,
    currentSessionId: 0,
    sessionTimerStart: 0,
    sessionTimerEnd: 0,
    sessionTimerElapsed: 0
},
  mutations: {
    SET_AUTH_TOKEN(state, token) {
      state.token = token;
      localStorage.setItem('token', token);
      axios.defaults.headers.common['Authorization'] = `Bearer ${token}`
    },
    SET_USER(state, user) {
      state.user = user;
      localStorage.setItem('user',JSON.stringify(user));
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
      SessionService.create({userId: state.user.id, date: state.sessionTimerStart}).then(result => {
        state.currentSessionId = result.data.sessionId;
      });
    },
    STOP_TIMER(state) {
      state.sessionTimerEnd = Date.now();
      state.sessionTimerElapsed = state.sessionTimerEnd - state.sessionTimerStart;
      SessionService.update(state.currentSessionId, {userId: state.user.id, duration: state.sessionTimerElapsed, date: state.sessionTimerStart});
      state.sessionTimerStart = 0;
      state.sessionTimerEnd = 0;
    }
  }
})
