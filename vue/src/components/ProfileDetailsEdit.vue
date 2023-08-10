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
        <span v-if="sexValidationError" class="error-message">Sex must be one character: M, F or O</span>

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
      <label>BMI:</label><input v-model="calculatedBMI"/>
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
      originalUser: { ...this.$store.state.user },
      hasValidationError: false,
      sexValidationError: false,
      weightValidationError: false,
      heightValidationError: false,
      bmiValidationError: false,
    };
    
    },  
    computed: {
    user() {
      return { ...this.originalUser};
    },
        calculatedBMI() {
      if (!isNaN(this.user.weight) && !isNaN(this.user.height)) {
        const weight = parseFloat(this.user.weight);
        const height = parseFloat(this.user.height) / 100; 
        return (weight / (height * height)).toFixed(2);
      }
      return '';
    },
  },

methods:{
    submitForm() {
      this.validateForm();
      if (this.hasValidationError) {
        return;
      }
      this.saveProfile();
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
    this.sexValidationError = this.user.sex && (this.user.sex.length !== 1 || !['M', 'F', 'O'].includes(this.user.sex.toUpperCase()));
    this.weightValidationError = isNaN(this.user.weight);
    this.heightValidationError = isNaN(this.user.height);
    this.bmiValidationError = isNaN(this.user.bmi);

    // Check if any validation error exists
    this.hasValidationError = this.sexValidationError || this.weightValidationError || this.heightValidationError || this.bmiValidationError;
  },
    //   openImageUpload() {
    //   this.$refs.imageInput.click();
    // },
    // handleFileUpload(event) {
    //   const file = event.target.files[0];
    //   if (file) {
    //     const formData = new FormData();
    //     formData.append('photo', file);
    //     ProfileService.uploadImage(formData)
    //       .then(response => {
    //         this.editUser.photo = response.data.photoUrl;
    //       })
    //       .catch(error => {
    //         console.error('Error uploading image:', error);
    //       });
    //   }
    // },
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