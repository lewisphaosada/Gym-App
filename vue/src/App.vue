<template>
  <div id="app">
    <nav id="nav">
      <router-link v-bind:to="{ name: 'home' }" class="nav-link">Home</router-link>
      <span class="divider">|</span>
      <router-link v-if="!isAuthenticated" v-bind:to="{ name: 'register' }" class="nav-link">Register</router-link>
      <router-link v-if="isAuthenticated" :to="{ name: 'profile', params: { id: $store.state.user.id } }" class="nav-link">Profile</router-link>
      <span class="divider" v-if="isAuthenticated">|</span>
      <router-link v-if="isAuthenticated" :to="{ name: 'sessions', params: { id: $store.state.user.id } }" class="nav-link">Your Sessions</router-link>
      <span class="divider" v-if="isAuthenticated">|</span>
      <router-link v-if="isAuthenticated" :to="{ name: 'schedule' }" class="nav-link">Schedule</router-link>
      <span class="divider" v-if="isAuthenticated">|</span>
      <router-link v-if="isAuthenticated" :to="{path: '/machines'}" class="nav-link">Workouts</router-link>
      <span class="divider" v-if="isAuthenticated">|</span>
      <router-link v-if="isAuthenticated" :to="{ name: 'goals', params: { id: $store.state.user.id } }" class="nav-link">Goals</router-link>
      <span class="divider" v-if="isAuthenticated">|</span>
      <!-- <router-link v-if="isAuthenticated" v-bind:to="{ name: 'logout' }" class="nav-link">Logout</router-link>
      <span class="divider" v-if="isAuthenticated">|</span> -->
      <!-- <router-link v-if="isAuthenticated" v-bind:to="{ name: 'logout' }" class="nav-link" @click="populateUserRole()">Logout</router-link>
      <span class="divider" v-if="isEmployee() && isAuthenticated">|</span> -->
      <router-link  v-if="isEmployee() && isAuthenticated" :to="{ name: 'employeeportal' }" class="nav-link">Employee Portal</router-link>
      <span class="divider" v-if="isEmployee() && isAuthenticated">|</span>
      <router-link to="/monthly-equipment-usage" class="nav-link" v-if="isEmployee() && isAuthenticated">Equipment Usage</router-link>
      <span class="divider" v-if="isAdmin() && isAuthenticated">|</span>
      <router-link to="/employee-register" class="nav-link" v-if="isAdmin() && isAuthenticated">Employee Register</router-link>

      <!-- <router-link to="/employee-portal" class="nav-link">Employee Portal</router-link>
      <span class="divider" v-if="isAuthenticated">|</span> -->

     
    </nav>
    <router-view />
  </div>
</template>
<script>
export default {
  name: "App",
  userRole: '',
  created() {
    this.populateUserRole();
  },
  watch: {
    // eslint-disable-next-line no-unused-vars
    $route (to, from){
      this.populateUserRole();
    }
  },
  computed: {
    isAuthenticated() {
      return this.$store.state.token !== '';
    }
  },
  methods: {
    populateUserRole() {
      if(this.isAuthenticatedMethod()){
        this.userRole = this.$store.state.user.authorities[0].name;
      }
    },
    isEmployee() {
      if(this.userRole === 'ROLE_EMPLOYEE' || this.userRole === 'ROLE_ADMIN'){
        return true
      } else {
        return false
      }
    },
    isAdmin() {
      if(this.userRole === 'ROLE_ADMIN'){
        return true
      } else {
        return false
      }
    },
    isAuthenticatedMethod() {
      return this.$store.state.token !== '';
    }
  }
};
</script>

<style>
#app {
  font-family: "Avenir", Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2C3E50;
}
#nav {
  padding: 15px;
  background-color: #f3f3f3; 
  border-bottom: 1px solid #ddd;
  display: flex;
  justify-content: center;
  align-items: center;
}
.nav-link {
  margin: 0 10px;
  text-decoration: none;
  color: #000; 
}
.divider {
  margin: 0 5px;
  color: #999;
}
</style>