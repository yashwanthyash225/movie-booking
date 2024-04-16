import {Dimensions, Image, TouchableOpacity} from 'react-native';

const windowWidth = Dimensions.get('window').width;
const windowHeight = Dimensions.get('window').height;

function ImageSliderImage({data, index, onImgPress}) {
  return (
    <TouchableOpacity onPress={() => onImgPress()}>
      <Image
        source={data.imageSrc}
        style={{height: windowHeight / 3, width: windowWidth}}
        resizeMode="stretch"
      />
    </TouchableOpacity>
  );
}

export default ImageSliderImage;
