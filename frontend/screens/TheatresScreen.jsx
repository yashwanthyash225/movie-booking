import {createNativeStackNavigator} from '@react-navigation/native-stack';
import TheatreTheatreScreen from './theatres/TheatreTheatreScreen';
import SeatMap from './SeatMap';
import TheatreMovieScreen from './theatres/TheatreMovieScreen';

function TheatresScreen({navigation}) {
  const Tab = createNativeStackNavigator();
  const theatreTheatreScreen = 'TheatreTheatreScreen';
  const theatreMovieScreen = 'TheatreMovieScreen';
  const seatMap = 'SeatMap';
  return (
    <Tab.Navigator initialRouteName={theatreTheatreScreen}>
      <Tab.Screen
        name={theatreTheatreScreen}
        component={TheatreTheatreScreen}
        options={{headerShown: false}}></Tab.Screen>
      <Tab.Screen
        name={theatreMovieScreen}
        component={TheatreMovieScreen}
        options={{headerShown: false}}></Tab.Screen>
      <Tab.Screen
        name={seatMap}
        component={SeatMap}
        options={{headerShown: false}}></Tab.Screen>
    </Tab.Navigator>
  );
}

export default TheatresScreen;
