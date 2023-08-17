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

    clearErrors() {
      this.registrationErrors = false;
      this.registrationErrorMsg = "There were problems registering this user.";
    },
  },
};
</script>
<style scoped>
.login-container {
  display: flex;
  justify-content: center;
  align-items: center;
  height: 100vh;
}

.login-form,
.registration-form {
  width: 300px;
  padding: 20px;
  border: 1px solid #ccc;
  border-radius: 5px;
  background-color: #f6f6f6;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
}

.login-heading,
.registration-heading {
  font-size: 24px;
  margin-bottom: 20px;
  text-align: center;
}

.alert {
  color: #d9534f;
  margin-bottom: 10px;
}

.form-input-group {
  margin-bottom: 15px;
}

.input-label {
  display: block;
  font-weight: bold;
  margin-bottom: 5px;
}

.input-field {
  width: 100%;
  padding: 10px;
  border: 1px solid #ccc;
  border-radius: 3px;
  font-size: 14px;
  box-sizing: border-box;
}

.submit-button {
  width: 100%;
  padding: 10px;
  background-color: #007bff;
  color: #fff;
  border: none;
  border-radius: 3px;
  font-size: 16px;
  cursor: pointer;
  margin-top: 10px;
}

.register-link {
  text-align: center;
  margin-top: 10px;
}
</style>

