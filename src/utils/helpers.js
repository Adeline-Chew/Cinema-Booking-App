import moment from "moment";
import numeral from "numeral";

export function parseCurrency(number) {
  return numeral(parseFloat(number)).format(
    Number.isInteger(number) ? "0,0.00" : "0,0.00"
  );
}

export function parseDate(date) {
  return moment(date).format("DD MMM YYYY");
}

export function parseTime(date) {
  return moment(date).format("hh:mm a");
}

export function parseDateTime(date) {
  return moment(date).format("DD MMM YYYY hh:mm a");
}
