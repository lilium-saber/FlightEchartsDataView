import axios from '/axios/dist/axios.js';

const axiosInstance = axios.create({
    baseURL: 'http://localhost:8080' // Spring Boot后端的基础URL
});

export default axiosInstance;
