import axios from "./axios.custom.jsx";
const fetchDataMovie = (pageNumber = 1, pageSize = 10) => {
  try {
    const URL_BACKEND = "/movies";
    return axios.get(URL_BACKEND, {
      params: {
        pageNumber,
        pageSize,
      },
    });
  } catch (error) {
    console.error("Error fetching orders:", error);
    throw error;
  }
};
const createMovies = async ({
  title,
  description,
  genre,
  type,
  isFree,
  price,
  releaseDate,
  director,
  duration,
  thumbnail, // File ảnh
}) => {
  try {
    const URL_BACKEND = "/movies/create";

    // Tạo FormData để gửi cả JSON và file
    const formData = new FormData();

    // Chuyển đối tượng thành JSON string
    const movieData = {
      title,
      description,
      genre,
      type,
      isFree,
      price,
      releaseDate,
      director,
      duration,
    };

    formData.append("moviesRequest", JSON.stringify(movieData)); // Gửi JSON dưới dạng chuỗi
    if (thumbnail) {
      formData.append("thumbnail", thumbnail); // Gửi file ảnh
    }
    const response = await axios.post(URL_BACKEND, formData, {
      headers: { "Content-Type": "multipart/form-data" },
    });
    return response.data;
  } catch (error) {
    console.error("Lỗi khi tạo phim:", error);
    throw error;
  }
};
export { fetchDataMovie, createMovies };
