import java.time.Duration;
import java.time.LocalDateTime;
import java.util.*;

public class Statistics {
    private int totalTraffic;
    private LocalDateTime minTime;
    private LocalDateTime maxTime;
    private final Set<String> existingPages = new HashSet<>();
    private final Map<String, Integer> osStatic = new HashMap<>();

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
        if (entry.getHttpCode() == 200) {
            existingPages.add(entry.getRequestUrl());
        }
        String userAgentString = entry.getUserAgent();
        UserAgent userAgent = new UserAgent(userAgentString);
        UserAgent.OperatingSystem os = userAgent.getOs();
        String osName = os.name();
        osStatic.put(osName, osStatic.getOrDefault(osName, 0) + 1);
    }
    public Map<String, Double> getOperatingSystemStats() {
        Map<String, Double> stats = new HashMap<>();
        int totalCount = osStatic.values().stream().mapToInt(Integer::intValue).sum();

        if (totalCount == 0) {
            return Collections.unmodifiableMap(stats);
        }

        for (Map.Entry<String, Integer> entry : osStatic.entrySet()) {
            double proportion = (double) entry.getValue() / totalCount;
            stats.put(entry.getKey(), proportion);
        }

        return Collections.unmodifiableMap(stats);
    }
    public List<String> getExistingPages() {
        List<String> sorted = new ArrayList<>(existingPages);
        Collections.sort(sorted);
        return Collections.unmodifiableList(sorted);
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





