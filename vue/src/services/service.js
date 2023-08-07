import axios from 'axios';

const http = axios.create({
    baseURL: "http://localhost:9000"
  });

  export default{

    getProfile(userID){
        return http.get(`/profile/${userID}`);
    },

    updateProfile(profile){
        return http.put(`/profile/${}`, profile);
    },




  }