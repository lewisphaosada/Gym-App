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
        <span v-if="hasValidationError" class="error-message">Must input numbers</span>

    </div>

    <div>
        <label>Height:</label><input v-model="user.height"/>
        <span v-if="hasValidationError" class="error-message">Must input numbers</span>

    </div>

    <div>
      <label>BMI:</label><input v-model="user.bmi"/>
      <span v-if="hasValidationError" class="error-message">Must input numbers</span>

    </div>

    <button type="submit">Save</button>
    <button v-on:click="cancelEdit">Cancel</button>

 </form>

</div>  
</template>

<script>
import UserService from '../services/UserService.js'

export default{
    name: 'editprofile',
    data(){
        return{
    user: this.$store.state.user,
    hasValidationError: false,
     sexValidationError: false,
      weightValidationError: false,
      heightValidationError: false,
      bmiValidationError: false,
    };
    
    },

methods:{
   submitForm() {
      this.validateForm();
      if (!this.hasValidationError) {
        this.saveProfile();
      }
    },
        saveProfile(){
            UserService.updateProfile(this.user.id, this.user).then(response => {
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
      this.hasValidationError = false;

      if (
        (this.user.sex.length !== 1 && this.user.sex.length !== 0) ||
        (this.user.sex.length === 1 && !['M', 'F', 'O'].includes(this.user.sex.toUpperCase()))
      ) {
        this.sexValidationError = true;
        this.hasValidationError = true;
      } else {
        this.sexValidationError = false;
      }

      if (isNaN(this.user.weight)) {
        this.weightValidationError = true;
        this.hasValidationError = true;
      } else {
        this.weightValidationError = false;
      }

      if (isNaN(this.user.height)) {
        this.heightValidationError = true;
        this.hasValidationError = true;
      } else {
        this.heightValidationError = false;
      }

      if (isNaN(this.user.bmi)) {
        this.bmiValidationError = true;
        this.hasValidationError = true;
      } else {
        this.bmiValidationError = false;
      }
    },

        cancelEdit(){
        this.$router.push({ name: 'profile', params: { id: this.user.id} });
        },
    
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
  margin-bottom: 10px;
  font-size: 16px;
}

.profile-details label {
  display: block;
  font-weight: bold;
  margin-bottom: 5px;
}
</style>