import Banner from "../component/layout/users/card/card";
import MovieList from "../component/layout/users/content/content";
import Footer from "../component/layout/users/footer/footer";
import Header from "../component/layout/users/header/header";
import VideoSlider from "../component/layout/users/list-movie/movies";

const AppUser = () => {
  return (
    <div className="app-container">
      <Header />
      <main className="main-content">
        <Banner />
        <MovieList />
        <VideoSlider />
      </main>
      <Footer />
    </div>
  );
};
export default AppUser;
