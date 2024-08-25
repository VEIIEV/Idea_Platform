import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

public class MyJsonReader {
    //- Минимальное время полета между городами Владивосток и Тель-Авив для каждого авиаперевозчика
//- Разницу между средней ценой  и медианой для полета между городами  Владивосток и Тель-Авив
    private static final String FILE_NAME = "tickets.json";


    public static void main(String[] args) throws FileNotFoundException {
        Gson gson = new Gson();
        JsonReader jsr = new JsonReader(new FileReader(FILE_NAME));
        TicketWrapper ticketWrap = gson.fromJson(jsr, TicketWrapper.class);
        List<Ticket> tickets = ticketWrap.getTickets();
        List<Duration> minTimes = findMinTime(tickets, "Владивосток", "Тель-Авив");
        double diff = findDif(tickets, "Владивосток", "Тель-Авив");


    }

    public static double findDif(List<Ticket> tickets, String dCity, String aCity) {
        List<Ticket> ticketsFiltered = tickets.stream().
                filter((ticket) -> ticket.origin_name.equals(dCity) && ticket.destination_name.equals(aCity)).
                toList();
        double avg = ticketsFiltered.stream().mapToInt((ticket) -> ticket.price).average().getAsDouble();
        double median = findMedian(ticketsFiltered);
        System.out.println(avg - median + " rub");
        return avg - median;
    }

    public static List<Duration> findMinTime(List<Ticket> tickets, String dCity, String aCity) throws FileNotFoundException {
        Set<String> companies = tickets.stream().map(ticket -> ticket.carrier).collect(Collectors.toSet());
        List<Duration> durations = new ArrayList<>();
        for (String company : companies) {
            Duration duration = tickets.stream().
                    filter((ticket) -> ticket.origin_name.equals(dCity) && ticket.destination_name.equals(aCity)).
                    filter(ticket -> ticket.carrier.equals(company)).
                    min(Comparator.comparing(MyJsonReader::findTravelTime)).
                    map(MyJsonReader::findTravelTime).get();
            durations.add(duration);
            System.out.println(company + ": " + duration.toMinutes() + " minute");
        }

        return durations;
    }

    private static double findMedian(List<Ticket> tickets) {
        int[] prices = tickets.stream().
                mapToInt((a) -> a.price).
                toArray();
        Arrays.sort(prices);
        int n = prices.length;

        if (n % 2 != 0) {
            return prices[n / 2];
        }
        return (prices[(n / 2) - 1] + prices[n / 2]) / 2.0;
    }

    private static Duration findTravelTime(Ticket ticket) {
        String arrivalDate = ticket.arrival_date;
        String arrivalTime = ticket.arrival_time;
        String departureDate = ticket.departure_date;
        String departureTime = ticket.departure_time;

        LocalDateTime arrival = convertToDateTime(arrivalDate, arrivalTime);
        LocalDateTime departure = convertToDateTime(departureDate, departureTime);
        return Duration.between(departure, arrival);
    }

    private static LocalDateTime convertToDateTime(String date, String time) {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd.MM.yy H:mm");
        return LocalDateTime.parse(date + " " + time, dateFormatter);
    }

    static class TicketWrapper {
        List<Ticket> tickets;

        public List<Ticket> getTickets() {
            return tickets;
        }

        public void setTickets(List<Ticket> tickets) {
            this.tickets = tickets;
        }
    }

    public static class Ticket {
        private String origin;
        private String origin_name;
        private String destination;
        private String destination_name;
        private String departure_date;
        private String departure_time;
        private String arrival_date;
        private String arrival_time;
        private String carrier;
        private int stops;
        private int price;

        // Конструктор
        public Ticket(String origin, String originName, String destination, String destinationName,
                      String departureDate, String departureTime, String arrivalDate,
                      String arrivalTime, String carrier, int stops, int price) {
            this.origin = origin;
            this.origin_name = originName;
            this.destination = destination;
            this.destination_name = destinationName;
            this.departure_date = departureDate;
            this.departure_time = departureTime;
            this.arrival_date = arrivalDate;
            this.arrival_time = arrivalTime;
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

        public String getOrigin_name() {
            return origin_name;
        }

        public void setOrigin_name(String origin_name) {
            this.origin_name = origin_name;
        }

        public String getDestination() {
            return destination;
        }

        public void setDestination(String destination) {
            this.destination = destination;
        }

        public String getDestination_name() {
            return destination_name;
        }

        public void setDestination_name(String destination_name) {
            this.destination_name = destination_name;
        }

        public String getDeparture_date() {
            return departure_date;
        }

        public void setDeparture_date(String departure_date) {
            this.departure_date = departure_date;
        }

        public String getDeparture_time() {
            return departure_time;
        }

        public void setDeparture_time(String departure_time) {
            this.departure_time = departure_time;
        }

        public String getArrival_date() {
            return arrival_date;
        }

        public void setArrival_date(String arrival_date) {
            this.arrival_date = arrival_date;
        }

        public String getArrival_time() {
            return arrival_time;
        }

        public void setArrival_time(String arrival_time) {
            this.arrival_time = arrival_time;
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
                    ", originName='" + origin_name + '\'' +
                    ", destination='" + destination + '\'' +
                    ", destinationName='" + destination_name + '\'' +
                    ", departureDate='" + departure_date + '\'' +
                    ", departureTime='" + departure_time + '\'' +
                    ", arrivalDate='" + arrival_date + '\'' +
                    ", arrivalTime='" + arrival_time + '\'' +
                    ", carrier='" + carrier + '\'' +
                    ", stops=" + stops +
                    ", price=" + price +
                    '}';
        }
    }

}
