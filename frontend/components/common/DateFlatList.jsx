import {useState} from 'react';
import {
  Dimensions,
  FlatList,
  StyleSheet,
  Text,
  TouchableOpacity,
  View,
} from 'react-native';

const windowWidth = Dimensions.get('window').width;
const windowHeight = Dimensions.get('window').height;

function DateFlatList({dateList, onDatePress}) {
  const [dateIndex, setDateIndex] = useState(0);

  const onCardPress = index => {
    setDateIndex(index);
    onDatePress(index);
  };

  function renderDateItem({item, index}) {
    return (
      <TouchableOpacity
        onPress={() => onCardPress(index)}
        style={[
          styles.dateCard,
          {backgroundColor: index === dateIndex ? 'tomato' : 'white'},
        ]}>
        <Text
          style={[
            styles.weekText,
            {color: index === dateIndex ? 'white' : 'black'},
          ]}>
          {item.week}
        </Text>
        <Text
          style={[
            styles.dateText,
            {color: index === dateIndex ? 'white' : 'black'},
          ]}>
          {item.date}
        </Text>
        <Text
          style={[
            styles.monthText,
            {color: index === dateIndex ? 'white' : 'black'},
          ]}>
          {item.month}
        </Text>
      </TouchableOpacity>
    );
  }

  return (
    <View>
      <FlatList
        data={dateList}
        renderItem={(item, index) => renderDateItem(item, index)}
        horizontal
      />
    </View>
  );
}

const styles = StyleSheet.create({
  dateCard: {
    width: windowWidth / 5,
    padding: 5,
    alignItems: 'center',
  },
  weekText: {
    fontSize: 15,
    fontWeight: 'bold',
  },
  dateText: {
    fontSize: 25,
    fontWeight: 'bold',
  },
  monthText: {
    fontSize: 15,
    fontWeight: 'bold',
  },
});

export default DateFlatList;
