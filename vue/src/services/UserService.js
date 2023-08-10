import axios from 'axios';

const http = axios.create({
    baseURL: "http://localhost:9000"
  });

  export default{

    getProfile(userID){
        return http.get(`/profile/${userID}`);
    },

    updateProfile(id, profile){
        return axios.put(`/profile/${id}/edit`, profile);
    }
  }