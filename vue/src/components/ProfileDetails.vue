<template>

  <div class="profile">
    <h2>Profile</h2>

    <div><label>Profile Picture:</label><img :src="user.photo" alt="User Photo" /></div>

    <div><label>Name:</label> {{ user.name }} </div>

    <div><label>Email:</label> {{ user.email }} </div>

    <div><label>Username:</label> {{ user.username }} </div>

    <div><label>Password:</label> {{user.password}} </div>

    <div><label>Sex:</label> {{user.sex}} </div>

    <div><label>Weight:</label> {{user.weight}} </div>

    <div><label>Height:</label> {{user.height}} </div>

    <div><label>BMI:</label> {{user.bmi}} </div>

  <router-link :to="'/profile/' + $store.state.user.id + '/edit'">Edit Profile</router-link>

</div>
    
</template>

<script>
import ProfileService from '../services/ProfileService.js'

export default {
  name: 'profile',
  data() {
    return {
      user: this.$store.state.user,
    };
  },
   created() {
    this.retrieveProfile(this.user.id);
  },
  methods:{
    retrieveProfile(id){
      ProfileService.getProfile(id).then(response =>{
        const userInfo = response.data;

          this.user.photo = userInfo.photo;
          this.user.name = userInfo.name;
          this.user.email = userInfo.email;
          this.user.username = userInfo.username;
          this.user.password = userInfo.password;
          this.user.sex = userInfo.sex;
          this.user.weight = userInfo.weight;
          this.user.height = userInfo.height;
          this.user.bmi = userInfo.bmi;
      })
  
    }
  },
};

</script>

<style scoped>

</style>