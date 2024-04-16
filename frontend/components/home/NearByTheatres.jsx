import {useEffect, useState} from 'react';
import {FlatList, StyleSheet, Text, View, Dimensions} from 'react-native';
import MatIcon from 'react-native-vector-icons/MaterialIcons';
import {getAllTheatres} from '../theatre/TheatreWeb';

const windowWidth = Dimensions.get('window').width;
const windowHeight = Dimensions.get('window').height;

function NearByTheatres({navigate}) {
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
        distance: '2.6 mi',
      };
    });
    setTheatreList(x);
  };

  useEffect(() => {
    fixTheatres();
  }, []);

  const renderTheatreItem = ({item}) => {
    return (
      <View style={styles.theatreCard}>
        <View style={styles.favorite}>
          <MatIcon name="favorite-border" color="black" size={25}></MatIcon>
        </View>
        <View style={styles.details}>
          <Text style={styles.theatreName}>{item.name}</Text>
          <Text style={styles.theatreAddress}>{item.address}</Text>
          <Text style={styles.theatreDistance}>{item.distance}</Text>
        </View>
      </View>
    );
  };

  if (theatreList.length == 0) {
    return (
      <View>
        <Text>Loading</Text>
      </View>
    );
  } else {
    return (
      <View>
        <Text style={styles.mainHeading}>Theatres Near By</Text>
        <View>
          <FlatList
            data={theatreList}
            keyExtractor={(item, index) => index}
            horizontal
            showsHorizontalScrollIndicator={false}
            renderItem={item => renderTheatreItem(item)}></FlatList>
        </View>
      </View>
    );
  }
}

const styles = StyleSheet.create({
  mainHeading: {
    color: 'black',
    fontWeight: 'bold',
    fontSize: 20,
    marginTop: 10,
    marginLeft: 10,
  },
  theatreCard: {
    width: windowWidth / 2,
    marginTop: 10,
    marginHorizontal: 10,
    flexDirection: 'row',
    justifyContent: 'center',
    borderColor: 'black',
    borderWidth: 2,
    padding: 10,
    borderRadius: 7,
  },
  favorite: {
    marginRight: 10,
  },
  theatreName: {
    fontWeight: 'bold',
    color: 'black',
    fontSize: 15,
  },
});

export default NearByTheatres;
