
public class UserAgent {
    public enum OperatingSystem {
    WINDOWS, MACOS, LINUX, OTHER
}
    public enum Browser {
        EDGE, FIREFOX, CHROME, OPERA, OTHER
    }
    private final OperatingSystem os;
    private final Browser browser;

    public UserAgent(String userAgentString) {
        if (userAgentString == null || userAgentString.isEmpty()) {
            this.os = OperatingSystem.OTHER;
            this.browser = Browser.OTHER;
            return;
        }
        if (userAgentString.contains("Windows")) {
            this.os = OperatingSystem.WINDOWS;
        } else if (userAgentString.contains("Macintosh") || userAgentString.contains("Mac OS")) {
            this.os = OperatingSystem.MACOS;
        } else if (userAgentString.contains("Linux")) {
            this.os = OperatingSystem.LINUX;
        } else {
            this.os = OperatingSystem.OTHER;
        }
        if (userAgentString.contains("Edge/")) {
            this.browser = Browser.EDGE;
        } else if (userAgentString.contains("Firefox/")) {
            this.browser = Browser.FIREFOX;
        } else if (userAgentString.contains("Chrome/")) {
            this.browser = Browser.CHROME;
        } else if (userAgentString.contains("Opera/")) {
            this.browser = Browser.OPERA;
        } else {
            this.browser = Browser.OTHER;
        }

    }

    public OperatingSystem getOs() {
        return os;
    }

    public Browser getBrowser() {
        return browser;
    }
    public boolean isBot() {
        return this.toString().toLowerCase().contains("bot");
    }

    @Override
    public String toString() {
        return "UserAgent{" +
                "os=" + os +
                ", browser=" + browser +
                '}';
    }
}
