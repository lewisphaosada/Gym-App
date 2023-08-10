<template>
  <div>
    <!-- <FullCalendar :events="events" /> -->
  </div>
</template>

<script>

export default {
  components: {
    // FullCalendar,
  },
  data() {
    return {
      events: [
        {
          title: 'Event 1',
          start: '2023-08-10T10:00:00',
          end: '2023-08-10T12:00:00',
        },
      ],
    };
  },
};
</script>
