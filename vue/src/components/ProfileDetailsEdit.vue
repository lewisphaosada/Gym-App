<template>
<div>
<h2>Edit Profile</h2>
  <form v-on:submit.prevent="saveProfile">

    <div>
        <label>Photo:</label><input v-model="user.photo"/>
    </div>

    <div>
        <label>Name:</label><input v-model="user.name"/>
    </div>

     <div>
        <label>Email:</label><input v-model="user.email"/>
    </div>

     <div>
        <label>Username:</label><input v-model="user.username"/>
    </div>

    <div>
        <label>Password:</label><input v-model="user.password"/>
    </div>

     <div>
        <label>Sex:</label><input v-model="user.sex"/>
    </div>

    <div>
        <label>Weight:</label><input v-model="user.weight"/>
    </div>

    <div>
        <label>Height:</label><input v-model="user.height"/>
    </div>

    <div>
        <label>BMI:</label><input v-model="user.bmi"/>
    </div>

    <button type="submit">Save</button>
    <button v-on:click="cancelEdit">Cancel</button>

 </form>

</div>  
</template>

<script>

import ProfileService from '../services/ProfileService.js'

export default{
    name: 'editprofile',
    data(){
        return{
    user: this.$store.state.user,
    };
    
    },
methods:{

        saveProfile(){
            ProfileService.updateProfile(this.user.id, this.user).then(response => {
            if(response.status === 200){
            this.$store.commit('SET_USER', this.user);
            this.$router.push({ name: 'profile', params: { id: this.user.id} });
                }
            })
            .catch(error =>{
            if (error.response) {
              this.errorMsg =
                "Error updating profile. Response received was '" +
                error.response.statusText +
                "'.";
            } else if (error.request) {
              this.errorMsg =
                "Error updating profile. Server could not be reached.";
            } else {
              this.errorMsg =
                "Error updating profile. Profile could not be updated.";
            }
          });
        },

        cancelEdit(){
        this.$router.push({ name: 'profile', params: { id: this.user.id} });
        },
    
    },

};

</script>



<style scoped>

</style>