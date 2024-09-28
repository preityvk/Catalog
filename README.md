# Catalog
Here is a description for the provided code in the form of a README file:

---

# Shamir's Secret Sharing Scheme

This Java project implements Shamir's Secret Sharing Scheme using Lagrange interpolation to reconstruct a secret (constant term) from provided shares. The goal of the program is to reconstruct the secret from a set of encoded points, where each point is in a different numeric base.

## Overview

Shamir's Secret Sharing is a cryptographic method used to split a secret into multiple shares such that only a subset of the shares is required to reconstruct the original secret. This implementation reads input data in JSON format, where each share has a corresponding x-coordinate and a y-coordinate. The y-coordinate (the share) is encoded in different numeric bases (e.g., binary, hexadecimal, decimal).

The program takes these shares, decodes the y-values, and uses **Lagrange interpolation** to reconstruct the polynomial that was used to generate the shares. The constant term of this polynomial is the original secret.

## Features
- **Lagrange Interpolation**: This mathematical method is used to reconstruct the polynomial given the shares.
- **Multiple Number Bases**: Supports decoding numbers from different numeric bases (e.g., binary, decimal, hexadecimal).
- **JSON Input**: Accepts input data in JSON format to allow flexible and structured input for the test cases.
  
## Key Components

### `decodeValue(int base, String value)`
This function decodes a given string `value` from a numeric base (e.g., binary, hexadecimal) and returns the corresponding long integer value.

- **Parameters**:
  - `base`: The numeric base (e.g., 2 for binary, 10 for decimal, etc.).
  - `value`: The string representation of the number in the given base.
- **Returns**: Decoded `long` integer value.

### `lagrangeInterpolation(List<long[]> points, double x)`
This function performs Lagrange interpolation on the provided points to solve for the value of the polynomial at `x = 0`, which corresponds to the constant term of the polynomial.

- **Parameters**:
  - `points`: A list of `(x, y)` points (long arrays).
  - `x`: The value at which to evaluate the polynomial (in this case, `x = 0` for the constant term).
- **Returns**: The interpolated value of the polynomial at `x = 0`.

### `solveTestCase(JSONObject testCase)`
This function processes a single test case by extracting the relevant points, decoding the y-values, and applying Lagrange interpolation to find the original secret.

- **Parameters**:
  - `testCase`: A JSON object containing the test case data, including the x-coordinates, y-values in different bases, and the number of shares (`n`) and threshold (`k`).
- **Returns**: The reconstructed secret (constant term of the polynomial).

### `main(String[] args)`
This is the entry point of the program. It defines two sample test cases in JSON format, parses them, and computes the secrets for each test case using the functions above.

- **Sample Test Case 1**:
    - Uses 4 points but only 3 are required to reconstruct the secret.
    - X-values are 1, 2, 3, 6 with y-values in different bases.
- **Sample Test Case 2**:
    - Uses 9 points with 6 required to reconstruct the secret.
    - X-values are 1 to 9 with y-values in different numeric bases.

## Sample JSON Input Format

```json
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
}
```
- `"keys"`: Defines the total number of shares (`n`) and the minimum required to reconstruct the secret (`k`).
- Other keys represent x-coordinates, with their associated y-values stored in various numeric bases.

## How to Run

1. Install Java Development Kit (JDK) if not installed.
2. Save the provided code in a file, for example, `ShamirSecretSharing.java`.
3. Compile the Java code using:
   ```bash
   javac ShamirSecretSharing.java
   ```
4. Run the program using:
   ```bash
   java ShamirSecretSharing
   ```

## Example Output

```
Result for test case 1 (c): 6.0
Result for test case 2 (c): 28735619723833.0
```

## License

This project is open-source and can be freely used and modified under the terms of the MIT license.

---

Feel free to adapt or add more specific sections based on your requirements!
