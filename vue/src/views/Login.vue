<template>
  <div class="login">
    <div class="image-container">
      <img class="image" src="https://logopond.com/logos/e5da360110ec3b999d0755eeb6e0da30.png"/>
    </div>
    <div class="login-container">
      <login-component></login-component>
    </div>
  </div>
</template>

<script>
import LoginComponent from "@/components/LoginComponent"; 

export default {
  name: "YourView",
  components: {
    LoginComponent
  },
 
};
</script>

<style scoped>
.login {
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
}

.image-container {
  display: flex;
  justify-content: center;
  margin-top: 10px; 
}

.image {
  max-width: 100%;
  height: auto;
}

.login-container {
  margin-top: -150px; 
}
</style>

