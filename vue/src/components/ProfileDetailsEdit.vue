<template>
<div>
<h2>Edit Profile</h2>
  <form v-on:submit.prevent="saveProfile">

    <div>
        <label>Photo:</label><input v-model="editUser.photo"/>
    </div>

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
        <label>Sex:</label><input v-model="editUser.sex"/>
    </div>

    <div>
        <label>Weight:</label><input v-model="editUser.weight"/>
    </div>

    <div>
        <label>Height:</label><input v-model="editUser.height"/>
    </div>

    <div>
        <label>BMI:</label><input v-model="editUser.bmi"/>
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
    editUser:{
      photo: '',
      name: '',
      email: '',
      username: this.$store.state.user.username,
      password: '',
      sex:'',
      weight: '',
      height: '',
      bmi: ''
    }
    };
    },
methods:{

        saveProfile(){
            ProfileService.updateProfile(this.$store.state.user.id, this.editUser).then(response => {
            if(response.status === 200){
            this.$store.commit('SET_USER', this.editUser);
            this.$router.push({ name: 'profile', params: { id: this.$store.state.user.id} });
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
        this.$router.push({ name: 'profile', params: { id: this.$store.state.user.id} });
        },
    
    },
// created(){
//     const user = this.$store.state.user;

//     this.editUser.photo = user.photo;
//     this.editUser.name = user.name;
//     this.editUser.email = user.email;
//     this.editUser.username = user.username;
//     this.editUser.weight = user.weight;
//     this.editUser.height = user.height;
//     this.editUser.bmi = user.bmi;
    
//     }
};

</script>



<style scoped>

</style>