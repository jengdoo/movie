import React, { useState, useEffect } from "react";

const bannerList = [
  "https://tse2.mm.bing.net/th?id=OIP.MnZODH-CnO_yL9IJVOvgVQHaEK&pid=Api&P=0&h=220",
  "https://tse1.mm.bing.net/th?id=OIP.BlGXla8XXdl9X_OjCb-l_wHaEK&pid=Api&P=0&h=220",
  "https://tse1.mm.bing.net/th?id=OIP.xlb7WIt-xzof-gk3f_98cQHaEK&pid=Api&P=0&h=220",
];

const styles = {
  banner: {
    position: "relative",
    width: "100%",
    height: "600px",
    display: "flex",
    justifyContent: "center",
    alignItems: "center",
    color: "#fff",
    textAlign: "center",
    overflow: "hidden",
  },
  background: {
    position: "absolute",
    top: 0,
    left: 0,
    width: "100%",
    height: "100%",
    backgroundSize: "cover",
    backgroundPosition: "center",
    transition: "opacity 1s ease-in-out",
  },
  overlay: {
    position: "absolute",
    top: 0,
    left: 0,
    width: "100%",
    height: "100%",
    background: "rgba(0, 0, 0, 0.5)", // Lớp phủ tối giúp nhìn rõ chữ hơn
  },
  content: {
    position: "relative",
    zIndex: 2,
    maxWidth: "700px",
  },
  title: {
    fontSize: "48px",
    fontWeight: "bold",
    textTransform: "uppercase",
    letterSpacing: "2px",
    textShadow: "2px 2px 8px rgba(255, 255, 255, 0.8)",
  },
  description: {
    fontSize: "18px",
    marginTop: "10px",
    opacity: 0.8,
  },
  buttons: {
    marginTop: "20px",
  },
  button: {
    padding: "12px 24px",
    fontSize: "16px",
    fontWeight: "bold",
    border: "none",
    cursor: "pointer",
    borderRadius: "5px",
    margin: "5px",
    transition: "all 0.3s ease",
  },
  watchNow: {
    background: "linear-gradient(to right, #ffcc00, #ff9900)",
    color: "#000",
  },
  watchLater: {
    background: "transparent",
    border: "2px solid #fff",
    color: "#fff",
  },
};

const Banner = () => {
  const [currentIndex, setCurrentIndex] = useState(0);

  useEffect(() => {
    const interval = setInterval(() => {
      setCurrentIndex((prevIndex) => (prevIndex + 1) % bannerList.length);
    }, 3000); // Đổi ảnh mỗi 5 giây

    return () => clearInterval(interval);
  }, []);

  return (
    <section style={styles.banner}>
      {/* Background Image */}
      <div
        style={{
          ...styles.background,
          backgroundImage: `url(${bannerList[currentIndex]})`,
        }}
      ></div>

      {/* Overlay */}
      <div style={styles.overlay}></div>

      {/* Content */}
      <div style={styles.content}>
        <h2 style={styles.title}>New Styling MOVIES</h2>
        <p style={styles.description}>
          Discover the latest blockbusters and trending films.
        </p>
        <div style={styles.buttons}>
          <button style={{ ...styles.button, ...styles.watchNow }}>
            WATCH NOW
          </button>
          <button style={{ ...styles.button, ...styles.watchLater }}>
            WATCH LATER
          </button>
        </div>
      </div>
    </section>
  );
};

export default Banner;
