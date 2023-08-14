<template>
  <div class="machine-details">
    <h2 class="form-title">{{ exercise.name }}</h2>
    <img :src="exercise.photo" alt="Exercise Photo" class="machine-photo" />

    <div class="form-group">
      <h3 class="form-subtitle">Description:</h3>
      <p>{{ exercise.description }}</p>
    </div>

    <div class="form-group">
      <h3 class="form-subtitle">Enter Your Workout Details:</h3>
      <label class="form-label">Sets:</label>
      <input v-model="sets" type="number" class="form-input" />

      <label class="form-label">Reps:</label>
      <input v-model="reps" type="number" class="form-input" />

      <label class="form-label">Weight (lbs):</label>
      <input v-model="weight" type="number" class="form-input" />

      <label class="form-label">Duration (minutes):</label>
      <input v-model="duration" type="number" class="form-input" />

      <div class="button-group">
        <button @click="addWorkout" class="form-button">Add to Workout</button>
        <button @click="goToMachines" class="form-button">Cancel</button>
      </div>
    </div>
  </div>
</template>


<script>
import { ref, onMounted } from "vue";
import axios from "axios";
import router from "@/router";

export default {
  props: ["exerciseId"],
  setup(props) {
    const exercise = ref({
      photo: "",
      name: "",
      description: "",
      sets: "",
      reps: "",
      weight: "",
      duration: "",
    });

    onMounted(async () => {
      try {
        const response = await axios.get(
          `http://localhost:9000/exercises/${props.exerciseId}`
        );
        exercise.value = response.data;
        console.log("Fetched exercise details:", exercise.value);
      } catch (error) {
        console.error("Error fetching exercise:", error);
      }
    });

    const addWorkout = () => {
      // Implement the logic to add the workout data to your workout table here
      console.log("Workout data:", {
        sets: exercise.sets,
        reps: exercise.reps,
        weight: exercise.weight,
        duration: exercise.duration,
      });
    };

    const goToMachines = () => {
       router.push("/machines");
    };

    return {
      exercise,
      addWorkout,
      goToMachines,
    };
  },
};
</script>

<style scoped>
.machine-details {
  max-width: 600px;
  margin: 0 auto;
  padding: 20px;
  background-color: #f6f6f6;
  border: 1px solid #ccc;
  border-radius: 5px;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
}

.form-title {
  font-size: 24px;
  margin-bottom: 20px;
}

.machine-photo {
  width: 100%;
  height: auto;
  max-height: 400px; /* Adjust the max-height for better fit */
  object-fit: contain; /* Use 'contain' to fit within the space */
  border-radius: 5px;
  margin-bottom: 20px;
}

.form-group {
  margin-bottom: 20px;
}

.form-subtitle {
  font-size: 18px;
  margin-bottom: 10px;
}

.form-label {
  display: block;
  font-weight: bold;
  margin-bottom: 5px;
}

.form-input {
  width: 100%;
  padding: 10px;
  border: 1px solid #ccc;
  border-radius: 5px;
  font-size: 16px;
}

.button-group {
  display: flex;
  justify-content: space-between;
  margin-top: 20px;
}

.form-button {
  flex: 1;
  padding: 10px;
  background-color: #007bff;
  color: #fff;
  border: none;
  border-radius: 5px;
  cursor: pointer;
  transition: background-color 0.3s;
  margin-right: 10px;
}

.form-button:last-child {
  margin-right: 0;
}

.form-button:hover {
  background-color: #0056b3;
}
</style>
