import axios from 'axios';

export default {
    list(userId) {
        return axios.get(`/sessions/${userId}`);
    },

    create(session) {
        return axios.post(`/sessions/create-session`, session);
    },

    update(id, session) {
        return axios.put(`/session/${id}`, session)
    },

    getExerciseName(exerciseId) {
        return axios.get(`/session/${exerciseId}`)
    },

    delete(id) {
        return axios.delete(`/sessions/${id}`);
    }
}