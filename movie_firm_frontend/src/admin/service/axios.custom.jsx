import axios from "axios";

// Lấy token từ localStorage (nếu có)
const getToken = () => {
  return localStorage.getItem("accessToken");
};

// Tạo một instance Axios
const axiosClient = axios.create({
  baseURL: "http://localhost:8080/api/v1",
  timeout: 10000,
  headers: {
    "Content-Type": "application/json",
  },
});

// Thêm interceptor trước khi gửi request
axiosClient.interceptors.request.use(
  (config) => {
    const token = getToken();
    if (token) {
      config.headers.Authorization = `Bearer ${token}`;
    }
    return config;
  },
  (error) => {
    return Promise.reject(error);
  }
);

// Thêm interceptor để xử lý response
axiosClient.interceptors.response.use(
  (response) => {
    return response.data; // Lấy trực tiếp `data`, không cần `response.data`
  },
  (error) => {
    if (error.response) {
      console.error("API Error:", error.response.data);
      if (error.response.status === 401) {
        // Nếu 401 Unauthorized, có thể logout
        localStorage.removeItem("accessToken");
        window.location.href = "/login"; // Chuyển hướng về trang login
      }
    }
    return Promise.reject(error);
  }
);

export default axiosClient;
