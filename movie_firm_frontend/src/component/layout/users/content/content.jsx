import Slider from "react-slick";
import "slick-carousel/slick/slick.css";
import "slick-carousel/slick/slick-theme.css";

const styles = {
  movieList: {
    padding: "20px",
    textAlign: "center",
  },
  movieCard: {
    width: "150px",
    height: "200px",
    backgroundColor: "#333",
    color: "#fff",
    display: "flex",
    alignItems: "center",
    justifyContent: "center",
    borderRadius: "10px",
  },
};

const MovieList = () => {
  const settings = {
    dots: true, // Hiển thị chấm trượt
    infinite: true, // Lặp lại sau khi hết
    speed: 500, // Tốc độ chuyển đổi (ms)
    slidesToShow: 3, // Số lượng hiển thị trên màn hình
    slidesToScroll: 1, // Cuộn từng mục một
    autoplay: true, // Chạy tự động
    autoplaySpeed: 2000, // 2 giây trượt một lần
  };

  return (
    <section style={styles.movieList}>
      <h2 style={{ color: "yellow" }}>Watch Your Movies</h2>
      <Slider {...settings}>
        <div style={styles.movieCard}>Movie 1</div>
        <div style={styles.movieCard}>Movie 2</div>
        <div style={styles.movieCard}>Movie 3</div>
        <div style={styles.movieCard}>Movie 4</div>
        <div style={styles.movieCard}>Movie 5</div>
        <div style={styles.movieCard}>Movie 6</div>
      </Slider>
    </section>
  );
};

export default MovieList;
