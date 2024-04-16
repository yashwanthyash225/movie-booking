import {useEffect, useRef, useState} from 'react';
import {
  View,
  Text,
  StyleSheet,
  Dimensions,
  FlatList,
  TouchableOpacity,
} from 'react-native';
import ImageSliderImage from './ImageSliderImage';
import {getImageSliderMovies} from './HomeWeb';

function ImageSlider({navigate}) {
  const windowWidth = Dimensions.get('window').width;
  const windowHeight = Dimensions.get('window').height;

  const [imageIndex, setImageIndex] = useState(0);
  const flatlistref = useRef();

  const [moviesList, setMoviesList] = useState([]);

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
    const movies = await getImageSliderMovies();
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

  useEffect(() => {
    gett();
  }, []);

  const imageRender = ({item, index}) => {
    return (
      <ImageSliderImage data={item} onImgPress={onImgPress} index={index} />
    );
  };

  const onImgPress = () => {
    navigate.navigate('MoviesTheatreScreen', moviesList.at(imageIndex));
  };

  const dotIndicators = () => {
    return moviesList.map((dot, index) => {
      if (index === imageIndex) {
        return (
          <TouchableOpacity
            style={{
              backgroundColor: 'white',
              height: 10,
              width: 10,
              borderRadius: 5,
              marginHorizontal: 6,
            }}></TouchableOpacity>
        );
      }
      return (
        <View
          style={{
            backgroundColor: 'gray',
            height: 10,
            width: 10,
            borderRadius: 5,
            marginHorizontal: 6,
          }}></View>
      );
    });
  };

  const handleScroll = event => {
    loc = event.nativeEvent.contentOffset.x;
    ind = loc / windowWidth;
    setImageIndex(ind);
  };

  const getItemLayout = (data, index) => ({
    length: windowWidth,
    offset: windowWidth * index,
    index: index,
  });

  useEffect(() => {
    let interval = setInterval(() => {
      let nextIndex = (imageIndex + 1) % moviesList.length;
      flatlistref.current.scrollToIndex({index: nextIndex, animated: true});
    }, 10000);

    return () => clearInterval(interval);
  });

  if (moviesList.length == 0) {
    return (
      <View>
        <Text>Loading</Text>
      </View>
    );
  } else {
    return (
      <View>
        <FlatList
          ref={flatlistref}
          horizontal
          data={moviesList}
          getItemLayout={getItemLayout}
          renderItem={(item, index) => imageRender(item, index)}
          pagingEnabled={true}
          onScroll={handleScroll}
          showsHorizontalScrollIndicator={false}></FlatList>
        <View
          style={{
            flexDirection: 'row',
            position: 'absolute',
            top: windowHeight / 3.25,
            left: windowWidth / 2.5,
          }}>
          {dotIndicators()}
        </View>
      </View>
    );
  }
}

const styles = StyleSheet.create({});

export default ImageSlider;
