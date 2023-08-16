<template>
  <div>
      {{sessionWorkouts}}
  </div>
</template>

<script>
import WorkoutService from '../services/WorkoutService'

export default {
  name: 'session-comp',
  data() {
    return {
      sessionWorkouts: []
    }
  },
  created() {
    this.populateSessionWorkouts();
    // console.log(this.sessionWorkouts)
  },
  methods: {
    populateSessionWorkouts() {
      WorkoutService.listBySessionId(this.$route.params.id).then(response => {
        this.sessionWorkouts = response.data;
      })
    }
  }
}
</script>

<style>

</style>