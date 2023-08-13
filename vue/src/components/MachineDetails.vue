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
      <input v-model="exercise.sets" type="number" class="form-input" />

      <label class="form-label">Reps:</label>
      <input v-model="exercise.reps" type="number" class="form-input" />

      <label v-if="exercise.timeSlot" class="form-label">Time Slot:</label>
      <input
        v-if="exercise.timeSlot"
        v-model="exercise.duration"
        type="text"
        class="form-input"
      />

     <label v-if="exercise.weightUsed" class="form-label">Weight Used:</label>
      <input
        v-if="exercise.weightUsed"
        v-model="exercise.weight"
        type="text"
        class="form-input"
      />
    </div>
  </div>
</template>


<script>
import axios from "axios";

export default {
  props: ["id"],
  data() {
    return {
      exercise: {
        name: '',
        photo: '',
        description: '',
        
      },
    };
  },
  async created() {
    try {
      const response = await axios.get(`http://localhost:9000/exercises/${this.id}`);
      this.exercise = response.data;
      
    } catch (error) {
      console.error('Error fetching exercise:', error);
    }
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
</style>
