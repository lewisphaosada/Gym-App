<template>
       <div>
        <h2>Your Goals</h2>
    <input v-model="newGoal" placeholder="Write Your Goal Here">
    <button @click="addGoal">Add Goal</button>

    
    <li v-for="(goal, index) in editableGoals" :key="index">
        <input v-model="editableGoals[index]" />
        <button @click="removeGoal(index)">Remove</button>
        <button @click="updateGoal(index)">Update</button>
      </li>

    </div>
    </template>

<script>

export default {
    name: 'goal',
    data() {
    return {
      goals: [],      
      newGoal: '',
      editableGoals: [],
    };
  },
    methods: {
    addGoal() {
      if (this.newGoal.trim() !== '') {
        this.goals.push(this.newGoal);
        this.editableGoals.push(this.newGoal); 
        this.newGoal = '';
      }
    },
    removeGoal(index) {
      this.goals.splice(index, 1);
      this.editableGoals.splice(index, 1); 
    },
    updateGoal(index) {
      this.goals[index] = this.editableGoals[index];
    },
    
    },

    created(){

    },
        

}
</script>

<style scoped>

</style>
