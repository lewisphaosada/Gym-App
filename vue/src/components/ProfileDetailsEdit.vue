<template>
<div>
<h2>Edit Profile</h2>
  <form v-on:submit.prevent="submitForm">

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
        <span v-if="hasValidationError" class="error-message">Sex must be one character: M, F or O</span>

    </div>

    <div>
        <label>Weight:</label><input v-model="user.weight"/>
        <span v-if="weightValidationError" class="error-message">Must input numbers</span>

    </div>

    <div>
        <label>Height:</label><input v-model="user.height"/>
        <span v-if="heightValidationError" class="error-message">Must input numbers</span>

    </div>

    <div>
      <label>BMI:</label><input v-model="user.bmi"/>
      <span v-if="bmiValidationError" class="error-message">Must input numbers</span>

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
    hasValidationError: false
    };
    
    },
     computed: {
    weightValidationError() {
      return isNaN(this.user.weight);
    },

    heightValidationError() {
      return isNaN(this.user.height);
    },

    bmiValidationError() {
      return isNaN(this.user.bmi);
    }
  },
methods:{ 
  
  submitForm() {
      if (this.validateForm()) {
        this.saveProfile();
      }
    },

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

     validateForm() {
      if (this.weightValidationError ||this.heightValidationError || this.bmiValidationError ||
        (this.user.sex.length !== 1 && this.user.sex.length !== 0) ||
        (this.user.sex.length === 1 && !['M', 'F', 'O'].includes(this.user.sex.toUpperCase()))
      ) {
        this.hasValidationError = true;
        return false;
      } else {
        this.hasValidationError = false;
        return true;
      }
    },

        cancelEdit(){
        this.$router.push({ name: 'profile', params: { id: this.user.id} });
        },
    
    },

};

</script>



<style scoped>

</style>