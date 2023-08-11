<template>
  <div class="calendar-container">
    <h2 class="calendar-title">Upcoming Classes</h2>
    <div class="calendar">
      <date-picker v-model="date" :config="options"></date-picker>
    </div>
  </div>
</template>
 
<script>
import datePicker from 'vue-bootstrap-datetimepicker';
import 'bootstrap/dist/css/bootstrap.css';
import 'pc-bootstrap4-datetimepicker/build/css/bootstrap-datetimepicker.css';
export default {
  data() {
    return {
      date: new Date(),
      options: {
        format: 'MM/DD/YYYY',
        useCurrent: false
      }
    }
  },
  components: {
    datePicker
  }
}
</script>

<style scoped>
.calendar-container {
  max-width: 800px;
  margin: 0 auto;
  padding: 20px;
}

.calendar-title {
  text-align: center;
  margin-bottom: 20px;
}

.calendar {
  background-color: #fff;
  border-radius: 8px;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
  padding: 20px;
}

.calendar .form-control {
  border-color: #ccc;
}

.calendar .input-group-append button {
  background-color: #007bff;
  color: #fff;
  border-color: #007bff;
}

.calendar .input-group-append button:hover {
  background-color: #0056b3;
  border-color: #0056b3;
}
</style>