import java.time.Duration;
import java.time.LocalDateTime;
import java.util.*;

public class Statistics {
    private int totalTraffic;
    private LocalDateTime minTime;
    private LocalDateTime maxTime;
    private final Set<String> existingPages = new HashSet<>();
    private final Map<String, Integer> osStatic = new HashMap<>();
    private final Set<String> nonExistingPages = new HashSet<>();
    private final Map<String, Integer> browserStatic = new HashMap<>();
    private int userRequestsCount = 0;
    private int errRequest = 0;
    private final Set<String> userIps = new HashSet<>();

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
        if (entry.getHttpCode() == 404) {
            nonExistingPages.add(entry.getRequestUrl());
        }
        if (isErrorCode(entry.getHttpCode())) {
            errRequest++;
        }
        String userAgentString = entry.getUserAgent();
        if (userAgentString != null && !userAgentString.isEmpty()) {
            UserAgent userAgent = new UserAgent(userAgentString);

            UserAgent.OperatingSystem os = userAgent.getOs();
            String osName = os.name();
            osStatic.put(osName, osStatic.getOrDefault(osName, 0) + 1);

            UserAgent.Browser browser = userAgent.getBrowser();
            String browserName = browser.name();
            browserStatic.put(browserName, browserStatic.getOrDefault(browserName, 0) + 1);
            if (!userAgent.isBot()) {
                userRequestsCount++;
                userIps.add(entry.getIp());
            }
        }
    }

    private boolean isErrorCode(int httpCode) {
        return httpCode >= 400 && httpCode < 600;
    }

    public Map<String, Double> getBrowserStats() {
        Map<String, Double> browserStats = new HashMap<>();
        int totalCount = browserStatic.values().stream()
                .mapToInt(Integer::intValue)
                .sum();

        if (totalCount == 0) {
            return Collections.unmodifiableMap(browserStats);
        }

        for (Map.Entry<String, Integer> entry : browserStatic.entrySet()) {
            double proportion = (double) entry.getValue() / totalCount;
            browserStats.put(entry.getKey(), proportion);
        }

        return Collections.unmodifiableMap(browserStats);
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

    public List<String> getNonExistingPages() {
        List<String> sorted = new ArrayList<>(nonExistingPages);
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

    public double getAverageVisitsPerHour() {
        if (this.minTime == null || this.maxTime == null) {
            return 0.0;
        }

        Duration duration = Duration.between(this.minTime, this.maxTime);
        long hours = Math.max(duration.toHours(), 1);

        return (double) userRequestsCount / hours;
    }

    public double getAverageNumberOfErroneousRequestsPerHour() {
        if (this.minTime == null || this.maxTime == null) {
            return 0.0;
        }

        Duration duration = Duration.between(this.minTime, this.maxTime);
        long hours = Math.max(duration.toHours(), 1);

        return (double) errRequest / hours;
    }
    public double getAverageVisitsPerUser() {
        if (userIps.isEmpty()) {
            return 0.0;
        }
        return (double) userRequestsCount / userIps.size();
    }
}





