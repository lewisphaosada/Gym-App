<template>
  <div>
    <h1>Sessions</h1>
    <sessions/>
  </div>
</template>

<script>
import Sessions from '../components/Sessions.vue';

export default {
  name: "home",
  components: {
    Sessions
  }
};
</script>