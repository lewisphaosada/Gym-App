<template>
  <div class="machine-details" v-if="machine">
    <h2 class="form-title">{{ machine.name }}</h2>
    <img :src="machine.photo" alt="Machine Photo" class="machine-photo" />

    <div class="form-group">
      <h3 class="form-subtitle">Description:</h3>
      <p>{{ machine.description }}</p>
    </div>

    <div class="form-group">
      <h3 class="form-subtitle">Enter Your Workout Details:</h3>
      <label class="form-label">Sets:</label>
      <input v-model="sets" type="number" class="form-input" />

      <label class="form-label">Reps:</label>
      <input v-model="reps" type="number" class="form-input" />

      <label v-if="machine.weightUsed !== undefined" class="form-label"
        >Weight Used (lbs):</label
      >
      <input
        v-if="machine.weightUsed !== undefined"
        v-model="weight"
        type="text"
        class="form-input"
      />

      <label v-if="machine.Duration !== undefined" class="form-label"
        >Duration:</label
      >
      <input
        v-if="machine.Duration !== undefined"
        v-model="duration"
        type="time"
        step="1"
        class="form-input"
      />
    </div>

    <div class="button-group">
      <button @click="saveWorkout" class="btn btn-save">Save</button>
      <button @click="cancelWorkout" class="btn btn-cancel">Cancel</button>
    </div>
  </div>
</template>

<script>
import { machines } from "@/store/machines.js";
import axios from "axios";

export default {
  props: ["id"],
  data() {
    return {
      sets: "",
      reps: "",
      weight: "",
      duration: "",
      userId: null,
      machine: null,
    };
  },
  created() {
    console.log("Received id prop:", this.id);
    this.machine = machines.find(
      (machine) => machine.id === parseInt(this.$route.params.id)
    );
  },
  methods: {
    async saveWorkout() {
      const userId = this.$store.state.user.id;

      const workoutData = {
        user_id: userId,
        exercise_id: this.id,
        session_id: null,
        weight: parseFloat(this.weight),
        sets: parseInt(this.sets),
        reps: parseInt(this.reps),
        duration: this.duration,
      };
      try {
        const response = await axios.post(
          "http://localhost:9000/workouts",
          workoutData
        );
        const savedWorkout = response.data;
        console.log("Saved workout data:", savedWorkout);

        this.$router.push({
          name: "WorkoutPage",
        });
      } catch (error) {
        console.error("Error saving workout:", error);
      }
    },
    cancelWorkout() {
      this.$router.go(-1);
    },
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
  max-height: 300px;
  object-fit: cover;
  border-radius: 5px;
  margin-bottom: 20px;
}

.form-group-button {
  margin-top: 20px;
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

.btn {
  background-color: #007bff;
  color: #fff;
  border: none;
  border-radius: 5px;
  padding: 10px 20px;
  cursor: pointer;
  margin-top: 10px;
}
</style>
