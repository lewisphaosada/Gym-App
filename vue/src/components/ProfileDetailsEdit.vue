<template>
  <div class="profile-container">
    <h2 class="profile-heading">Edit Profile</h2>
    <form v-on:submit.prevent="saveProfile">
      <div class="profile-details">
        <label>Name:</label><input v-model="editUser.name" />
      </div>
      <div class="profile-details">
        <label>Email:</label><input v-model="editUser.email" />
      </div>
      <div class="profile-details">
        <label>Username:</label><input v-model="editUser.username" />
      </div>
      <div class="profile-details">
        <label>Password:</label><input v-model="editUser.password" />
      </div>
      <div class="profile-details">
        <label>Profile Picture:</label>
        <img v-if="editUser.photo" :src="editUser.photo" alt="User Photo" />
        <div v-else class="empty-profile-picture"></div>
        <button class="upload-button" @click="openImageUpload">Upload Image</button>
        <input type="file" accept="image/*" @change="handleFileUpload" ref="imageInput" style="display: none;" />
      </div>
      <div class="profile-button-container">
        <button class="profile-edit-button" type="submit">Save</button>
        <button class="profile-edit-button" v-on:click="cancelEdit">Cancel</button>
      </div>
    </form>
  </div>
</template>

<script>
import service from '../services/service.js';

export default {
  name: 'editprofile',
  data() {
    return {
      editUser: {
        name: '',
        email: '',
        username: '',
        password: '',
        photo: ''
      }
    };
  },
  methods: {
    saveProfile() {
      const userID = this.$store.state.user.userID;
      service.updateProfile(userID, this.editUser)
        .then(response => {
          if (response.status === 200) {
            this.$store.commit('EDIT_PROFILE_STATE');
            this.resetUser();
            this.$router.push('/profile');
          }
        })
        .catch(error => {
          if (error.response) {
            this.errorMsg = "Error updating profile. Response received was '" + error.response.statusText + "'.";
          } else if (error.request) {
            this.errorMsg = "Error updating profile. Server could not be reached.";
          } else {
            this.errorMsg = "Error updating profile. Profile could not be updated.";
          }
        });
    },
    cancelEdit() {
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
        photo: ''
      };
    },
    openImageUpload() {
      this.$refs.imageInput.click();
    },
    handleFileUpload(event) {
      const file = event.target.files[0];
      if (file) {
        const formData = new FormData();
        formData.append('photo', file);
        service.uploadImage(formData)
          .then(response => {
            this.editUser.photo = response.data.photoUrl;
          })
          .catch(error => {
            console.error('Error uploading image:', error);
          });
      }
    }
  },
  created() {
    const user = this.$store.state.user;
    this.editUser.name = user.name;
    this.editUser.email = user.email;
    this.editUser.username = user.username;
    this.editUser.photo = user.photo;
  }
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