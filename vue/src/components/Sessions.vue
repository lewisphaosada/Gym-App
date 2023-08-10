<template>
  <div>
    <ul class="session-list" v-if="sessions.length > 0">
      <li v-for="session in sessions" v-bind:key="session.id">{{session.date}}</li>
    </ul>
    <h2 v-else>No sessions yet. Let's workout!</h2>
  </div>
</template>

<script>
// import SessionService from '../services/SessionService'

export default {
  name: 'sessions',
  data() {
    return {
      sessions: [
        // {
        //   id: 1,
        //   date: '02/14/2023'
        // },
        // {
        //   id: 2,
        //   date: '02/15/2023'
        // },
        // {
        //   id: 3,
        //   date: '02/16/2023'
        // }
      ]
    }
  },
  // created() {
  //   SessionService.list().then(response => {
  //     this.sessions = response.data;
  //   })
  // }
}
</script>

<style>
.session-list {
  list-style: none
}
</style>