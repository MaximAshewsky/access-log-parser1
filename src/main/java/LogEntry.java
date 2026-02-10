import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LogEntry {
    public enum HttpMethod {
        GET, POST, PUT, DELETE, HEAD, OPTIONS, TRACE, CONNECT
    }
    private final String ip;
    private final String dash1;
    private final String dash2;
    private final LocalDateTime timestamp;
    private final String request;
    private final HttpMethod httpMethod;
    private final int httpCode;
    private final long bytes;
    private final String referer;
    private final String userAgent;
    private static final DateTimeFormatter DATE_TIME_FORMATTER =
            DateTimeFormatter.ofPattern("dd/MMM/yyyy:HH:mm:ss Z", java.util.Locale.ENGLISH);

    public LogEntry(String line) {
        String regex = "^(\\S+)\\s+(-|-|\\S+)\\s+(-|-|\\S+)\\s+\\[([^]]+)\\]\\s+\"([^\"]+)\"\\s+(\\d+)\\s+(\\d+)\\s+\"([^\"]*)\"\\s+\"([^\"]*)\"$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(line);

        if (matcher.find()) {
            this.ip = matcher.group(1);
            this.dash1 = matcher.group(2);
            this.dash2 = matcher.group(3);
            String timestampStr = matcher.group(4);
            try {
                this.timestamp = LocalDateTime.parse(timestampStr, DATE_TIME_FORMATTER);
            } catch (Exception e) {
                throw new IllegalArgumentException("Неверный формат даты: " + timestampStr, e);
            }
            this.request = matcher.group(5);
            String methodStr = this.request.split("\\s+")[0];
            this.httpMethod = HttpMethod.valueOf(methodStr);
            this.httpCode = Integer.parseInt(matcher.group(6));
            String bytesStr = matcher.group(7);
            this.bytes = bytesStr.isEmpty() ? 0L : Long.parseLong(bytesStr);
            this.referer = matcher.group(8);
            this.userAgent = matcher.group(9);
        } else {
            throw new IllegalArgumentException("Строка не соответствует формату лога: " + line);
        }
    }

    public String getIp() {
        return ip;
    }

    public String getDash1() {
        return dash1;
    }

    public String getDash2() {
        return dash2;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public String getRequest() {
        return request;
    }

    public HttpMethod getHttpMethod() {
        return httpMethod;
    }

    public int getHttpCode() {
        return httpCode;
    }

    public long getBytes() {
        return bytes;
    }

    public String getReferer() {
        return referer;
    }

    public String getUserAgent() {
        return userAgent;
    }

    @Override
    public String toString() {
        return "LogEntry{" +
                "ip='" + ip + '\'' +
                ", dash1='" + dash1 + '\'' +
                ", dash2='" + dash2 + '\'' +
                ", timestamp=" + timestamp +
                ", request='" + request + '\'' +
                ", httpMethod=" + httpMethod +
                ", httpCode=" + httpCode +
                ", bytes=" + bytes +
                ", referer='" + referer + '\'' +
                ", userAgent='" + userAgent + '\'' +
                '}';
    }

    public String extractBotName() {
        String userAgent = this.userAgent;
        if (userAgent == null || userAgent.isEmpty()) {
            return null;
        }
        int start = userAgent.indexOf('(');
        int end = userAgent.indexOf(')');
        if (start == -1 || end == -1 || start >= end) {
            return null;
        }
        String firstBrackets = userAgent.substring(start + 1, end);
        String[] parts = firstBrackets.split(";");
        if (parts.length < 2) {
            return null;
        }
        String fragment = parts[1].trim();
        int slashIndex = fragment.indexOf('/');
        if (slashIndex == -1) {
            return fragment;
        }
        return fragment.substring(0, slashIndex);
    }
    public String getRequestUrl() {
        String[] parts = this.request.split("\\s+");
        if (parts.length < 2) {
            return "";
        }
        return parts[1];
    }
}