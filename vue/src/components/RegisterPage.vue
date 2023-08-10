<template>
  <div id="register" class="login-container">
    <form @submit.prevent="register" class="login-form">
      <h1 class="login-heading">Create Account</h1>
      <div role="alert" v-if="registrationErrors" class="alert">
        {{ registrationErrorMsg }}
      </div>
      <div class="form-input-group">
        <label for="username" class="input-label">Username</label>
        <input
          type="text"
          id="username"
          v-model="user.username"
          class="input-field"
          required
          autofocus
        />
      </div>
      <div class="form-input-group">
        <label for="password" class="input-label">Password</label>
        <input
          type="password"
          id="password"
          v-model="user.password"
          class="input-field"
          required
        />
      </div>
      <div class="form-input-group">
        <label for="confirmPassword" class="input-label"
          >Confirm Password</label
        >
        <input
          type="password"
          id="confirmPassword"
          v-model="user.confirmPassword"
          class="input-field"
          required
        />
      </div>
      <button type="submit" class="submit-button">Create Account</button>
      <p class="register-link">
        <router-link :to="{ name: 'login' }"
          >Already have an account? Log in.</router-link
        >
      </p>
    </form>
  </div>
</template>

<script>
import AuthService from "../services/AuthService";

export default {
  name: "RegisterPage",
  data() {
    return {
      user: {
        username: "",
        password: "",
        confirmPassword: "",
        role: "user",
      },
      registrationErrors: false,
      registrationErrorMsg: "There were problems registering this user.",
    };
  },
  methods: {
    register() {
      if (this.user.password != this.user.confirmPassword) {
        this.registrationErrors = true;
        this.registrationErrorMsg = "Password & Confirm Password do not match.";
      } else {
        AuthService.register(this.user)
          .then((response) => {
            if (response.status == 201) {
              this.$router.push({
                path: "/login",
                query: { registration: "success" },
              });
            }
          })
          .catch((error) => {
            const response = error.response;
            this.registrationErrors = true;
            if (response.status === 400) {
              this.registrationErrorMsg = "Bad Request: Validation Errors";
            } else if (response.status === 507) {
              this.registrationErrorMsg = "fofo";
            }
          });
      }
    },
    methods: {
      register() {
        if (this.user.password != this.user.confirmPassword) {
          this.registrationErrors = true;
          this.registrationErrorMsg =
            "Password & Confirm Password do not match.";
        } else {
          AuthService
            .register(this.user)
            .then((response) => {
              if (response.status == 201) {
                this.$router.push({
                  path: "/login",
                  query: { registration: "success" },
                });
              }
            })
            .catch((error) => {
              const response = error.response;
              this.registrationErrors = true;
              if (response.status === 400) {
                this.registrationErrorMsg = "Bad Request: Validation Errors";
              }
            });
        }
      },
      clearErrors() {
        this.registrationErrors = false;
        this.registrationErrorMsg =
          "There were problems registering this user.";
      },
    },
  },
};
</script>
  
  <style scoped>
.form-input-group {
  margin-bottom: 1rem;
}
label {
  margin-right: 0.5rem;
}
</style>
  