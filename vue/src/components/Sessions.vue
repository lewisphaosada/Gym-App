<template>
  <div>
    <h2 v-if="sessions.length">Average Session Time: {{ millisecondsToHHMMSS(getAverageSessionTime) }}</h2>
    <ul class="session-list" v-if="sessions.length > 0">
      <li v-for="session in sessions" v-bind:key="session.sessionId">
        <router-link
          :to="{ name: 'session', params: { id: session.sessionId } }"
          >{{ convertUnixToDate(session.date) }} Duration:
          {{  millisecondsToHHMMSS(session.duration) }}</router-link
        >
      </li>
    </ul>
    <h2 v-else>No sessions yet. Let's workout!</h2>
  </div>
</template>

<script>
import SessionService from "../services/SessionService";

export default {
  name: "sessions-comp",
  data() {
    return {
      sessions: [],
      averageSessionTime: 0,
    };
  },
  methods: {
    convertUnixToDate(unixTime) {
      const date = new Date(unixTime);
      const year = date.getFullYear();
      const month = date.getMonth() + 1;
      const day = date.getDate();
      const formattedDate = `${month}-${day}-${year}`;
      return formattedDate;
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
},
  },
  computed: {
    getAverageSessionTime() {
      let totalTime = 0;
      this.sessions.forEach((session) => (totalTime += session.duration));
      const averageTime = totalTime / this.sessions.length;
      return averageTime;
    },
  },
  created() {
    SessionService.list(this.$route.params.id).then((response) => {
      this.sessions = response.data;
      this.averageSessionTime = this.getAverageSessionTime;
      console.log(this.sessions);
    });
  },
};
</script>

<style>
.session-list {
  list-style: none;
  padding: 0;
}

.session-list li {
  border: 1px solid #ccc;
  margin: 10px 0;
  padding: 10px;
  border-radius: 5px;
  background-color: #f5f5f5;
  transition: background-color 0.2s ease-in-out;
}

.session-list li:hover {
  background-color: #e0e0e0;
}

.session-list li a {
  text-decoration: none;
  color: #333;
}

.session-list h2 {
  font-size: 1.5rem;
  margin-bottom: 10px;
}
</style>