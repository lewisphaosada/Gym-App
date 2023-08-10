// <template>
//   <div>
//     <h2>Employee Actions</h2>
//     <div v-for="member in members" :key="member.id">
//       <p>{{ member.name }}</p>
//       <button @click="checkInMember(member)">Check In</button>
//       <button @click="checkOutMember(member)">Check Out</button>
//     </div>
//   </div>
// </template>

// <script>
// import { getMember, checkIn, checkOut } from "@/api";

// export default {
//   data() {
//     return {
//       members: [],
//     };
//   },
//   methods: {
//     checkInMember(member) {
//       checkIn(member.id);
//       this.members = [...this.members];
//     },
//     checkOutMember(member) {
//       checkOut(member.id);
//       this.members = [...this.members];
//     },
//   },
//   created() {
//     // Fetch members from API or Vuex state
//     // For now, simulate fetching data
//     this.members = [getMember(1), getMember(2)];
//   },
// };
// </script>