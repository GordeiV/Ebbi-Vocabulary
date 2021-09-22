import javax.persistence.criteria.CriteriaBuilder;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[][] matrix = new int[n][n];

        ArrayList<String> outputStrings = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            String line = br.readLine();
            int index = 0;
            for (String number : line.split("")) {
                int k = Integer.parseInt(number);
                matrix[i][index] = k;
                if (k == 1) {
                    outputStrings.add((i + 1) + " " + (index + 1));
                }
                index++;
            }
        }

        for(String s : outputStrings) {
            System.out.println(s);
        }

//        System.out.println(Arrays.deepToString(matrix));
    }
}


