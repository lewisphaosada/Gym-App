<template>
  <div class="exercise-list">
    <h1>Choose an Exercise:</h1>
    <div class="exercise-grid">
      <div
        v-for="exercise in exercises"
        :key="exercise.exercise_id"
        class="exercise-item"
      >
        <router-link
          :to="{
            name: 'MachineDetails',
            params: { exerciseId: exercise.exercise_id },
          }"
        >
          <div class="exercise-box">
            <img :src="exercise.photo" alt="Exercise" class="exercise-image" />
            <p class="exercise-title">{{ exercise.name }}</p>
          </div>
        </router-link>
      </div>
    </div>
  </div>
</template>


<script>
import axios from "axios";

export default {
  data() {
    return {
      exercises: [],
    };
  },
  created() {
    console.log(this.$store.state.currentSessionId);
  },
  methods: {
    async fetchExercises() {
      try {
        const response = await axios.get("http://localhost:9000/exercises");
        this.exercises = response.data;
        console.log("Fetched exercises:", this.exercises);
      } catch (error) {
        console.error("Error fetching exercises:", error);
      }
    },
    
  },
  mounted() {
    this.fetchExercises();
  },
};
</script>


<style scoped>
.exercise-list {
  max-width: 1200px;
  margin: 0 auto;
  padding: 20px;
}

.exercise-grid {
  display: grid;
  grid-template-columns: repeat(6, 1fr);
  gap: 20px;
}

.exercise-item {
  display: flex;
  justify-content: center;
}
exercise-box {
  border: 1px solid #ccc;
  padding: 10px;
  border-radius: 8px;
  background-color: #f9f9f9;
  text-align: center;
  transition: transform 0.2s;
}

.exercise-box:hover {
  transform: scale(1.05);
}

.exercise-image {
  width: 100%;
  height: auto;
  max-height: 150px;
  object-fit: cover;
  cursor: pointer;
}

.exercise-title {
  font-size: 16px;
  margin-top: 10px;
  color: #333;
}

.exercise-image {
  width: 100%;
  height: auto;
  max-height: 400px;
  object-fit: contain;
  border: 2px solid rgb(63, 195, 240); 
  border-radius: 5px;
  margin-bottom: 20px;
}
</style>