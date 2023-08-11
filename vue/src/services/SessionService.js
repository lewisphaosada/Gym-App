import axios from 'axios';

export default {
    list(id) {
        return axios.get(`/sessions/${id}`);
    },

    create(session) {
        return axios.post(`/sessions`, session);
    },

    update(id, session) {
        return axios.put(`/sessions/${id}`, session)
    },

    delete(id) {
        return axios.delete(`/sessions/${id}`);
    }
}