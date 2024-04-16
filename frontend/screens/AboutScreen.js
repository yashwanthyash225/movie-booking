import { View, Text, StyleSheet } from "react-native";

function AboutScreen({navigation}) {
    return (
        <View style={styles.about}>
            <Text style={styles.text} onPress={() => {navigation.navigate("About")}}>About Screen !!</Text>
        </View>
    );
}

const styles = StyleSheet.create({
    about: {
        flex: 1,
        alignItems: 'center',
        justifyContent: 'center'
    },
    text: {
        fontSize: 30,
        color: 'green'
    }
});

export default AboutScreen;