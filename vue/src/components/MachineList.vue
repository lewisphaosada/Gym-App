<template>
  <div class="exercise-list">
    <h1>Exercise List</h1>
    <div class="image-scroll-view">
      <div
        v-for="exercise in exercises"
        :key="exercise.exercise_id"
        class="image-item"
      >
        <router-link
          :to="{
            name: 'MachineDetails',
            params: { exerciseId: exercise.exercise_id },
          }"
        >
          <img :src="exercise.photo" alt="Exercise" class="exercise-image" />
          <p>{{ exercise.name }}</p>
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

    navigateToDetails(exerciseId) {
      this.$store.commit("SET_SELECTED_EXERCISE_ID", exerciseId);
      this.$router.push({ name: "MachineDetails" });
    },
  },
  mounted() {
    this.fetchExercises();
  },
};
</script>


<style scoped>
.exercise-list {
  max-width: 800px;
  margin: 0 auto;
  padding: 20px;
}

.image-scroll-view {
  display: flex;
  overflow-x: auto;
  padding-bottom: 20px;
}

.image-item {
  flex: 0 0 auto;
  margin-right: 10px;
}

.exercise-image {
  width: 150px;
  height: 150px;
  object-fit: cover;
  cursor: pointer;
  border: 1px solid #ccc;
  border-radius: 5px;
  transition: transform 0.2s;
}

.exercise-image:hover {
  transform: scale(1.1);
}
</style>
