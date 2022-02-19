import React from "react";
import QRCode from "qrcode.react";

const QRGenerator = (props) => {
  const { valueString, documentId } = props;
  return (
    <div>
      <QRCode
        id={documentId}
        value={valueString}
        size={128}
        bgColor={"#fff"}
        fgColor={"#000"}
        level="H"
        includeMargin={true}
      />
    </div>
  );
};

export default QRGenerator;
