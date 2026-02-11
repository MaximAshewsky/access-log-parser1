import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamToStringConverter {
    public static String getStringFromStream(Stream<String> stringStream) {
        return stringStream
                .collect(Collectors.joining(" "));
    }
}
