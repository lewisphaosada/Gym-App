<template>
  <div class="modal">
    <div class="modal-content">
      <h2>Member Profile</h2>
<<<<<<< HEAD
      <img src="https://img.freepik.com/free-icon/user_318-286823.jpg" alt="Logo" class="logo-image" />
=======
      <img src="https://img.freepik.com/free-icon/user_318-286823.jpg" alt="Logo" class="logo" />
>>>>>>> 61dd7f0496ef687ee9a9d345d3cc7b51e438de70
      <p>Name: {{ selectedMember.name }}</p>
      <p>Email: {{ selectedMember.email }}</p>
      <p>Username: {{ selectedMember.username }}</p>
      <p>Password: {{ selectedMember.password }}</p>
      <p>Sex: {{ selectedMember.sex }}</p>
      <p>Weight: {{ selectedMember.weight }}</p>
      <p>Height: {{ selectedMember.height }}</p>
      <p>BMI: {{ selectedMember.bmi }}</p>
<<<<<<< HEAD
      <button @click="closeModal">Close</button>    
      </div>
  </div>
</template>

=======
    </div>
  </div>
</template>


>>>>>>> 61dd7f0496ef687ee9a9d345d3cc7b51e438de70
<script>
export default {
  props: {
    selectedMember: Object,
  },
  methods: {
    closeModal() {
<<<<<<< HEAD
      // Emit an event to let the parent component know to hide the modal
=======
>>>>>>> 61dd7f0496ef687ee9a9d345d3cc7b51e438de70
      this.$emit('close');
    },
  },
};
</script>

<style scoped>
<<<<<<< HEAD
.modal {
  display: flex;
=======

.logo {
  width: 100px; /* Adjust the width as needed */
  height: auto;
  margin-top: 10px;
  margin: auto;
}
.modal {
display: flex;
>>>>>>> 61dd7f0496ef687ee9a9d345d3cc7b51e438de70
  flex-direction: column;
  align-items: flex-start;
  margin-bottom: 20px;
  padding: 10px;
  border: 1px solid #ddd;
  border-radius: 0.5rem;
  background: black;
  background: -webkit-linear-gradient(to right, #3f4c6b, #606c88);
  background: linear-gradient(to right top, #3f4c6b, #606c88);
  padding: 1rem;
<<<<<<< HEAD
  color: #ffffff;
=======
  color: white;
>>>>>>> 61dd7f0496ef687ee9a9d345d3cc7b51e438de70
  box-shadow: 0px 87px 78px -39px rgba(0, 0, 0, 0.4);
  margin: auto;
  width: 32em;
}
<<<<<<< HEAD
.modal-content {
 margin: auto;
}
.logo-image {
  width: 100px; /* Adjust the width as needed */
  height: auto;
  margin-top: 10px;
  margin: auto;
}
</style>
=======
.modal-content{
  margin: auto;
}
</style>
>>>>>>> 61dd7f0496ef687ee9a9d345d3cc7b51e438de70
