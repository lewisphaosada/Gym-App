<template>
  <div class="employee-portal">
    <h2>Welcome to the Employee Portal</h2>
    <p>This is where employees can access special features and functionalities.</p>
    <!-- Add more content or components here -->
  </div>
</template>

<script>
import EmployeePortal from '@/components/EmployeePortal.vue'

export default {
    name: "EmployeePortal"
    
};
</script>

<style scoped>
.employee-portal {
  margin: 20px;
  padding: 20px;
  border: 1px solid #ccc;
  border-radius: 5px;
  background-color: #f8f8f8;
  text-align: center;
}
</style>