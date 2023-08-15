<template>
  <div class="workout-page">
    <h2 class="page-title">Your Workout</h2>
    <div class="workout-details">
      <h3 class="workout-machine">{{ exerciseName }}</h3>
      <p><strong>Sets:</strong> {{ workoutData.sets }}</p>
      <p><strong>Reps:</strong> {{ workoutData.reps }}</p>
      <p v-if="workoutData.weight">
        <strong>Weight Used:</strong> {{ workoutData.weight }}
      </p>
      <p v-if="workoutData.duration">
        <strong>Duration:</strong> {{ workoutData.duration }} minutes
      </p>
    </div>
    <button @click="goToMachineList" class="btn btn-back">Back to Machines</button>
  </div>
</template>

<script>
import WorkoutService from "../services/WorkoutService.js";

import router from "@/router";

export default {
  props: ["workoutData", "exerciseName"],
  created() {
    this.fetchSavedWorkouts();
  },
  methods: {
    async fetchSavedWorkouts() {
      try {
        const response = await WorkoutService.list();
        this.savedWorkouts = response.data;
      } catch (error) {
        console.error("Error fetching saved workouts:", error);
      }
    },
    goToMachineList() {
      router.push({ name: "MachineList" });
    },
  },
  data() {
    return {
      savedWorkouts: [],
    };
  },
};
</script>

<style scoped>
.workout-page {
  max-width: 800px;
  margin: 0 auto;
  padding: 20px;
}

.page-title {
  font-size: 24px;
  margin-bottom: 20px;
}

.workout-list {
  display: grid;
  grid-template-columns: repeat(auto-fill, minmax(300px, 1fr));
  gap: 20px;
}

.workout-item {
  background-color: #f6f6f6;
  border: 1px solid #ccc;
  border-radius: 5px;
  padding: 10px;
}

.workout-card {
  display: flex;
  flex-direction: column;
  justify-content: space-between;
  height: 100%;
}

.workout-title {
  font-size: 20px;
  margin-bottom: 5px;
}

.btn {
  background-color: #007bff;
  color: #fff;
  border: none;
  border-radius: 5px;
  padding: 10px 20px;
  cursor: pointer;
  margin-top: 10px;
}

.btn-back {
  background-color: #ccc;
  color: #fff;
}
</style>
