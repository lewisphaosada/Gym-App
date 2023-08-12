<template>
  <div class="modal">
    <div class="modal-content">
      <h2>Member Profile</h2>
      <img src="https://img.freepik.com/free-icon/user_318-286823.jpg" alt="Logo" class="logo" />
      <p>Name: {{ selectedMember.name }}</p>
      <p>Email: {{ selectedMember.email }}</p>
      <p>Username: {{ selectedMember.username }}</p>
      <p>Password: {{ selectedMember.password }}</p>
      <p>Sex: {{ selectedMember.sex }}</p>
      <p>Weight: {{ selectedMember.weight }}</p>
      <p>Height: {{ selectedMember.height }}</p>
      <p>BMI: {{ selectedMember.bmi }}</p>
    </div>
  </div>
</template>


<script>
export default {
  props: {
    selectedMember: Object,
  },
  methods: {
    closeModal() {
      this.$emit('close');
    },
  },
};
</script>

<style scoped>

.logo {
  width: 100px; /* Adjust the width as needed */
  height: auto;
  margin-top: 10px;
  margin: auto;
}
.modal {
display: flex;
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
  color: white;
  box-shadow: 0px 87px 78px -39px rgba(0, 0, 0, 0.4);
  margin: auto;
  width: 32em;
}
.modal-content{
  margin: auto;
}
</style>
