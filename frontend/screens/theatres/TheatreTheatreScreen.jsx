import {useEffect, useState} from 'react';
import {
  Dimensions,
  FlatList,
  StyleSheet,
  Text,
  TouchableOpacity,
  View,
} from 'react-native';
import {getAllTheatres} from '../../components/theatre/TheatreWeb';

const windowWidth = Dimensions.get('window').width;
const windowHeight = Dimensions.get('window').height;

function TheatreTheatreScreen({route, navigation}) {
  const [theatreList, setTheatreList] = useState([]);

  const fixTheatres = async () => {
    const theatres = await getAllTheatres();
    const x = theatres.map((item, index) => {
      return {
        key: index,
        id: item.id,
        name: item.name,
        address: item.address,
        locationId: item.locationId,
      };
    });
    setTheatreList(x);
  };

  useEffect(() => {
    fixTheatres();
  }, []);

  function renderTheatreItem({item}) {
    return (
      <TouchableOpacity
        style={styles.theatreCard}
        onPress={() => navigation.navigate('TheatreMovieScreen', item)}>
        <View style={styles.titleView}>
          <Text style={styles.titleText}>{item.name}</Text>
          <Text style={styles.addressText}>{item.address}</Text>
        </View>
      </TouchableOpacity>
    );
  }

  if (theatreList.length == 0) {
    return (
      <View>
        <Text>Loading</Text>
      </View>
    );
  } else {
    return (
      <FlatList
        data={theatreList}
        renderItem={item => renderTheatreItem(item)}
        showsVerticalScrollIndicator={false}></FlatList>
    );
  }
}

const styles = StyleSheet.create({
  theatreCard: {
    marginVertical: 10,
    marginHorizontal: 10,
    shadowColor: '#000',
    elevation: 15,
    padding: 20,
    height: windowHeight / 5,
    backgroundColor: 'white',
  },
  titleView: {},
  titleText: {
    fontSize: 30,
    color: 'black',
  },
  addressText: {
    fontSize: 15,
  },
});

export default TheatreTheatreScreen;
