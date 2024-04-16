import {ScrollView, Text, View} from 'react-native';
import ImageSlider from '../../components/home/ImageSlider';
import RecommendedMovies from '../../components/home/RecommendedMovies';
import NearByTheatres from '../../components/home/NearByTheatres';

function HomePageScreen({navigation}) {
  return (
    <ScrollView style={{backgroundColor: 'white'}}>
      <ImageSlider navigate={navigation} />
      <RecommendedMovies navigate={navigation} />
      <NearByTheatres navigate={navigation} />
    </ScrollView>
  );
}

export default HomePageScreen;
