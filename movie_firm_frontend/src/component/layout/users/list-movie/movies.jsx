import React, { useState, useEffect } from "react";

const videoList = [
  {
    title: "Movie 1",
    src: "https://www.youtube.com/watch?v=HXkh7EOqcQ4",
    thumbnail:
      "https://tse2.mm.bing.net/th?id=OIP.qDV5fPcKnA-1bkZ5OdL70gHaEK&pid=Api&P=0&h=220",
  },
  {
    title: "Movie 2",
    src: "https://www.youtube.com/watch?v=HXkh7EOqcQ4",
    thumbnail:
      "https://tse2.mm.bing.net/th?id=OIP.DJuGwoGXwGGpC0Ykq0-iWgHaEK&pid=Api&P=0&h=220",
  },
  {
    title: "Movie 3",
    src: "https://www.youtube.com/watch?v=OuNo8Tkb3lI",
    thumbnail:
      "https://tse2.mm.bing.net/th?id=OIP.W0vkXhejV1S5O9HwYTnk6gHaEA&pid=Api&P=0&h=220",
  },
];

const VideoSlider = () => {
  const [currentIndex, setCurrentIndex] = useState(0);

  useEffect(() => {
    const interval = setInterval(() => {
      nextVideo();
    }, 10000); // Chuyển video mỗi 5s

    return () => clearInterval(interval);
  }, [currentIndex]);

  const nextVideo = () => {
    setCurrentIndex((prevIndex) => (prevIndex + 1) % videoList.length);
  };

  const prevVideo = () => {
    setCurrentIndex(
      (prevIndex) => (prevIndex - 1 + videoList.length) % videoList.length
    );
  };

  return (
    <div style={styles.container}>
      <div style={styles.videoWrapper}>
        <iframe
          key={videoList[currentIndex].src}
          src={videoList[currentIndex].src.replace("watch?v=", "embed/")}
          title={videoList[currentIndex].title}
          style={styles.video}
          frameBorder="0"
          allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture"
          allowFullScreen
        ></iframe>
        <button onClick={prevVideo} style={styles.buttonLeft}>
          &#9665;
        </button>
        <button onClick={nextVideo} style={styles.buttonRight}>
          &#9655;
        </button>
      </div>
      <div style={styles.sidebar}>
        {videoList.map((video, index) => (
          <div
            key={index}
            style={styles.videoItem}
            onClick={() => setCurrentIndex(index)}
          >
            <img
              src={video.thumbnail}
              alt={video.title}
              style={styles.thumbnail}
            />
            <p style={styles.title}>{video.title}</p>
          </div>
        ))}
      </div>
    </div>
  );
};

const styles = {
  container: {
    display: "flex",
    alignItems: "center",
    gap: "20px",
    padding: "20px",
    background: "#000",
    color: "#fff",
  },
  videoWrapper: { position: "relative", width: "70%" },
  video: { width: "100%", height: "400px", borderRadius: "10px" },
  buttonLeft: {
    position: "absolute",
    left: "10px",
    top: "50%",
    transform: "translateY(-50%)",
    fontSize: "20px",
    background: "rgba(0,0,0,0.5)",
    border: "none",
    cursor: "pointer",
    color: "#fff",
    padding: "10px",
  },
  buttonRight: {
    position: "absolute",
    right: "10px",
    top: "50%",
    transform: "translateY(-50%)",
    fontSize: "20px",
    background: "rgba(0,0,0,0.5)",
    border: "none",
    cursor: "pointer",
    color: "#fff",
    padding: "10px",
  },
  sidebar: {
    display: "flex",
    flexDirection: "column",
    gap: "10px",
    width: "30%",
  },
  videoItem: {
    display: "flex",
    alignItems: "center",
    gap: "10px",
    cursor: "pointer",
    padding: "10px",
    borderRadius: "5px",
    transition: "0.3s",
    background: "#222",
  },
  thumbnail: { width: "50px", height: "50px", borderRadius: "5px" },
  title: { fontSize: "14px", fontWeight: "bold" },
};

export default VideoSlider;
