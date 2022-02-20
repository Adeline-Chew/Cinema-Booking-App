import React from "react";
import QRCode from "qrcode.react";
import logo from "../../images/cinemalogo_white.png";

const QRGenerator = (props) => {
  const { valueString, documentId } = props;
  return (
    <div>
      <QRCode
        id={documentId}
        // Change to deployed app link
        value={valueString}
        size={128}
        bgColor={"#fff"}
        fgColor={"#000"}
        level="H"
        includeMargin={true}
        imageSettings={{
          src: logo,
          height: 24,
          width: 24,
          excavate: true,
        }}
      />
    </div>
  );
};

export default QRGenerator;
