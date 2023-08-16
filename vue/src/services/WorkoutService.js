import axios from 'axios';


export default {
  list() {
    return axios.get(`/workouts`);
  },
  create(workout) {
    return axios.post(`/workouts/save`, workout);
  },
  update(id, workout) {
    return axios.put(`/workouts/${id}`, workout);
  },
  delete(id) {
    return axios.delete(`/workouts/${id}`);
  },
  exercises() {
    return axios.get('/exercises');
  }
  
};
