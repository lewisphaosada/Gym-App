<template>
  <div id="login" class="login-container">
    <form @submit.prevent="login" class="login-form">
      <h1 class="login-heading">Employee Login</h1>
      <div role="alert" v-if="invalidCredentials" class="alert">
        Invalid username and password!
      </div>
      <div class="form-input-group">
        <label for="username" class="input-label">Username: </label>
        <input
          type="text"
          id="username"
          v-model="employee.username"
          class="input-field"
          required
          autofocus
        />
      </div>
      <div class="form-input-group">
        <label for="password" class="input-label">Password: </label>
        <input
          type="password"
          id="password"
          v-model="employee.password"
          class="input-field"
          required
        />
      </div>
      <button type="submit" class="submit-button">Sign in</button>
    </form>
  </div>
</template>

<script>
import authService from "../services/AuthService";

export default {
  data() {
    return {
      employee: {
        username: "",
        password: "",
      },
      invalidCredentials: false,
    };
  },
  methods: {
    EmployeeLogin() {
      authService
        .login(this.user)
        .then((response) => {
          if (response.status == 200) {
            if (response.data.user.role != "ROLE_USER") {
              this.$store.commit("SET_AUTH_TOKEN", response.data.token);
              this.$store.commit("SET_USER", response.data.user);
              this.$router.push({
                path: "/",
                query: { registration: "success" },
              });
            } else {
              this.invalidCredentials = true;
            }
          }
        })
        .catch((error) => {
          const response = error.response;

          if (response.status === 401) {
            this.invalidCredentials = true;
          }
        });
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

.login-form {
  width: 300px;
  padding: 20px;
  border: 1px solid #ccc;
  border-radius: 5px;
  background-color: #f6f6f6;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
}

.login-heading {
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
}

.register-link {
  text-align: center;
  margin-top: 10px;
}
</style>