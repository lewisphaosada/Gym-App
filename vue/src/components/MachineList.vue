<template>
  <div class="machine-list">
    <router-link
      v-for="machine in machines"
      :key="machine.id"
      :to="{ name: 'MachineDetails', params: { id: machine.id } }"
      class="machine-item"
    >
      <img
        :src="machine.photo"
        :alt="machine.name"
        class="machine-image"
        @click="navigateToMachineDetails(machine.id)"
      />
      <h3 class="machine-title">{{ machine.name }}</h3>
    </router-link>
  </div>
</template>

<script>
import { machines } from "@/store/machines.js";

export default {
  data() {
    return {
      machines: machines,
    };
  },
  methods: {
    navigateToMachineDetails(machineId) {
      if (
        this.$route.name !== "MachineDetails" ||
        this.$route.params.id !== machineId
      ) {
        this.$router.push({
          name: "MachineDetails",
          params: { id: machineId },
        });
      }
    },
  },
};
</script>

<style scoped>

.machine-list {
  display: grid;
  grid-template-columns: repeat(4, 1fr); /* Maximum of 4 items in a row */
  grid-gap: 20px;
  padding: 20px;
}
.machine-item {
  border: 1px solid #ccc;
  padding: 10px;
  border-radius: 8px;
  background-color: #f9f9f9;
  text-align: center;
  transition: transform 0.2s;
}
.machine-item:hover {
  transform: scale(1.05);
}

.machine-image {
  max-width: 100%;
  height: 150px; 
  object-fit: cover;
  margin-bottom: 10px;
}

.machine-title {
  font-size: 16px;
  margin: 0;
  color: #333;
}
</style>
