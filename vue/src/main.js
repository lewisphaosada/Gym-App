import Vue from 'vue'
import App from './App.vue'
import router from './router/index'
import store from './store/index'
import axios from 'axios'
// import FullCalendar from 'vue-full-calendar';
// import 'vue-full-calendar/dist/fullcalendar.min.css';


// import datePicker from 'vue-bootstrap-datetimepicker';

Vue.config.productionTip = false

axios.defaults.baseURL = process.env.VUE_APP_REMOTE_API;

// Vue.use(datePicker);



// Vue.use(FullCalendar);
// Vue.config.productionTip = false;

new Vue({
  router,
  store,
  render: h => h(App)
}).$mount('#app')
