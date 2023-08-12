<template>
<<<<<<< HEAD
    <employee-portal></employee-portal>
=======
  <div class="employee-portal">
    <h2>Employee Portal</h2>
    <div class="member" v-for="member in gymMembers" :key="member.id">
      <p>{{ member.name }}</p>
    </div>
  </div>
>>>>>>> fbfae95b423e1ab404e229aacf3a1c2e85ad4588
</template>

<script>
import axios from 'axios';

export default {
<<<<<<< HEAD
    components: {
        EmployeePortal
    }
}
</script>

<style scoped>

=======
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
        //.catch(error => {
         // console.error('Error fetching gym members:', error);
       // });
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
  background-color: #f8f8f8;
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
>>>>>>> fbfae95b423e1ab404e229aacf3a1c2e85ad4588
</style>