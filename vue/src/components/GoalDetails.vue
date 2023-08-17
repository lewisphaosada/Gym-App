<template>
  <div class="modal">
    <h2>Your Goals</h2>
    <input v-model="newGoal" placeholder="What do you you have planned?" class="custom-input">
    <button @click="addGoal" class="add-button">Add Goal</button>

    <div class="goal-list">
      <div v-for="(goal, index) in goals" :key="index" class="goal-card">
        <input v-model="editableGoals[index]" class="custom-input" />
        <div class="check-buttons">
          <button @click="removeGoal(index)" class="remove-button">Remove</button>
          <button @click="updateGoal(index)" class="update-button">Update</button>
          <label>
            <input type="checkbox" class="ui-checkbox" v-model="goalCompleted[index]" />
            Completed
          </label>
        </div>
      </div>
    </div>
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
      goalCompleted: [] 
    };
  },
  methods: {
    addGoal() {
      if (this.newGoal.trim() !== '') {
        this.goals.push(this.newGoal);
        this.editableGoals.push(this.newGoal);
        this.goalCompleted.push(false); 
        this.newGoal = '';
      }
    },
    removeGoal(index) {
      this.goals.splice(index, 1);
      this.editableGoals.splice(index, 1);
      this.goalCompleted.splice(index, 1);
    },
    updateGoal(index) {
      this.goals[index] = this.editableGoals[index];
    }
  }
};
</script>

<style scoped>
:root{
    --dark:#374151;
    --darker: #1F2937;
    --darkest: #111827;
    --gray: #6B7280;
    --light: #EEE;
    --pink: #EC4899;
    --purple: #8B5Cf6;
}
h2{
   font-size: 1.5rem;
     font-weight: 300;
     color: var(--gray);
     margin-bottom: 1rem;
 
}

.custom-input {
  width: 50%;
  height: 40px;
  line-height: 36px;
  padding: 0 1rem;
  padding-left: 2.5rem;
  border: 5.5px solid transparent;
  border-radius: 8px;
  outline: none;
  background-color: #f3f3f4;
  color: #0d0c22;
  transition: 0.3s ease;
  margin: 1.5%;
  
  
}
.custom-input:focus, .custom-input:hover{
  outline: none;
 border-color: rgb(157 245 246 / 100%);
 background-color: #fff;
 box-shadow: 0 0 0 4px rgb(234 76 137 / 10%);
}
.modal {
 width: 1;
  height: 554px;
  border-radius: 30px;
  background: #e0e0e0;
  box-shadow: 15px 15px 30px #bebebe, -15px -15px 30px #ffffff;

}
.add-button {
  color: var(--pink);
  font-size: 1.25rem;
  font-weight: 700;
  cursor: pointer;
  transition: 0.4s;
  position: relative;
  display: inline-block;
  padding: 10px 30px;
  border: none;
  border-radius: 8px;
}

 .add-button:active{
     opacity: 0.6;
 }

.add-button:hover {
  opacity: 0.8;
}

.check-buttons {
  display: flex;
  align-items: center;
  margin-left: 100px;
}

.check-buttons > div {
  margin-right: 10px;
}

.check-buttons button {
  padding: 5px 5.5px;
  border-radius: 50px;
  border: 0;
  background-color: white;
  box-shadow: rgb(0 0 0 / 5%) 0 0 8px;
  letter-spacing: 1.5px;
  text-transform: uppercase;
  font-size: 15px;
  transition: all 0.5s ease;
  margin: 4%;
}

.check-buttons button.update-button:hover {
  letter-spacing: 1px;
  background-color: rgb(24, 220, 129);
  color: hsl(0, 0%, 100%);
  box-shadow: rgb(24, 220, 129) 0px 7px 29px 0px;
}

.check-buttons button.update-button:active {
  letter-spacing: 1px;
  background-color: rgb(24, 220, 129);
  color: hsl(0, 0%, 100%);
  box-shadow: rgb(24, 220, 129) 0px 7px 29px 0px;
  transform: translateY(10px);
  transition: 100ms;
}

.check-buttons button.remove-button:hover {
  letter-spacing: 1px;
  background-color: rgb(240, 67, 67);
  color: hsl(0, 0%, 100%);
  box-shadow: rgb(240, 67, 67) 0px 7px 29px 0px;
}

.check-buttons button.remove-button:active {
  letter-spacing: 1px;
  background-color: rgb(240, 67, 67);
  color: hsl(0, 0%, 100%);
  box-shadow: rgb(240, 67, 67) 0px 7px 29px 0px;
  transform: translateY(10px);
  transition: 100ms;
}
.goal-card{
    display: flex;
  flex-direction: column;
  align-items: flex-start;
  margin-bottom: 20px;
  padding: 10px;
  border: 1px solid #ddd;
  border-radius: 8.5rem;
  background: black;
  background: -webkit-linear-gradient(to right, #3f4c6b, #606c88);
  background: linear-gradient(to right top, #3f4c6b, #606c88);
  padding: 1rem;
  color: white;
  box-shadow: 0px 87px 78px -39px rgba(0, 0, 0, 0.4);
  margin: auto;
  width: 32em;
  color:black
}
.goal-card input.custom-input {
  width: 70%; 
}




/* checkbox  */
.ui-checkbox {
  --primary-color: #1677ff;
  --secondary-color: #fff;
  --primary-hover-color: #4096ff;
  /* checkbox */
  --checkbox-diameter: 15px;
  --checkbox-border-radius: 5px;
  --checkbox-border-color: #d9d9d9;
  --checkbox-border-width: 1px;
  --checkbox-border-style: solid;
  /* checkmark */
  --checkmark-size: 1.2;
  margin: 4px;
}

.ui-checkbox, 
.ui-checkbox *, 
.ui-checkbox *::before, 
.ui-checkbox *::after {
  -webkit-box-sizing: border-box;
  box-sizing: border-box;
}

.ui-checkbox {
  -webkit-appearance: none;
  -moz-appearance: none;
  appearance: none;
  width: var(--checkbox-diameter);
  height: var(--checkbox-diameter);
  border-radius: var(--checkbox-border-radius);
  background: var(--secondary-color);
  border: var(--checkbox-border-width) var(--checkbox-border-style) var(--checkbox-border-color);
  -webkit-transition: all 0.3s;
  -o-transition: all 0.3s;
  transition: all 0.3s;
  cursor: pointer;
  position: relative;
}

.ui-checkbox::after {
  content: "";
  position: absolute;
  top: 0;
  left: 0;
  right: 0;
  bottom: 0;
  -webkit-box-shadow: 0 0 0 calc(var(--checkbox-diameter) / 2.5) var(--primary-color);
  box-shadow: 0 0 0 calc(var(--checkbox-diameter) / 2.5) var(--primary-color);
  border-radius: inherit;
  opacity: 0;
  -webkit-transition: all 0.5s cubic-bezier(0.12, 0.4, 0.29, 1.46);
  -o-transition: all 0.5s cubic-bezier(0.12, 0.4, 0.29, 1.46);
  transition: all 0.5s cubic-bezier(0.12, 0.4, 0.29, 1.46);
}

.ui-checkbox::before {
  top: 40%;
  left: 50%;
  content: "";
  position: absolute;
  width: 4px;
  height: 7px;
  border-right: 2px solid var(--secondary-color);
  border-bottom: 2px solid var(--secondary-color);
  -webkit-transform: translate(-50%, -50%) rotate(45deg) scale(0);
  -ms-transform: translate(-50%, -50%) rotate(45deg) scale(0);
  transform: translate(-50%, -50%) rotate(45deg) scale(0);
  opacity: 0;
  -webkit-transition: all 0.1s cubic-bezier(0.71, -0.46, 0.88, 0.6),opacity 0.1s;
  -o-transition: all 0.1s cubic-bezier(0.71, -0.46, 0.88, 0.6),opacity 0.1s;
  transition: all 0.1s cubic-bezier(0.71, -0.46, 0.88, 0.6),opacity 0.1s;
}

.ui-checkbox:hover {
  border-color: var(--primary-color);
}

.ui-checkbox:checked {
  background: var(--primary-color);
  border-color: transparent;
}

.ui-checkbox:checked::before {
  opacity: 1;
  -webkit-transform: translate(-50%, -50%) rotate(45deg) scale(var(--checkmark-size));
  -ms-transform: translate(-50%, -50%) rotate(45deg) scale(var(--checkmark-size));
  transform: translate(-50%, -50%) rotate(45deg) scale(var(--checkmark-size));
  -webkit-transition: all 0.2s cubic-bezier(0.12, 0.4, 0.29, 1.46) 0.1s;
  -o-transition: all 0.2s cubic-bezier(0.12, 0.4, 0.29, 1.46) 0.1s;
  transition: all 0.2s cubic-bezier(0.12, 0.4, 0.29, 1.46) 0.1s;
}
</style>

