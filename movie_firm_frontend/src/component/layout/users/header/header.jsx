import React, { useState } from "react";
import { FaSearch, FaUserCircle, FaBars, FaTimes } from "react-icons/fa";
import "./header.css";

const Header = () => {
  const [showMobileMenu, setShowMobileMenu] = useState(false);

  return (
    <header className="header-container">
      <div className="header">
        <h1 className="logo">MOVIE</h1>

        {/* Icon Menu */}
        <div
          className="menu-icon"
          onClick={() => setShowMobileMenu(!showMobileMenu)}
        >
          {showMobileMenu ? (
            <FaTimes className="icon" />
          ) : (
            <FaBars className="icon" />
          )}
        </div>

        {/* Nav menu */}
        <nav className={`nav-menu ${showMobileMenu ? "active" : ""}`}>
          <a href="#" className="nav-item">
            Home
          </a>
          <a href="#" className="nav-item">
            Categories
          </a>
          <a href="#" className="nav-item">
            Trending
          </a>
          <a href="#" className="nav-item">
            Service
          </a>

          {/* Search */}
          <div className="search-container">
            <input
              type="text"
              placeholder="Search..."
              className="search-input"
            />
            <FaSearch className="icon" />
          </div>

          {/* User icon sẽ nằm trong menu khi responsive */}
          <div className="mobile-user">
            <FaUserCircle className="icon user-icon" />
          </div>
        </nav>

        {/* Khi màn hình lớn, hiển thị user icon bên phải */}
        {!showMobileMenu && (
          <FaUserCircle className="icon user-icon desktop-user" />
        )}
      </div>
    </header>
  );
};

export default Header;
