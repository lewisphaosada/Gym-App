<template>
  <div class="model">
    <div class="model-content">
      <h2>Member Profile</h2>
      <img src="https://img.freepik.com/free-icon/user_318-286823.jpg" alt="Logo" class="logo" />
      <p>Name: {{ userProfile.name }}</p>
      <p>Email: {{ userProfile.email }}</p>
      <p>Username: {{ userProfile.username }}</p>
      <p>Sex: {{ userProfile.sex }}</p>
      <p>Weight: {{ userProfile.weight }}</p>
      <p>Height: {{ userProfile.height }}</p>
      <div>
        <router-link :to="{ name: 'sessions', params: { id: selectedMember.id } }"><h3>Workout Metrics</h3></router-link>
        <p>Total Workouts: {{ userSessions.length }}</p>
        <p>Average Workout Duration: {{ millisecondsToHHMMSS(getAverageSessionTime) }}</p>
      </div>
    </div>
  </div>
</template>

<script>
import SessionService from '../services/SessionService';
import UserService from '../services/UserService';

export default {
  props: {
    selectedMember: Object,
  },
  data() {
    return {
      userProfile: {},
      userSessions: [],
      showWorkoutMetrics: false,
    };
  },
  created() {
    this.populateUserProfile();
    this.getUserSessions(this.selectedMember.id);
  },
  computed: {
    getAverageSessionTime() {
      let totalTime = 0;
      this.userSessions.forEach(userSession => totalTime += userSession.duration)
      const averageTime = totalTime / this.userSessions.length
      return averageTime
    }
  },
  methods: {
    populateUserProfile() {
      UserService.getProfile(this.selectedMember.id).then(response => {
        this.userProfile = response.data

      })
    },
    getUserSessions(userId) {
      SessionService.list(userId).then(response => {
        this.userSessions = response.data
      })
    },
    millisecondsToHHMMSS(milliseconds) {
    try {
        const seconds = Math.floor(milliseconds / 1000);
        const hours = Math.floor(seconds / 3600);
        const minutes = Math.floor((seconds % 3600) / 60);
        const remainingSeconds = seconds % 60;

        return `${hours.toString().padStart(2, '0')}:${minutes.toString().padStart(2, '0')}:${remainingSeconds.toString().padStart(2, '0')}`;
    } catch (error) {
        return `Error: ${error.message}`;
    }
}
  }
};
</script>


<style scoped>

.logo {
  width: 100px; /* Adjust the width as needed */
  height: auto;
  margin-top: 10px;
  margin: auto;
}
.model {
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
.model-content {
 margin: auto;
}
.logo-image {
  width: 100px; /* Adjust the width as needed */
  height: auto;
  margin-top: 10px;
  margin: auto;
}
</style>
