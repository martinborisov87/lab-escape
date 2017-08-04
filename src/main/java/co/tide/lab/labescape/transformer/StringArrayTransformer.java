package co.tide.lab.labescape.transformer;

import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class StringArrayTransformer {

    public static final String NEW_LINE = "\n";

    public char[][] transformToArray(String stringToTransform) {
        String[] splittedString = stringToTransform.split(NEW_LINE);
        int numRows = splittedString.length;
        int numColumns = splittedString[0].length();

        char[][] result = new char[numRows][numColumns];
        int i = 0;
        for (String s : splittedString) {
            if (numColumns != s.length()) {
                throw new IllegalArgumentException("Strings have different lengths");
            }

            result[i++] = s.toCharArray();
        }

        return result;
    }

    public String transformToString(char[][] arrayToTransform) {
        List<String> collect = Arrays.stream(arrayToTransform)
                .map(String::new)
                .collect(Collectors.toList());

        return String.join(NEW_LINE, collect);
    }

}
