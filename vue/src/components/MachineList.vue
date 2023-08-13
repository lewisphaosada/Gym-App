<template>
  <div class="exercise-list">
    <h1>Exercise List</h1>
    <div class="image-scroll-view">
      <div v-for="exercise in exercises" :key="exercise.id" class="image-item">
        <img
          :src="exercise.photo"
          alt="Exercise"
          class="exercise-image"
          @click="navigateToDetails(exercise)"
        />
        <p>{{ exercise.name }}</p>
      </div>
    </div>
  </div>
</template>

<script>
import { ref, onMounted } from "vue";
import axios from "axios";
import router from "@/router";

export default {
  setup() {
    const exercises = ref([]);

    onMounted(async () => {
      try {
        const response = await axios.get("http://localhost:9000/exercises");
        exercises.value = response.data;
        console.log("Fetched exercises:", exercises.value);
      } catch (error) {
        console.error("Error fetching exercises:", error);
      }
    });

    const navigateToDetails = (exerciseId) => {
      console.log("Clicked on exercise with ID:", exerciseId);
      console.log(exerciseId)
      router.push({ name: "MachineDetails", params: { id: exerciseId } });
    };

    return {
      exercises,
      navigateToDetails,
    };
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
