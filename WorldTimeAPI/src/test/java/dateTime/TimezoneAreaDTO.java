package dateTime;

public class TimezoneAreaDTO {
    String abbreviation;
    String client_ip;
    String datetime;
    int day_of_week;
    int day_of_year;
    String dst;
    String dst_from;
    int dst_offset;
    String dst_until;
    int raw_offset;
    String timezone;
    long unixtime;

    @Override
    public String toString() {
        return "TimezoneAreaDTO{" +
                "abbreviation='" + abbreviation + '\'' +
                ", client_ip='" + client_ip + '\'' +
                ", datetime='" + datetime + '\'' +
                ", day_of_week=" + day_of_week +
                ", day_of_year=" + day_of_year +
                ", dst='" + dst + '\'' +
                ", dst_from='" + dst_from + '\'' +
                ", dst_offset=" + dst_offset +
                ", dst_until='" + dst_until + '\'' +
                ", raw_offset=" + raw_offset +
                ", timezone='" + timezone + '\'' +
                ", unixtime=" + unixtime +
                ", utc_datetime='" + utc_datetime + '\'' +
                ", utc_offset='" + utc_offset + '\'' +
                ", week_number=" + week_number +
                '}';
    }

    String utc_datetime;
    String utc_offset;
    int week_number;




}