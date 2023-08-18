<template>
  <div id="register" class="login-container">
    <form @submit.prevent="register" class="login-form">
      <h1 class="login-heading">Create Employee</h1>
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
          >Are you a member? Log in.</router-link
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
        role: "employee",
      },
      registrationErrors: false,
      registrationErrorMsg: "There were problems registering this user.",
    };
  },
  created() {
    this.adminCheck();
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
                path: "/employee-portal",
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
    adminCheck() {
      if (this.$store.state.user.authorities[0].name != "ROLE_ADMIN") {
        if(this.$store.state.user.authorities[0].name === "ROLE_EMPLOYEE") {
          this.$router.push({path: '/employee-portal'})
        } else if(this.$store.state.user.authorities[0].name === "ROLE_USER") {
          this.$router.push({ path: "/" });
        }
      }
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
