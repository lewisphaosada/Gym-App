<template>
  <div>
    <FullCalendar :config="calendarConfig" />
  </div>
</template>

<script>
//popular vue full calendar library used. moment also installed to display blocks to 12hr format. looked through fullcalendar documentation and still can't get it to convert to 12 hour.
import { FullCalendar } from 'vue-full-calendar'
import 'vue-full-calendar/node_modules/fullcalendar/dist/fullcalendar.css';
import moment from 'moment';


export default {
  components: {
    FullCalendar,
    
  },
  data() {
    return {

      calendarConfig: {

        events: [
           {
            title: 'Yoga',
            start: moment('2023-08-25 05:00:00', 'YYYY-MM-DD HH:mm:ss').format(),
            end: moment('2023-08-25 06:00:00', 'YYYY-MM-DD HH:mm:ss').format() 
          },
            {
            title: 'Yoga',
            start: moment('2023-08-15 05:00:00', 'YYYY-MM-DD HH:mm:ss').format(),
            end: moment('2023-08-15 06:00:00', 'YYYY-MM-DD HH:mm:ss').format() 
          },
          {
            title: 'HIIT',
            start: moment('2023-08-13 08:00:00', 'YYYY-MM-DD HH:mm:ss').format(),
            end: moment('2023-08-13 09:00:00', 'YYYY-MM-DD HH:mm:ss').format()
          },
          {
            title: 'HIIT',
            start: moment('2023-08-22 08:00:00', 'YYYY-MM-DD HH:mm:ss').format(),
            end: moment('2023-08-22 09:00:00', 'YYYY-MM-DD HH:mm:ss').format()
          },
          {
           title: 'Pilates',
           start: moment('2023-08-15 11:00:00', 'YYYY-MM-DD HH:mm:ss').format(),
           end: moment('2023-08-15 12:00:00', 'YYYY-MM-DD HH:mm:ss').format() 
          },
            {
           title: 'Pilates',
           start: moment('2023-08-21 11:00:00', 'YYYY-MM-DD HH:mm:ss').format(),
           end: moment('2023-08-21 12:00:00', 'YYYY-MM-DD HH:mm:ss').format() 
          },
          {
            title: 'AcroYoga',
            start: moment('2023-08-16 09:00:00', 'YYYY-MM-DD HH:mm:ss').format(),
            end: moment('2023-08-16 11:00:00', 'YYYY-MM-DD HH:mm:ss').format()
          },
          {
            title: 'AcroYoga',
            start: moment('2023-08-20 09:00:00', 'YYYY-MM-DD HH:mm:ss').format(),
            end: moment('2023-08-20 11:00:00', 'YYYY-MM-DD HH:mm:ss').format()
          },

        ]
      },
    };
  },
};
</script>
