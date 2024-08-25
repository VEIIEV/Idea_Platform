import java.nio.file.Path;

public class JsonReader {
//- Минимальное время полета между городами Владивосток и Тель-Авив для каждого авиаперевозчика
//- Разницу между средней ценой  и медианой для полета между городами  Владивосток и Тель-Авив



    public static void find_min_time(){
        Path.of(" ")
    }



    public static  class Ticket {
        private String origin;
        private String originName;
        private String destination;
        private String destinationName;
        private String departureDate;
        private String departureTime;
        private String arrivalDate;
        private String arrivalTime;
        private String carrier;
        private int stops;
        private int price;

        // Конструктор
        public Ticket(String origin, String originName, String destination, String destinationName,
                      String departureDate, String departureTime, String arrivalDate,
                      String arrivalTime, String carrier, int stops, int price) {
            this.origin = origin;
            this.originName = originName;
            this.destination = destination;
            this.destinationName = destinationName;
            this.departureDate = departureDate;
            this.departureTime = departureTime;
            this.arrivalDate = arrivalDate;
            this.arrivalTime = arrivalTime;
            this.carrier = carrier;
            this.stops = stops;
            this.price = price;
        }

        // Геттеры и сеттеры для всех полей
        public String getOrigin() {
            return origin;
        }

        public void setOrigin(String origin) {
            this.origin = origin;
        }

        public String getOriginName() {
            return originName;
        }

        public void setOriginName(String originName) {
            this.originName = originName;
        }

        public String getDestination() {
            return destination;
        }

        public void setDestination(String destination) {
            this.destination = destination;
        }

        public String getDestinationName() {
            return destinationName;
        }

        public void setDestinationName(String destinationName) {
            this.destinationName = destinationName;
        }

        public String getDepartureDate() {
            return departureDate;
        }

        public void setDepartureDate(String departureDate) {
            this.departureDate = departureDate;
        }

        public String getDepartureTime() {
            return departureTime;
        }

        public void setDepartureTime(String departureTime) {
            this.departureTime = departureTime;
        }

        public String getArrivalDate() {
            return arrivalDate;
        }

        public void setArrivalDate(String arrivalDate) {
            this.arrivalDate = arrivalDate;
        }

        public String getArrivalTime() {
            return arrivalTime;
        }

        public void setArrivalTime(String arrivalTime) {
            this.arrivalTime = arrivalTime;
        }

        public String getCarrier() {
            return carrier;
        }

        public void setCarrier(String carrier) {
            this.carrier = carrier;
        }

        public int getStops() {
            return stops;
        }

        public void setStops(int stops) {
            this.stops = stops;
        }

        public int getPrice() {
            return price;
        }

        public void setPrice(int price) {
            this.price = price;
        }

        // Метод для отображения информации о билете
        @Override
        public String toString() {
            return "Ticket{" +
                    "origin='" + origin + '\'' +
                    ", originName='" + originName + '\'' +
                    ", destination='" + destination + '\'' +
                    ", destinationName='" + destinationName + '\'' +
                    ", departureDate='" + departureDate + '\'' +
                    ", departureTime='" + departureTime + '\'' +
                    ", arrivalDate='" + arrivalDate + '\'' +
                    ", arrivalTime='" + arrivalTime + '\'' +
                    ", carrier='" + carrier + '\'' +
                    ", stops=" + stops +
                    ", price=" + price +
                    '}';
        }
    }

}
