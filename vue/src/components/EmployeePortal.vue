
<template>
  <div class="employee-portal">
    <h2>Employee Portal</h2>
    <div class="member" v-for="member in gymMembers" :key="member.id">
      <p>{{ member.name }}</p>
    </div>
  </div>
</template>

<script>

import axios from 'axios';

export default {
  data() {
    return {
      gymMembers: [],
    };
  },
  created() {
    this.loadGymMembers();
  },
  methods: {
    loadGymMembers() {
      axios.get('http://api.example.com/gym-members')
        .then(response => {
          this.gymMembers = response.data;
        })
        .catch(error => {
         console.error('Error fetching gym members:', error);
       });
    },
  },
};
</script>

<style scoped>
.employee-portal {
  margin: 20px;
  padding: 20px;
  border: 1px solid #ccc;
  border-radius: 5px;
  background-color: #F8F8F8;
}
.member {
  display: flex;
  align-items: center;
  justify-content: space-between;
  margin-bottom: 10px;
  padding: 10px;
  border: 1px solid #ddd;
  border-radius: 5px;
  background-color: #fff;
}
</style>