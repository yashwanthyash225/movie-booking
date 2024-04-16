import {useEffect, useState} from 'react';
import {
  StyleSheet,
  Text,
  View,
  FlatList,
  Dimensions,
  TouchableOpacity,
} from 'react-native';
import MatIcon from 'react-native-vector-icons/MaterialIcons';
import {getShowsByMovieId} from '../components/home/HomeWeb';
import DateFlatList from '../components/common/DateFlatList';

const windowWidth = Dimensions.get('window').width;
const windowHeight = Dimensions.get('window').height;

function MoviesTheatreScreen({route, navigation}) {
  const movieItem = route.params;
  const [dateIndex, setDateIndex] = useState(0);

  const gett = async () => {
    const shows = await getShowsByMovieId(movieItem.id);
    const x = shows.map((item, index) => {
      return {
        key: index,
        date: item.date,
        month: item.month,
        week: item.week,
        theatres: item.theatreDetails,
      };
    });
    setShowsList(x);
  };

  const [showsList, setShowsList] = useState([]);

  useEffect(() => {
    gett();
  }, []);

  const onDatePress = index => {
    setDateIndex(index);
  };

  const onPressFuncSeat = (timeIndex, theatreIndex, theatreItem, timings) => {
    navigation.navigate('SeatMap', {
      timeIndex,
      theatreIndex,
      theatreItem,
      movieItem,
      timings,
    });
  };

  const renderTheatreItem = ({item, index}) => {
    return (
      <View style={styles.theatreCard}>
        <View style={styles.theatreDetails}>
          <MatIcon name="favorite-border" color="black" size={25}></MatIcon>
          <View style={styles.theatreNameBox}>
            <Text style={styles.theatreName}>{item.theatre.name}</Text>
            <Text style={styles.theatreAddress}>
              {item.theatre.address + '  |  ' + '4.5 mi'}
            </Text>
          </View>
        </View>
        <View style={styles.timingsBox}>
          {item.showTimings.map((timeItem, timeIndex) => {
            return (
              <TouchableOpacity
                style={styles.timingsCard}
                onPress={() =>
                  onPressFuncSeat(
                    timeIndex,
                    index,
                    item.theatre,
                    item.showTimings,
                  )
                }>
                <Text style={styles.timingsValue}>{timeItem.timing}</Text>
              </TouchableOpacity>
            );
          })}
        </View>
      </View>
    );
  };

  if (showsList.length === 0) {
    return (
      <View>
        <Text>Loading</Text>
      </View>
    );
  } else {
    return (
      <View style={styles.screen}>
        <View style={styles.screenHeader}>
          <Text style={styles.screenTitle}>{movieItem.title}</Text>
        </View>
        <View style={styles.dateView}>
          <DateFlatList
            dateList={showsList.map(item => {
              return {date: item.date, week: item.week, month: item.month};
            })}
            onDatePress={onDatePress}></DateFlatList>
        </View>
        <FlatList
          keyExtractor={(item, index) => index}
          data={showsList.at(dateIndex).theatres}
          showsVerticalScrollIndicator={false}
          renderItem={(item, index) =>
            renderTheatreItem(item, index)
          }></FlatList>
      </View>
    );
  }
}

const styles = StyleSheet.create({
  screen: {
    flex: 1,
    backgroundColor: 'white',
  },
  screenHeader: {
    padding: 10,
    position: 'fixed',
  },
  screenTitle: {
    color: 'black',
    fontWeight: 'bold',
    fontSize: 35,
  },
  dateCard: {
    flexDirection: 'column',
    justifyContent: 'center',
    alignItems: 'center',
    padding: 5,
    width: windowWidth / 6,
  },
  dateValue: {
    fontSize: 15,
  },
  theatreCard: {
    padding: 10,
  },
  theatreDetails: {
    flexDirection: 'row',
  },
  theatreNameBox: {
    paddingLeft: 10,
  },
  theatreName: {
    fontWeight: 'bold',
    color: 'black',
    fontSize: 20,
  },
  timingsBox: {
    flexDirection: 'row',
    flexWrap: 'wrap',
    paddingHorizontal: 20,
    paddingTop: 10,
  },
  timingsCard: {
    borderColor: 'tomato',
    borderWidth: 2,
    padding: 10,
    borderRadius: 10,
    margin: 5,
  },
  timingsValue: {
    color: 'black',
    fontWeight: 'bold',
  },
});

export default MoviesTheatreScreen;
