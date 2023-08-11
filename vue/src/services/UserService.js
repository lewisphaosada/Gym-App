import axios from 'axios';

  export default{

    getProfile(id){
        return axios.get(`/profile/${id}`);
    },

    updateProfile(id, profile){
        return axios.put(`/profile/${id}/edit`, profile);
    }
  }