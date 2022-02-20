import React from "react";
import {
  Document,
  Page,
  Text,
  Image,
  View,
  StyleSheet,
} from "@react-pdf/renderer";
import logo from "../../images/cinemalogo.png";

const styles = StyleSheet.create({
  page: {
    flexDirection: "row",
    backgroundColor: "#fff",
  },
  view: {
    display: "flex",
    flexDirection: "row",
    justifyContent: "center",
    width: "100%",
  },
});

const GeneratePdf = ({ dataUrl }) => {
  return (
    <Document>
      <Page size="A4" style={styles.page}>
        <View style={styles.view}>
          <Image allowDangerousPaths src={logo} />
        </View>
        <View style={styles.view}>
          <Image allowDangerousPaths src={dataUrl} />
        </View>
        <View>
          <Text>Movie Name: Spider Man: Home Coming</Text>
        </View>
      </Page>
    </Document>
  );
};

export default GeneratePdf;
