<template>
  <div class="machine-list">
    <h1>Machine List</h1>
    <div class="image-scroll-view">
      <div v-for="machine in machines" :key="machine.id" class="image-item">
        <img :src="machine.photo" alt="Machine" class="machine-image" @click="navigateToDetails(machine.id)" />
      </div>
    </div>
  </div>
</template>

<script>
import { computed, onMounted } from 'vue';
import { useStore } from '@/store';

export default {
  setup() {
    const store = useStore();
    const machines = computed(() => store.state.machines.machines);
  onMounted(() => {
      store.dispatch('machines/fetchMachines');
    });

    const navigateToDetails = (machineId) => {
      // You can navigate to the details page using router programmatically here
      // For now, I'll just log the machine ID
      console.log('Navigating to details of machine:', machineId);
    };

    return {
      machines,
      navigateToDetails,
    };
  },
}
</script>

<style scoped>
.machine-list {
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
.machine-image {
  width: 150px;
  height: 150px;
  object-fit: cover;
  cursor: pointer;
  border: 1px solid #ccc;
  border-radius: 5px;
  transition: transform 0.2s;
}

.machine-image:hover {
  transform: scale(1.1);
}
</style>