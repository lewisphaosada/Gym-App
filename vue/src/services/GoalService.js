import axios from 'axios';

export default {
    get(id) {
        return axios.get(`/goals/${id}`);
    },

    create(goals) {
        return axios.post(`/goals`, goals);
    },

    update(id, goals) {
        return axios.put(`/goals/${id}`, goals)
    },

    delete(id) {
        return axios.delete(`/goals/${id}`);
    }
}