import {createNativeStackNavigator} from '@react-navigation/native-stack';
import MoviesMovieScreen from './MoviesMovieScreen';
import MoviesTheatreScreen from './MoviesTheatreScreen';
import SeatMap from './SeatMap';

function MoviesScreen({navigation}) {
  const Tab = createNativeStackNavigator();
  const movieName = 'InnerMovie';
  const theatreName = 'InnerTheatre';
  const seatMap = 'SeatMap';
  return (
    <Tab.Navigator initialRouteName={movieName}>
      <Tab.Screen
        name={movieName}
        component={MoviesMovieScreen}
        options={{headerShown: false}}></Tab.Screen>
      <Tab.Screen
        name={theatreName}
        component={MoviesTheatreScreen}
        options={{headerShown: false}}></Tab.Screen>
      <Tab.Screen
        name={seatMap}
        component={SeatMap}
        options={{headerShown: false}}></Tab.Screen>
    </Tab.Navigator>
  );
}

export default MoviesScreen;
