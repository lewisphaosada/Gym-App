
<template>
  <div>
    <h2>Member Check-in/Check-out</h2>
    <div v-for="member in members" :key="member.id">
      <p>{{ member.name }}</p>
      <p v-if="member.checkedIn">Checked in since: {{ member.checkInTime }}</p>
      <button @click="checkInOut(member)">Check {{ member.checkedIn ? "Out" : "In" }}</button>
    </div>
  </div>
</template>

<script>
import { getMember, checkIn, checkOut } from "@/api";

export default {
  data() {
    return {
      members: [],
    };
  },
  methods: {
    checkInOut(member) {
      if (member.checkedIn) {
        checkOut(member.id);
      } else {
        checkIn(member.id);
      }
      this.members = [...this.members];
    },
  },
  created() {
    // Fetch members from API or Vuex state
    // For now, simulate fetching data
    this.members = [getMember(1), getMember(2)];
  },
};
</script>
