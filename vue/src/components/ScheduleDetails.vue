<template>
  <div>
    <FullCalendar :config="calendarConfig" />
  </div>
</template>

<script>
//popular vue full calendar library used
import { FullCalendar } from 'vue-full-calendar'
import 'vue-full-calendar/node_modules/fullcalendar/dist/fullcalendar.css';


export default {
  components: {
    FullCalendar
  },
  data() {
    return {
      calendarConfig: {
        // Configure your calendar here
      }
    }
  }
}
</script>