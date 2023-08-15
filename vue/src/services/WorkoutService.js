import axios from 'axios';

const BASE_URL = 'http://localhost:9000'; 

export default {
  list() {
    return axios.get(`${BASE_URL}/workouts`);
  },
  create(workoutData) {
    return axios.post(`${BASE_URL}/workouts/save-workout`, workoutData);
  },
  update(id, workout) {
    return axios.put(`${BASE_URL}/workouts/${id}`, workout);
  },
  delete(id) {
    return axios.delete(`${BASE_URL}/workouts/${id}`);
  },
  exercises() {
    return axios.get('/exercises');
  }
  
};
