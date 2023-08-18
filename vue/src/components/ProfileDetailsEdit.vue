<template>
  <div class="login-container">
    <h1 class="login-heading">Edit Profile</h1>
    <form @submit.prevent="submitForm" class="login-form">
      <div class="form-input-group">
        <label for="photo" class="input-label">Photo URL:</label>
        <input v-model="user.photo" id="photo" class="input-field" />
      </div>

      <div class="form-input-group">
        <label for="name" class="input-label">Name:</label>
        <input v-model="user.name" id="name" class="input-field" />
      </div>
      <div class="form-input-group">
        <label for="email" class="input-label">Email:</label>
        <input v-model="user.email" id="email" class="input-field" />
      </div>

      <div class="form-input-group">
        <label for="username" class="input-label">Username:</label>
        <input v-model="user.username" id="username" class="input-field" />
      </div>

      <div class="form-input-group">
        <label for="sex" class="input-label">Sex:</label>
        <input v-model="user.sex" id="sex" class="input-field" />
        <span v-if="sexValidationError" class="error-message"
          >Sex must be one character: M, F or O</span
        >
      </div>

      <div class="form-input-group">
        <label for="weight" class="input-label">Weight (pounds):</label>
        <input v-model="user.weight" id="weight" class="input-field" />
        <span v-if="weightValidationError" class="error-message"
          >Must input numbers</span
        >
      </div>

      <div class="form-input-group">
        <label for="height" class="input-label">Height (inches):</label>
        <input v-model="user.height" id="height" class="input-field" />
        <span v-if="heightValidationError" class="error-message"
          >Must input numbers</span
        >
      </div>

      <div class="form-input-group">
        <label for="bmi" class="input-label">BMI:</label>
        <input v-model="calculatedBMI" id="bmi" class="input-field" />
        <span v-if="bmiValidationError" class="error-message"
          >Must input numbers</span
        >
      </div>

      <button type="submit" class="submit-button">Save</button>
      <button @click="cancelEdit" class="cancel-button">Cancel</button>
    </form>
  </div>
</template>
    

<script>
import UserService from "../services/UserService.js";

export default {
  name: "editprofile",
  data() {
    return {
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
      return { ...this.originalUser };
    },
    calculatedBMI() {
      if (!isNaN(this.user.weight) && !isNaN(this.user.height)) {
        const weightInPounds = parseFloat(this.user.weight);
        const heightInInches = parseFloat(this.user.height);
        return (
          (weightInPounds / (heightInInches * heightInInches)) *
          703
        ).toFixed(2);
      }
      return "";
    },
  },

  methods: {
    submitForm() {
      this.validateForm();
      if (this.hasValidationError) {
        return;
      }
      this.saveProfile();
    },
    saveProfile() {
      UserService.updateProfile(this.user.id, this.user)
        .then((response) => {
          if (response.status === 200) {
            this.$store.commit("SET_USER", this.user);
            this.$router.push({
              name: "profile",
              params: { id: this.user.id },
            });
          }
        })
        .catch((error) => {
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
      this.sexValidationError =
        this.user.sex &&
        (this.user.sex.length !== 1 ||
          !["M", "F", "O"].includes(this.user.sex.toUpperCase()));
      this.weightValidationError = isNaN(this.user.weight);
      this.heightValidationError = isNaN(this.user.height);
      this.bmiValidationError = isNaN(this.user.bmi);

      this.hasValidationError =
        this.sexValidationError ||
        this.weightValidationError ||
        this.heightValidationError ||
        this.bmiValidationError;
    },
    //   openImageUpload() {
    //   this.$refs.imageInput.click();
    // },
    // handleFileUpload(event) {
    //   const file = event.target.files[0];
    //   if (file) {
    //     const formData = new FormData();
    //     formData.append('photo', file);
    //     UserService.uploadImage(formData)
    //       .then(response => {
    //         this.user.photo = response.data.photoUrl;
    //       })
    //       .catch(error => {
    //         console.error('Error uploading image:', error);
    //       });
    //   }
    // },
    cancelEdit() {
      this.$router.push({ name: "profile", params: { id: this.user.id } });
    },
  },
};
</script>



<style scoped>
.login-container {
  display: flex;
  flex-direction: column;
  align-items: center;
  padding: 15px; 
  border: 1px solid #007bff; 
  border-radius: 5px;
  background-color: #f6f6f6;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
  text-align: center;
  max-width: 350px; 
  margin: 0 auto;
}

.login-heading {
  font-size: 20px; 
  margin-bottom: 10px;
  color: #007bff; 
}

.form-input-group {
  margin-bottom: 8px; 
}

.input-label {
  display: block;
  font-weight: bold;
  margin-bottom: 2px;
}

.input-field {
  width: 100%;
  padding: 6px; 
  border: 1px solid #ccc;
  border-radius: 3px;
  font-size: 12px; 
  box-sizing: border-box;
}

.error-message {
  color: #d9534f;
  margin-top: 2px; 
  display: inline-block;
  font-size: 10px;
}

.submit-button,
.cancel-button {
  width: 100%;
  padding: 8px; 
  background-color: #007bff;
  color: #fff;
  border: none;
  border-radius: 3px;
  font-size: 14px; 
  cursor: pointer;
  margin-top: 8px; 
}
</style>

