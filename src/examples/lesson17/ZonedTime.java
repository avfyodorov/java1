package examples.lesson17;

/**
 * Автор: Фёдоров Александр
 * Дата:  29.07.2022  17:21
 */

class TimeZone {
    int hours; //часовой сдвиг, может быть отрицательным
    int minutes; //сдвиг в минутах
    public TimeZone(int hours){
        this.hours = hours;
        this.minutes = 0;
    }
    public TimeZone(int hours, int minutes){
        this.hours = hours;
        this.minutes = minutes;
    }

}
class Time {
    int hours;
    int minutes;
    int seconds;

    public Time(int hours, int minutes, int seconds) {
        this.hours = hours;
        this.minutes = minutes;
        this.seconds = seconds;
    }

    public String toString() {
        String h, m, s;
        final int tt = 10;
        if (hours < tt)
            h = "0" + hours;
        else
            h = Integer.toString(hours);
        if (minutes < tt)
            m = "0" + minutes;
        else
            m = Integer.toString(minutes);
        if (seconds < tt)
            s = "0" + seconds;
        else
            s = Integer.toString(seconds);
        return h + ":" + m + ":" + s;
    }

    public int toSeconds() {
        return hours * 60 * 60 + minutes * 60 + seconds;
    }

    public int secondsBetween(Time time) {
        return Math.abs(this.hours * 60 * 60 + this.minutes * 60 + this.seconds - time.toSeconds());
    }

    public TimeZone getTimeZone() {
        return null;
    }

    public static void main(String[] args) {

    }
}

public class ZonedTime extends Time {
        TimeZone zone;

        public ZonedTime(int hours, int minutes, int seconds) {
            super(hours, minutes, seconds);
            this.zone = getTimeZone();
        }

        public ZonedTime(int hours, int minutes, int seconds, TimeZone zone) {
            super(hours, minutes, seconds);
            this.zone = zone;
        }

        @Override
        public TimeZone getTimeZone() {
            return zone;
        }

    @Override
    public int secondsBetween(Time time) {
        TimeZone k1 = this.getTimeZone();
        TimeZone k2 = time.getTimeZone();
        return (k1.hours * 60 * 60 + k1.minutes * 60) - (k2.hours * 60 * 60 + k2.minutes * 60);
    }


    public static void main(String[] args) {
        TimeZone t1 = new TimeZone(2,4);
        ZonedTime z1 = new ZonedTime(t1.hours, t1.minutes,0);
        TimeZone t2 = new TimeZone(1,2);
        ZonedTime z2 = new ZonedTime(t2.hours, t2.minutes,0);
        System.out.println(z1.secondsBetween(z2));
    }

    }
