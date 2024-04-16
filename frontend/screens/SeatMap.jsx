import {useEffect, useRef, useState} from 'react';
import {
  FlatList,
  StyleSheet,
  Text,
  TouchableOpacity,
  View,
  Dimensions,
  SafeAreaView,
  TouchableWithoutFeedback,
} from 'react-native';
import MatCommunityIcon from 'react-native-vector-icons/MaterialCommunityIcons';

const windowWidth = Dimensions.get('window').width;
const windowHeight = Dimensions.get('window').height;

function SeatMap({route, navigation}) {
  const {timeIndex, theatreIndex, theatreItem, movieItem, timings} =
    route.params;
  const [currIndex, setCurrIndex] = useState(timeIndex);
  const timeListRef = useRef();
  const [seats, SetSeats] = useState([
    {
      seatRow: 'A',
      seatList: [
        {available: 0, seatNum: '1'},
        {available: 0, seatNum: '2'},
        {available: 1, seatNum: '3'},
        {available: 0, seatNum: '4'},
        {available: 1, seatNum: '5'},
        {available: 0, seatNum: '6'},
        {available: 1, seatNum: '7'},
        {available: 1, seatNum: '8'},
        {available: 0, seatNum: '9'},
        {available: 1, seatNum: '10'},
        {available: 0, seatNum: '11'},
        {available: 1, seatNum: '12'},
      ],
    },
    {
      seatRow: 'B',
      seatList: [
        {available: 0, seatNum: '1'},
        {available: 1, seatNum: '2'},
        {available: 1, seatNum: '3'},
        {available: 0, seatNum: '4'},
        {available: 1, seatNum: '5'},
      ],
    },
    {
      seatRow: 'C',
      seatList: [
        {available: 1, seatNum: '1'},
        {available: 0, seatNum: '2'},
        {available: 1, seatNum: '3'},
        {available: 1, seatNum: '4'},
      ],
    },
  ]);

  const [selectedSeats, setSelectedSeats] = useState([]);

  const renderTimings = ({item, index}) => {
    return (
      <TouchableOpacity
        activeOpacity={0.7}
        style={[
          styles.timeCard,
          {backgroundColor: index === currIndex ? 'tomato' : 'white'},
        ]}
        onPress={() => {
          setCurrIndex(index);
        }}>
        <Text
          style={[
            styles.timeValue,
            {color: index === currIndex ? 'white' : 'black'},
          ]}>
          {item.timing}
        </Text>
      </TouchableOpacity>
    );
  };

  const addSeat = seat => {
    setSelectedSeats([...selectedSeats, seat]);
  };

  const removeSeat = seat => {
    newList = selectedSeats.filter(e => e != seat);
    setSelectedSeats(newList);
  };

  const renderSeatRowItem = ({item, index}) => {
    return (
      <View style={styles.seatRow}>
        {item.seatList.map(e => {
          if (e.available === 0) {
            return (
              <TouchableWithoutFeedback>
                <MatCommunityIcon
                  name="seat"
                  size={35}
                  color="black"></MatCommunityIcon>
              </TouchableWithoutFeedback>
            );
          } else if (selectedSeats.includes(item.seatRow + e.seatNum)) {
            return (
              <TouchableOpacity
                onPress={() => removeSeat(item.seatRow + e.seatNum)}>
                <MatCommunityIcon
                  name="seat"
                  size={35}
                  color="green"></MatCommunityIcon>
              </TouchableOpacity>
            );
          }
          return (
            <TouchableOpacity onPress={() => addSeat(item.seatRow + e.seatNum)}>
              <MatCommunityIcon
                name="seat-outline"
                size={35}
                color="black"></MatCommunityIcon>
            </TouchableOpacity>
          );
        })}
      </View>
    );
  };

  return (
    <SafeAreaView style={styles.screen}>
      <View style={styles.screenHeader}>
        <View style={styles.movieBox}>
          <Text style={styles.movieTitle}>{movieItem.title}</Text>
        </View>
        <View style={styles.theatreBox}>
          <Text style={styles.theatreName}>{theatreItem.name}</Text>
          <Text style={styles.theatreAddress}>{theatreItem.address}</Text>
        </View>
      </View>
      <View style={styles.timelist}>
        <FlatList
          ref={timeListRef}
          data={timings}
          keyExtractor={(item, index) => index}
          horizontal
          showsHorizontalScrollIndicator={false}
          renderItem={(item, index) => renderTimings(item, index)}></FlatList>
      </View>
      <View style={styles.seatBox} zoomScale={2}>
        <FlatList
          data={seats}
          keyExtractor={(item, index) => index}
          showsVerticalScrollIndicator={false}
          renderItem={(item, index) =>
            renderSeatRowItem(item, index)
          }></FlatList>
      </View>
    </SafeAreaView>
  );
}

const styles = StyleSheet.create({
  screen: {
    backgroundColor: 'white',
    flex: 1,
  },
  screenHeader: {
    flexDirection: 'row',
    padding: 10,
    position: 'fixed',
  },
  movieBox: {
    flex: 1,
    alignItems: 'center',
  },
  theatreBox: {
    flex: 1,
    alignItems: 'center',
  },
  movieTitle: {
    fontWeight: 'bold',
    fontSize: 30,
    color: 'black',
  },
  theatreName: {
    fontWeight: 'bold',
    fontSize: 20,
    color: 'black',
  },
  timelist: {
    position: 'fixed',
    height: windowHeight / 15,
  },
  timeCard: {
    width: windowWidth / 4,
    alignItems: 'center',
    padding: 10,
    borderTopWidth: 1,
    borderBottomWidth: 1,
    justifyContent: 'center',
  },
  timeValue: {
    fontWeight: 'bold',
    fontSize: 15,
  },
  seatBox: {
    borderWidth: 2,
    margin: 10,
    padding: 10,
  },
  seatRow: {
    flex: 1,
    alignItems: 'center',
    flexDirection: 'row',
    justifyContent: 'center',
  },
});

export default SeatMap;
