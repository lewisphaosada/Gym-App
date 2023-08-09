<template>
  <div class="profile-container">
    <h2 class="profile-heading">Profile</h2>
    
    <div class="profile-details">
      <div class="profile-picture">
        <img v-if="user.photo" :src="user.photo" alt="User Photo" />
        <div v-else class="empty-profile-picture"></div>
      </div>
          <div class="profile-info">
        <div class="profile-details"><label>Name:</label> {{ user.name }} </div>
        <div class="profile-details"><label>Email:</label> {{ user.email }} </div>
        <div class="profile-details"><label>Username:</label> {{ user.username }} </div>
        <div class="profile-details"><label>Password:</label> {{ user.password }} </div>
        <router-link class="profile-edit-button" to="/edit">Edit Profile</router-link>
      </div>
    </div>
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

.profile-container {
  max-width: 600px;
  margin: 0 auto;
  padding: 20px;
  background-color: #f6f6f6;
  border: 1px solid #ccc;
  border-radius: 5px;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
  text-align: center;
}

.profile-heading {
  font-size: 24px;
  margin-bottom: 20px;
}

.profile-details {
  position: relative;
  background-color: #f6f6f6;
  border: 1px solid #ccc;
  border-radius: 5px;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
  padding: 20px;
}

.profile-details label {
  display: block;
  font-weight: bold;
  margin-bottom: 5px;
}

.profile-details p {
  margin: 0;
}

.profile-edit-button {
  position: absolute;
  top: 10px;
  right: 10px;
  padding: 10px 20px;
  background-color: #007bff;
  color: #fff;
  border: none;
  border-radius: 3px;
  font-size: 16px;
  cursor: pointer;
}

.profile-picture {
  text-align: center;
  margin-bottom: 20px;
  position: relative;
}

.profile-picture img {
  width: 120px;
  height: 120px;
  object-fit: cover;
  border-radius: 50%;
  border: 4px solid #fff;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.2);
}
.empty-profile-picture {
  width: 120px;
  height: 120px;
  border: 4px dashed #ccc;
  border-radius: 50%;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.2);
  display: inline-block;
}

</style>