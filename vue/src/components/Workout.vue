<template>
  <div class="workout-page">
    <h2 class="page-title">Your Workouts</h2>
    <div class="workout-list">
      <div
        class="workout-item"
        v-for="(workout, index) in savedWorkouts"
        :key="index"
      >
        <div class="workout-card">
          <h3 class="workout-title">{{ workout.machineName }}</h3>
          <p><strong>Sets:</strong> {{ workout.sets }}</p>
          <p><strong>Reps:</strong> {{ workout.reps }}</p>
          <p v-if="workout.weightUsed">
            <strong>Weight Used:</strong> {{ workout.weightUsed }}
          </p>
          <p v-if="workout.duration">
            <strong>Duration:</strong> {{ workout.duration }}
          </p>
        </div>
      </div>
    </div>
    <button @click="goToMachineList" class="btn btn-back">Add Exercise</button>
  </div>
</template>

<script>
import WorkoutService from "../services/WorkoutService.js";
import { machines } from "@/store/machines.js";

export default {
  props: ["workoutData"],
  created() {
    this.fetchSavedWorkouts();
  },
  methods: {
    async fetchSavedWorkouts() {
      try {
        const response = await WorkoutService.list();
        this.savedWorkouts = response.data; // Assign fetched data to savedWorkouts
      } catch (error) {
        console.error("Error fetching saved workouts:", error);
      }
    },
    goToMachineList() {
      this.$router.push({ name: "MachineList" });
    },
  },
  data() {
    return {
      machines: machines,
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
