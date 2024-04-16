import axios from 'axios';

const API_URL = `http://172.24.20.7:8080/api/v1/`;

export const getAllTheatres = async () => {
  const url = API_URL + `theatre/all`;
  const response = await axios.get(url);
  return response.data;
};

export const getShowsByTheatreId = async theatreId => {
  const url = API_URL + `frontend/getShowsByTheatreId?theatreId=` + theatreId;
  const response = await axios.get(url);
  return response.data;
};
