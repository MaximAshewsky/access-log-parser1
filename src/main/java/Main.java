import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        Stream<String> stream = Stream.of("Привет", "как", "дела");
        String result = StreamToStringConverter.getStringFromStream(stream);
        System.out.println(result);
    }
}
