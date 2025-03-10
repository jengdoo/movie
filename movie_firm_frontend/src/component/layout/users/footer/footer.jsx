import React from "react";

const styles = {
  footer: {
    textAlign: "center",
    padding: "10px",
    backgroundColor: "#000",
    color: "#fff",
  },
};

const Footer = () => {
  return (
    <footer style={styles.footer}>
      <p>&copy; 2025 Movie Streaming. All Rights Reserved.</p>
    </footer>
  );
};

export default Footer;
