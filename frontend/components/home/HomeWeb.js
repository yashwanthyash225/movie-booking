import axios from "axios";

const API_URL = `http://172.24.20.7:8080/api/v1/`

export const getRecommendedMovies = async () => {
  const url = API_URL + `movie/all`;
  const response = await axios.get(url);
  return response.data;
};

export const getShowsByMovieId = async (movieId) => {
  const url = API_URL + `frontend/getShowsByMovieId?movieId=1`;
  const response = await axios.get(url);
  return response.data;
};

export const getImageSliderMovies = async (movieId) => {
  const url = API_URL + `movie/getImageSliderMovies`;
  const response = await axios.get(url);
  return response.data;
};