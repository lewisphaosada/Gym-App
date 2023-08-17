<template>
  <div>
    <h2>Total Workouts: {{sessionWorkouts.length}}</h2>
    <h2></h2>
    <ul v-for="workout in sessionWorkouts" v-bind:key="workout.id">
      <li class="exercise-name">{{exerciseNames[workout.id]}}
        <div>
          <ul class="workout-info">
            <li>Sets: {{workout.sets}}</li>
            <li>Reps: {{workout.reps}}</li>
            <li>Weight: {{workout.weight}}</li>
            <li>Duration: {{workout.duration}}</li>
          </ul>
        </div>
      </li>
    </ul>
  </div>
</template>

<script>
import WorkoutService from '../services/WorkoutService'
import SessionService from '../services/SessionService'

export default {
  name: 'session-comp',
  data() {
    return {
      sessionWorkouts: [],
      exerciseNames: {}
    }
  },
  created() {
    this.populateSessionWorkouts();
  },
  methods: {
    populateSessionWorkouts() {
      WorkoutService.listBySessionId(this.$route.params.id).then(response => {
        this.sessionWorkouts = response.data;
        this.fetchExerciseNames();
      })
    },
    getExerciseName(exerciseId) {
      SessionService.getExerciseName(exerciseId).then(response => {
        console.log(response.data);
        return response.data;
      })
    },
    fetchExerciseNames() {
      this.sessionWorkouts.forEach(workout => {
        SessionService.getExerciseName(workout.exerciseId).then(response => {
          // Update the exerciseNames object with exercise name
          this.$set(this.exerciseNames, workout.id, response.data);
        });
      });
    }
  }
}
</script>

<style>
.exercise-name:not(.workout-info) {
  list-style-type: none;
  font-weight: bold;
  text-align: center; /* Horizontally center the content */
  display: flex;
  align-items: center; /* Vertically center the content */
}

.workout-info {
  list-style-type: none;
  font-weight: normal;
  text-align: left;
}
</style>