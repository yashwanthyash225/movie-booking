import {
  FlatList,
  Text,
  View,
  StyleSheet,
  Dimensions,
  Image,
  TouchableOpacity,
} from 'react-native';
import IonIcon from 'react-native-vector-icons/Ionicons';
import FontAwesomeIcon from 'react-native-vector-icons/FontAwesome6';
import {useEffect, useState} from 'react';
import {getRecommendedMovies} from '../components/home/HomeWeb';

const windowWidth = Dimensions.get('window').width;
const windowHeight = Dimensions.get('window').height;

const getImageSrc = id => {
  switch (id) {
    case 1:
      return require('../assets/movies/image_1.jpg');
    case 2:
      return require('../assets/movies/image_2.jpg');
    case 3:
      return require('../assets/movies/image_3.jpg');
    case 4:
      return require('../assets/movies/image_4.jpg');
    case 5:
      return require('../assets/movies/image_5.jpg');
    case 6:
      return require('../assets/movies/image_6.jpg');
  }
};

function MoviesMovieScreen({navigation}) {
  const gett = async () => {
    const movies = await getRecommendedMovies();
    const x = movies.map((item, index) => {
      return {
        key: index,
        id: item.id,
        title: item.title,
        imageSrc: getImageSrc(item.id),
        stars: 8.9,
        likes: '247.3k',
      };
    });
    setMoviesList(x);
  };

  const [moviesList, setMoviesList] = useState([]);

  useEffect(() => {
    gett();
  }, []);

  const movieCard = ({item}) => {
    return (
      <View style={styles.card}>
        <TouchableOpacity
          style={{flex: 10}}
          activeOpacity={0.8}
          onPress={() => {
            navigation.navigate('InnerTheatre', item);
          }}>
          <Image
            source={item.imageSrc}
            style={{width: '100%', height: '100%', borderRadius: 5}}
            resizeMode="stretch"
          />
        </TouchableOpacity>
        <View style={styles.ratings}>
          <View style={styles.stars}>
            <IonIcon name="star" color="red" size={15}></IonIcon>
            <Text style={styles.ratingsvalue}>{item.stars}</Text>
          </View>
          <View style={styles.likes}>
            <FontAwesomeIcon
              name="thumbs-up"
              color="blue"
              size={15}></FontAwesomeIcon>
            <Text style={styles.ratingsvalue}>{item.likes}</Text>
          </View>
        </View>
        <View>
          <Text style={styles.movieTitle}>{item.title}</Text>
        </View>
      </View>
    );
  };

  return (
    <View style={{backgroundColor: 'white'}}>
      <FlatList
        data={moviesList}
        keyExtractor={(item, index) => index}
        numColumns={2}
        renderItem={item => movieCard(item)}></FlatList>
    </View>
  );
}

const styles = StyleSheet.create({
  card: {
    flex: 1,
    height: windowHeight / 2.5,
    width: windowWidth / 2,
    padding: 10,
  },
  ratings: {
    backgroundColor: '#f0f2f2',
    marginTop: 5,
    flex: 1,
    borderRadius: 8,
    padding: 3,
    flexDirection: 'row',
    flex: 1,
    justifyContent: 'space-around',
  },
  stars: {
    flex: 1,
    flexDirection: 'row',
    justifyContent: 'center',
  },
  likes: {
    flex: 1,
    justifyContent: 'center',
    flexDirection: 'row',
  },
  movieTitle: {
    fontSize: 15,
    fontWeight: '500',
    color: 'black',
  },
});

export default MoviesMovieScreen;
