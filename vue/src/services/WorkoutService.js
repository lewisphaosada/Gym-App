import axios from 'axios';

const BASE_URL = 'http://localhost:8083'; 

export default {
  list() {
    return axios.get(`${BASE_URL}/workouts`);
  },
  create(workout) {
    return axios.post(`${BASE_URL}/workouts`, workout);
  },
  update(id, workout) {
    return axios.put(`${BASE_URL}/workouts/${id}`, workout);
  },
  delete(id) {
    return axios.delete(`${BASE_URL}/workouts/${id}`);
  }
};
