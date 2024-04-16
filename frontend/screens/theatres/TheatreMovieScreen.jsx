import {useEffect, useState} from 'react';
import {
  Dimensions,
  FlatList,
  Image,
  ScrollView,
  StyleSheet,
  Text,
  TouchableOpacity,
  View,
} from 'react-native';
import {getShowsByTheatreId} from '../../components/theatre/TheatreWeb';
import DateFlatList from '../../components/common/DateFlatList';
import {SafeAreaView} from 'react-native-safe-area-context';

const windowWidth = Dimensions.get('window').width;
const windowHeight = Dimensions.get('window').height;

function TheatreMovieScreen({route, navigation}) {
  const theatreItem = route.params;
  const [showsList, setShowsList] = useState([]);
  const [dateIndex, setDateIndex] = useState(0);

  const fixShows = async () => {
    const shows = await getShowsByTheatreId(theatreItem.id);
    const x = shows.map((item, index) => {
      return {
        key: index,
        date: item.date,
        month: item.month,
        week: item.week,
        movies: item.movieDetails,
      };
    });
    setShowsList(x);
  };

  useEffect(() => {
    fixShows();
  }, []);

  const onDatePress = index => {
    setDateIndex(index);
  };

  const getImageSrc = id => {
    switch (id) {
      case 1:
        return require('../../assets/movies/image_1.jpg');
      case 2:
        return require('../../assets/movies/image_2.jpg');
      case 3:
        return require('../../assets/movies/image_3.jpg');
      case 4:
        return require('../../assets/movies/image_4.jpg');
      case 5:
        return require('../../assets/movies/image_5.jpg');
      case 6:
        return require('../../assets/movies/image_6.jpg');
    }
    return require('../../assets/movies/image_6.jpg');
  };

  const onPressNavigate = (
    timeIndex,
    theatreIndex,
    theatreItem,
    movieItem,
    timings,
  ) => {
    navigation.navigate('SeatMap', {
      timeIndex,
      theatreIndex,
      theatreItem,
      movieItem,
      timings,
    });
  };

  const renderMovieItem = ({item, index}) => {
    return (
      <View style={styles.movieCard}>
        <View style={styles.imageView}>
          <Image
            source={getImageSrc(item.movie.id)}
            style={{width: '100%', height: '100%', borderRadius: 5}}
            resizeMode="stretch"
          />
        </View>
        <View style={styles.detailsView}>
          <Text style={styles.titleText}>{item.movie.title}</Text>
          <Text style={styles.genreText}>{item.movie.genre}</Text>
          <View style={styles.timingView}>
            {item.showTimings.map((timeItem, timeIndex) => {
              return (
                <TouchableOpacity
                  style={styles.timingCard}
                  onPress={() =>
                    onPressNavigate(
                      timeIndex,
                      index,
                      theatreItem,
                      item.movie,
                      item.showTimings,
                    )
                  }>
                  <Text style={styles.timingText}>{timeItem.timing}</Text>
                </TouchableOpacity>
              );
            })}
          </View>
        </View>
      </View>
    );
  };

  if (showsList.length == 0) {
    return (
      <View>
        <Text>Loading</Text>
      </View>
    );
  } else {
    return (
      <ScrollView style={styles.whole}>
        <View>
          <View style={styles.theatreNameView}>
            <Text style={styles.theatreNameText}>{theatreItem.name}</Text>
            <Text style={styles.theatreAddressText}>{theatreItem.address}</Text>
          </View>
          <View style={styles.dateView}>
            <DateFlatList
              dateList={showsList.map(item => {
                return {date: item.date, week: item.week, month: item.month};
              })}
              onDatePress={onDatePress}></DateFlatList>
          </View>
        </View>
        <FlatList
          data={showsList.at(dateIndex).movies}
          renderItem={(item, index) => renderMovieItem(item, index)}
          scrollEnabled={true}
        />
      </ScrollView>
    );
  }
}

const styles = StyleSheet.create({
  whole: {
    backgroundColor: 'white',
  },
  theatreNameView: {
    width: windowWidth,
    padding: 20,
  },
  theatreNameText: {
    fontSize: 30,
    color: 'black',
  },
  theatreAddressText: {
    fontSize: 20,
  },
  dateView: {
    position: 'fixed',
  },
  movieCard: {
    padding: 15,
    flexDirection: 'row',
  },
  imageView: {
    flex: 1,
    height: windowWidth / 2,
  },
  detailsView: {
    flex: 2,
    paddingTop: 10,
    paddingLeft: 20,
  },
  titleText: {
    fontSize: 25,
    color: 'black',
  },
  genreText: {
    fontSize: 15,
  },
  timingView: {
    flexDirection: 'row',
    flexWrap: 'wrap',
  },
  timingCard: {
    padding: 10,
    margin: 5,
    borderColor: 'tomato',
    borderWidth: 2,
    alignItems: 'center',
    borderRadius: 5,
  },
  timingText: {
    fontSize: 15,
    color: 'black',
    fontWeight: 'bold',
  },
});

export default TheatreMovieScreen;
