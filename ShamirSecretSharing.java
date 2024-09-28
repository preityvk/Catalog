import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;

public class ShamirSecretSharing {

    // Function to decode the y values from the given base
    public static long decodeValue(int base, String value) {
        return Long.parseLong(value, base);
    }

    // Lagrange interpolation to solve for the polynomial
    public static double lagrangeInterpolation(List<long[]> points, double x) {
        double result = 0.0;

        for (int i = 0; i < points.size(); i++) {
            long[] pointI = points.get(i);
            double xi = pointI[0];
            double yi = pointI[1];

            // Calculate the basis polynomial for this point
            double term = yi;
            for (int j = 0; j < points.size(); j++) {
                if (i != j) {
                    long[] pointJ = points.get(j);
                    double xj = pointJ[0];
                    term *= (x - xj) / (xi - xj);
                }
            }
            result += term;
        }

        return result;
    }

    // Function to process a single test case
    public static double solveTestCase(JSONObject testCase) {
        // Extract n and k from the keys
        JSONObject keys = testCase.getJSONObject("keys");
        int n = keys.getInt("n");
        int k = keys.getInt("k");

        // Extract x and y values
        List<long[]> points = new ArrayList<>();
        for (String key : testCase.keySet()) {
            if (key.equals("keys")) continue;

            int x = Integer.parseInt(key);
            JSONObject root = testCase.getJSONObject(key);
            int base = root.getInt("base");
            String value = root.getString("value");

            // Decode y value
            long y = decodeValue(base, value);
            points.add(new long[]{x, y});
        }

        // We only need the first k points to solve the polynomial
        points = points.subList(0, k);

        // Use Lagrange interpolation to find the polynomial
        // The constant term of the polynomial is f(0)
        return lagrangeInterpolation(points, 0);
    }

    public static void main(String[] args) {
        // Sample test case 1
        String jsonStr1 = """
        {
            "keys": {
                "n": 4,
                "k": 3
            },
            "1": {
                "base": "10",
                "value": "4"
            },
            "2": {
                "base": "2",
                "value": "111"
            },
            "3": {
                "base": "10",
                "value": "12"
            },
            "6": {
                "base": "4",
                "value": "213"
            }
        }""";

        // Sample test case 2
        String jsonStr2 = """
        {
            "keys": {
                "n": 9,
                "k": 6
            },
            "1": {
                "base": "10",
                "value": "28735619723837"
            },
            "2": {
                "base": "16",
                "value": "1A228867F0CA"
            },
            "3": {
                "base": "12",
                "value": "32811A4AA0B7B"
            },
            "4": {
                "base": "11",
                "value": "917978721331A"
            },
            "5": {
                "base": "16",
                "value": "1A22886782E1"
            },
            "6": {
                "base": "10",
                "value": "28735619654702"
            },
            "7": {
                "base": "14",
                "value": "71AB5070CC4B"
            },
            "8": {
                "base": "9",
                "value": "122662581541670"
            },
            "9": {
                "base": "8",
                "value": "642121030037605"
            }
        }""";

        // Parse the JSON strings
        JSONObject testCase1 = new JSONObject(jsonStr1);
        JSONObject testCase2 = new JSONObject(jsonStr2);

        // Solve both test cases
        double result1 = solveTestCase(testCase1);
        double result2 = solveTestCase(testCase2);

        // Output results
        System.out.println("Result for test case 1 (c): " + result1);
        System.out.println("Result for test case 2 (c): " + result2);
    }
}