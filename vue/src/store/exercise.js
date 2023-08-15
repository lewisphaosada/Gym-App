import axios from 'axios';

const state = {
  exercises: [],
  selectedExerciseId: null, // 
};

const mutations = {
  setExercises(state, exercises) {
    state.exercises = exercises;
  },
  setSelectedExerciseId(state, exerciseId) { 
    state.selectedExerciseId = exerciseId;
  },
};

const actions = {
  async fetchExercises({ commit }) {
    try {
      const response = await axios.get('http://localhost:9000/exercises');
      const exercises = response.data;
      console.log('Fetched exercises:', exercises);
      commit('setExercises', exercises);
    } catch (error) {
      console.error('Error fetching exercises:', error);
    }
  },
};

export default {
  state,
  mutations,
  actions,
};

