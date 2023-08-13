<template>
  <div>
    <ul class="session-list" v-if="sessions.length > 0">
      <li v-for="session in sessions" v-bind:key="session.sessionId">
        <router-link :to="{ name: 'session', params: {id: session.sessionId} }">{{ convertUnixToDate(session.date) }} {{session}}</router-link>
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
  },
  created() {
    SessionService.list(this.$store.state.user.id).then((response) => {
      this.sessions = response.data;
    });
  },
};
</script>

<style>
.session-list {
  list-style: none;
  cursor: pointer;
}
</style>