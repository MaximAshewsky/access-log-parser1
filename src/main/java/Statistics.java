import java.time.Duration;
import java.time.LocalDateTime;

public class Statistics {
    private int totalTraffic;
    private LocalDateTime minTime;
    private LocalDateTime maxTime;

    public Statistics() {
        this.totalTraffic = 0;
        this.minTime = null;
        this.maxTime = null;
    }

    public void addEntry(LogEntry entry) {
        this.totalTraffic += entry.getBytes();
        LocalDateTime currentTime = entry.getTimestamp();

        if (this.minTime == null || currentTime.isBefore(this.minTime)) {
            this.minTime = currentTime;
        }

        if (this.maxTime == null || currentTime.isAfter(this.maxTime)) {
            this.maxTime = currentTime;
        }

    }
    public double getTrafficRate() {
        if (this.minTime == null || this.maxTime == null) {
            return 0.0;
        }
        Duration duration = Duration.between(this.minTime, this.maxTime);
        long hours = duration.toHours();
        if (hours == 0) {
            hours = 1;
        }
        return (double) this.totalTraffic / hours;
    }
}



