import {createBottomTabNavigator} from '@react-navigation/bottom-tabs';
import {NavigationContainer} from '@react-navigation/native';
import HomeScreen from '../screens/HomeScreen';
import MoviesScreen from '../screens/MoviesScreen';
import AboutScreen from '../screens/AboutScreen';
import TheatresScreen from '../screens/TheatresScreen';
import IonIcon from 'react-native-vector-icons/Ionicons';
import MatIcon from 'react-native-vector-icons/MaterialIcons';
import MatCommunityIcon from 'react-native-vector-icons/MaterialCommunityIcons';

const homeName = 'Home';
const moviesName = 'Movies';
const theatresName = 'Theatres';
const aboutName = 'About';

const Tab = createBottomTabNavigator();

function MainContainer() {
  return (
    <NavigationContainer>
      <Tab.Navigator
        initialRouteName={homeName}
        screenOptions={({route}) => ({
          tabBarIcon: ({focused, color, size}) => {
            let iconName;
            let rn = route.name;
            if (rn === homeName) {
              iconName = focused ? 'home' : 'home-outline';
              return <IonIcon name={iconName} size={size} color={color} />;
            } else if (rn === moviesName) {
              return <MatIcon name="local-movies" size={size} color={color} />;
            } else if (rn === theatresName) {
              iconName = focused ? 'movie' : 'movie-outline';
              return (
                <MatCommunityIcon name={iconName} color={color} size={size} />
              );
            } else if (rn == aboutName) {
              iconName = focused ? 'account' : 'account-outline';
              return (
                <MatCommunityIcon name={iconName} color={color} size={size} />
              );
            }
          },
          tabBarActiveTintColor: 'tomato',
          tabBarInactiveTintColor: 'gray',
        })}>
        <Tab.Screen name={homeName} component={HomeScreen} />
        <Tab.Screen
          name={moviesName}
          component={MoviesScreen}
          options={{headerShown: false}}
        />
        <Tab.Screen
          name={theatresName}
          component={TheatresScreen}
          options={{headerShown: false}}
        />
        <Tab.Screen name={aboutName} component={AboutScreen} />
      </Tab.Navigator>
    </NavigationContainer>
  );
}

export default MainContainer;
