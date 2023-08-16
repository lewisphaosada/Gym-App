<template>
  <div id="login" class="login-container">
    <form @submit.prevent="login" class="login-form">
      <h1 class="login-heading">Please Sign In</h1>
      <div role="alert" v-if="invalidCredentials" class="alert">
        Invalid username and password!
      </div>
      <div role="alert" v-if="this.$route.query.registration" class="alert">
        Thank you for registering, please sign in.
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
      <button type="submit" class="submit-button">Sign in</button>
      <p class="register-link">
        <router-link :to="{ name: 'register' }"
          >Need an account? Sign up.</router-link
        >
      </p>
      <p>
        <router-link :to="{ name: 'employeelogin' }"
          >Employee Login</router-link
        >
      </p>
    </form>
  </div>
</template>

<script>
import AuthService from "../services/AuthService";

export default {
  name: "login",
  components: {},
  data() {
    return {
      user: {
        username: "",
        password: "",
      },
      invalidCredentials: false,
    };
  },
  methods: {
    login() {
      AuthService.login(this.user)
        .then((response) => {
          if (response.status == 200) {
            this.$store.commit("SET_AUTH_TOKEN", response.data.token);
            console.log(response.data)
            this.$store.commit("SET_USER", response.data.user);
             this.$router.push({path: '/'});
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

