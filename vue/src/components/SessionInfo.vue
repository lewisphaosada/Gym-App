<template>
  <div class="session-workouts-container">
    <button @click="goToMachinesList" class="go-to-machines-button">+</button>
    <h2 class="total-workouts">Total Workouts: {{ sessionWorkouts.length }}</h2>
    <div class="horizontal-scroll">
      <div
        v-for="workout in sessionWorkouts"
        :key="workout.id"
        class="workout-box"
      >
        <div class="workout-header">
          <div class="exercise-name">{{ exerciseNames[workout.id] }}</div>
          <div class="workout-buttons">
            <button @click="editWorkout(workout.id)" class="edit-button">
              -
            </button>
            <button @click="deleteWorkout(workout.id)" class="delete-button">
              x
            </button>
          </div>
        </div>
        <div class="workout-details">
          <div class="workout-detail">Sets: {{ workout.sets }}</div>
          <div class="workout-detail">Reps: {{ workout.reps }}</div>
          <div class="workout-detail">Weight: {{ workout.weight }} lbs</div>
          <div class="workout-detail">Duration: {{ workout.duration }} min</div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import WorkoutService from "../services/WorkoutService";
import SessionService from "../services/SessionService";

export default {
  name: "session-comp",
  data() {
    return {
      sessionWorkouts: [],
      exerciseNames: {},
    };
  },
  created() {
    this.populateSessionWorkouts();
  },
  methods: {
    populateSessionWorkouts() {
      WorkoutService.listBySessionId(this.$route.params.id).then((response) => {
        this.sessionWorkouts = response.data;
        this.fetchExerciseNames();
      });
    },
    fetchExerciseNames() {
      this.sessionWorkouts.forEach((workout) => {
        SessionService.getExerciseName(workout.exerciseId).then((response) => {
          this.$set(this.exerciseNames, workout.id, response.data);
        });
      });
    },
    goToMachinesList() {
      this.$router.push({ name: "machines-list" });
    },
    //   editWorkout(workoutId) {
    //     // Add code to edit the workout
    //   },
    //   deleteWorkout(workoutId) {
    //     // Add code to delete the workout
    //   },
  },
};
</script>

<style scoped>
.session-workouts-container {
  padding: 20px;
  position: relative;
}

.total-workouts {
  font-size: 24px;
  margin-bottom: 20px;
}

.go-to-machines-button {
  position: absolute;
  top: 40px;
  right: 40px;
  background-color: #007bff;
  color: #fff;
  border: none;
  border-radius: 50%;
  width: 40px;
  height: 40px;
  font-size: 24px;
  cursor: pointer;
}

.horizontal-scroll {
  display: flex;
  overflow-x: scroll;
  gap: 20px;
}

.workout-box {
  background-color: #f7f7f7;
  border: 1px solid #007bff;
  border-radius: 5px;
  padding: 10px;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
  flex: 0 0 250px;
  margin-bottom: 20px;
}

.workout-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
}

.exercise-name {
  font-weight: bold;
  text-align: center;
  margin-bottom: 10px;
  color: #333333;
}

.workout-buttons button {
  background-color: transparent;
  border: none;
  font-size: 20px;
  cursor: pointer;
}

.edit-button {
  color: #007bff;
  margin-right: 10px;
}

.delete-button {
  color: #d9534f;
}

.workout-details {
  display: flex;
  flex-direction: column;
  align-items: flex-start;
}

.workout-detail {
  margin: 5px 0;
  color: #666666;
  font-weight: bold;
}
</style>
