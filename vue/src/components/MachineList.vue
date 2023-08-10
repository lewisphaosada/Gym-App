<template>
  <div class="machine-details">
    <h2>{{ machine.name }}</h2>
    <img :src="machine.photo" alt="Machine Photo" />

    <div class="description">
      <h3>Description:</h3>
      <p>{{ machine.description }}</p>
    </div>

    <div class="user-input">
      <h3>Enter Your Workout Details:</h3>
      <label>Sets:</label>
      <input v-model="sets" type="number" />

      <label>Reps:</label>
      <input v-model="reps" type="number" />

      <label v-if="machine.timeSlot">Time Slot:</label>
      <input v-if="machine.timeSlot" v-model="timeSlot" type="text" />

      <label v-if="machine.weightUsed">Weight Used:</label>
      <input v-if="machine.weightUsed" v-model="weightUsed" type="text" />
    </div>
  </div>
</template>

<script>
export default {
  props: ['machine'],
  data() {
    return {
      sets: '',
      reps: '',
      timeSlot: '',
      weightUsed: ''
    };
  }
};
</script>

<style scoped>
.machine-list {
  max-width: 800px;
  margin: 0 auto;
  padding: 20px;
}

.section-title {
  font-size: 24px;
  margin-bottom: 20px;
}
.machine-items {
  display: grid;
  grid-template-columns: repeat(auto-fill, minmax(200px, 1fr));
  gap: 20px;
}

.machine-item {
  border: 1px solid #ccc;
  padding: 10px;
  text-align: center;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
  transition: transform 0.2s;
}
.machine-item:hover {
  transform: translateY(-5px);
}

.machine-image {
  width: 100%;
  max-height: 150px;
  object-fit: cover;
  margin-bottom: 10px;
}

.machine-name {
  font-size: 18px;
  margin: 10px 0;
}

.details-link {
  background-color: #007bff;
  color: #fff;
  padding: 5px 10px;
  border-radius: 5px;
  text-decoration: none;
  display: inline-block;
  transition: background-color 0.2s;
}

.details-link:hover {
  background-color: #0056b3;
}

</style>
