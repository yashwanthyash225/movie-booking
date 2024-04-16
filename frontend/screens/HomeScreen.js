import {createNativeStackNavigator} from '@react-navigation/native-stack';
import HomePageScreen from "./home/HomePageScreen";
import MoviesTheatreScreen from "./MoviesTheatreScreen";
import SeatMap from "./SeatMap";

function HomeScreen({navigation}) {

    const Tab = createNativeStackNavigator();
    const homePageName = "HomePageScreen";
    const movieTheatreName = "MoviesTheatreScreen";
    const seatMap = "SeatMap";
    return (
        <Tab.Navigator initialRouteName={homePageName}>
            <Tab.Screen name={homePageName} component={HomePageScreen} options={{headerShown: false}}></Tab.Screen>
            <Tab.Screen name={movieTheatreName} component={MoviesTheatreScreen} options={{headerShown: false}}></Tab.Screen>
            <Tab.Screen name={seatMap} component={SeatMap} options={{headerShown: false}}></Tab.Screen>
        </Tab.Navigator>
    );
}

export default HomeScreen;
