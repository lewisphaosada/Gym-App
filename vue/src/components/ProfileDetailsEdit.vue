<template>
<div>
<h2>Edit Profile</h2>
  <form v-on:submit.prevent="saveProfile">

    <div>
        <label>Name:</label><input v-model="editUser.name"/>
    </div>

     <div>
        <label>Email:</label><input v-model="editUser.email"/>
    </div>

     <div>
        <label>Username:</label><input v-model="editUser.username"/>
    </div>

    <div>
        <label>Password:</label><input v-model="editUser.password"/>
    </div>

    <div>
        <label>Photo:</label><input v-model="editUser.photo"/>
    </div>

    <button type="submit">Save</button>
    <button v-on:click="cancelEdit">Cancel</button>

 </form>

</div>  
</template>

<script>

import service from '../services/service.js'

export default{
    name: 'editprofile',
    data(){
        return{
            editUser:{
      name: '',
      email: '',
      username: '',
      password: '',
      photo: ''
    }
    };
    },
methods:{

        saveProfile(){
            const userID = this.$store.state.user.userID;
            service.updateProfile(userID, this.editUser).then(response => {
            if(response.status === 200){
            this.$store.commit('EDIT_PROFILE_STATE');
            this.resetUser();
            this.$router.push('/profile');
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
        this.$store.commit('EDIT_PROFILE_STATE');
        this.resetUser();
        this.$router.push('/profile');
        },

        resetUser() {
        this.editUser = {
        name: '',
        email: '',
        username: '',
        password: '',
        photo: '',
      };
    },
    
    },
created(){
    const user = this.$store.state.user;

    this.editedUser.name = user.name;
    this.editedUser.email = user.email;
    this.editedUser.username = user.username;
    this.editedUser.photo = user.photo;
    }
};

</script>



<style scoped>

</style>