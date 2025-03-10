import React from "react";

const styles = {
  movieList: {
    padding: "20px",
    textAlign: "center",
  },
  movies: {
    display: "flex",
    justifyContent: "center",
    gap: "15px",
  },
  movieCard: {
    width: "150px",
    height: "200px",
    backgroundColor: "#333",
    color: "#fff",
    display: "flex",
    alignItems: "center",
    justifyContent: "center",
  },
};

const MovieList = () => {
  return (
    <section style={styles.movieList}>
      <h2>Watch Your Movies</h2>
      <div style={styles.movies}>
        <div style={styles.movieCard}>Movie 1</div>
        <div style={styles.movieCard}>Movie 2</div>
        <div style={styles.movieCard}>Movie 3</div>
        <div style={styles.movieCard}>Movie 4</div>
      </div>
    </section>
  );
};

export default MovieList;
