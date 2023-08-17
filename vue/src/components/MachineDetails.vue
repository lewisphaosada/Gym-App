<template>
  <div class="machine-details">
    <h2 class="form-title">{{ exercise.name }}</h2>
    <img :src="exercise.photo" alt="Exercise Photo" class="machine-photo" />

    <div class="form-group">
      <!-- <h3 class="form-subtitle">Description:</h3> -->
      <div class="description-dropdown">
        <p class="dropdown-trigger" @click="toggleDescription">
          {{ showDescription ? 'Hide' : 'Show' }} Description
        </p>
        <p v-if="showDescription && exercise.description">{{ exercise.description }}</p>
      </div>
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
import WorkoutService from "../services/WorkoutService.js";
import Store from "../store/index.js";


export default {
  props: ["exerciseId"],
  created() {
    Store.state.currentSessionId;
  },
  data(){
    return{
    showDescription: false,
    }
  },
  methods:{
    toggleDescription() {
      this.showDescription = !this.showDescription;
    },
  },
  setup(props) {
    const exercise = ref({
      photo: "",
      name: "",
      description: "",
    });
    const sets = ref(0);
    const reps = ref(0);
    const weight = ref(0);
    const duration = ref(0);

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

    const addWorkout = async () => {
      const workoutData = {
        sessionId: Store.state.currentSessionId,
        userId: Store.state.user.id,
        exerciseId: props.exerciseId,
        sets: sets.value,
        reps: reps.value,
        weight: weight.value,
        duration: duration.value,
      };
      console.log(workoutData);
      
        await WorkoutService.create(workoutData);
        router.push({
          path: "/machines",
         // props: { workoutData, exerciseName: exercise.value.name },
        });
    };

    const goToMachines = () => {
      router.push("/machines");
    };

    return {
      exercise,
      sets,
      reps,
      weight,
      duration,
      addWorkout,
      goToMachines,
    };
  },
  // created() {
  //   console.log("Received id prop:", this.id);
  //   this.machine = machines.find(
  //     (machine) => machine.id === parseInt(this.$route.params.id)
  //   );
  //  },
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
  height: auto;
  max-height: 400px; /* Adjust the max-height for better fit */
  object-fit: contain; /* Use 'contain' to fit within the space */
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
.description-dropdown {
  position: relative;
  cursor: pointer;
}

.dropdown-trigger {
  color: #007bff;
  margin: 0;
  font-size: 16px;
}

.description-dropdown p:not(.dropdown-trigger) {
  display: none;
  margin-top: 10px;
}

.description-dropdown p:not(.dropdown-trigger) {
  display: block;
}
</style>
