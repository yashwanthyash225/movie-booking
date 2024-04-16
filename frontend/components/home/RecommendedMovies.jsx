import {
  View,
  Text,
  StyleSheet,
  Image,
  FlatList,
  Dimensions,
  TouchableOpacity,
} from 'react-native';
import IonIcon from 'react-native-vector-icons/Ionicons';
import FontAwesomeIcon from 'react-native-vector-icons/FontAwesome';
import {useEffect, useState} from 'react';
import {getRecommendedMovies} from './HomeWeb';

function RecommendedMovies({navigate}) {
  const windowWidth = Dimensions.get('window').width;
  const windowHeight = Dimensions.get('window').height;

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
  };

  const gett = async () => {
    const movies = await getRecommendedMovies();
    const x = movies.map((item, index) => {
      return {
        key: index,
        id: item.id,
        title: item.title,
        imageSrc: getImageSrc(item.id),
        stars: 8.9,
        likes: '243.3k',
      };
    });
    setMoviesList(x);
  };

  const [moviesList, setMoviesList] = useState([]);

  useEffect(() => {
    gett();
  }, []);

  const renderMovieItem = ({item}) => {
    return (
      <View style={styles.movieImage}>
        <TouchableOpacity
          onPress={() => {
            navigate.navigate('MoviesTheatreScreen', item);
          }}>
          <Image
            source={item.imageSrc}
            style={{
              height: windowHeight / 3,
              width: windowWidth / 2,
              borderRadius: 6,
            }}
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
    <View style={styles.box}>
      <Text style={styles.header}> Recommended Movies</Text>
      <FlatList
        data={moviesList}
        keyExtractor={(item, index) => index}
        horizontal
        showsHorizontalScrollIndicator={false}
        renderItem={item => renderMovieItem(item)}></FlatList>
    </View>
  );
}

const styles = StyleSheet.create({
  box: {
    marginTop: 10,
    marginLeft: 10,
  },
  header: {
    fontSize: 20,
    fontWeight: 'bold',
    color: 'black',
  },
  movieImage: {
    marginHorizontal: 10,
    marginTop: 10,
  },
  movieTitle: {
    fontSize: 15,
    fontWeight: '500',
    color: 'black',
  },
  ratings: {
    backgroundColor: '#f0f2f2',
    marginTop: 5,
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
  ratingsvalue: {
    paddingLeft: 7,
  },
});

export default RecommendedMovies;
