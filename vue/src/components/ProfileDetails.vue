<template>

  <div class="profile">
    <h2>Profile</h2>

    <div><label>Name:</label> {{ user.name }} </div>

    <div><label>Email:</label> {{ user.email }} </div>

    <div><label>Username:</label> {{ user.username }} </div>

    <div><label>Password:</label> {{user.password}} </div>

    <div><label>Profile Picture:</label><img :src="user.photo" alt="User Photo" /></div>

    <router-link to="/edit">Edit Profile</router-link>

</div>
    
</template>

<script>


export default {
  name: 'profile',
  computed: {
    user() {
      return this.$store.state.user;
    },
  },
  created() {
    this.retrieveProfile();
  },
  methods:{
    retrieveProfile(){
  
    }
  },
};

</script>

<style scoped>

</style>