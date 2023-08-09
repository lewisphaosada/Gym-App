<template>
  <div id="app">
    <nav id="nav">
      <router-link v-bind:to="{ name: 'home' }" class="nav-link">Home</router-link>
      <span class="divider">|</span>
      <router-link v-bind:to="{ name: 'register' }" class="nav-link">Register</router-link>

      <span class="divider" >|</span> <!-- should this vertical line also have v-if="isAuthenticated"? -->
      
      <router-link v-if="isAuthenticated" v-bind:to="{ name: 'profile' }" class="nav-link">Profile</router-link>
      <span class="divider" v-if="isAuthenticated">|</span>
      <router-link v-if="isAuthenticated" v-bind:to="{ name: 'logout' }" class="nav-link">Logout</router-link>
    </nav>
    <router-view />
  </div>
</template>

<script>
export default {
  name: "App",
  computed: {
    isAuthenticated() {
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
  color: #2c3e50;
}

#nav {
  padding: 15px;
  background-color: #f3f3f3; /* Grey background color */
  border-bottom: 1px solid #ddd;
  display: flex;
  justify-content: center;
  align-items: center;
}

.nav-link {
  margin: 0 10px;
  text-decoration: none;
  color: #000; /* Black navigation link color for Home, Register, Profile, and Logout */
}

.divider {
  margin: 0 5px;
  color: #999;
}

</style>





