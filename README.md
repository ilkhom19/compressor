# String Compressor

This Java program provides a basic implementation of a compression algorithm that compresses strings by storing repeated characters along with the number of repetitions. The compressed format includes the character followed by "±" and the count of repetitions.

## Usage

The `Compressor` class provides two main methods:

### Compress

```java
public static String compress(String input)
```

This method takes an input string and returns its compressed form. The input string should consist of alphabetic characters only; digits and the separator "±" are not allowed.

Example:
```java
String compressed = Compressor.compress("AAAAABBB#####");
```

### Decompress

```java
public static String decompress(String input)
```

This method takes a compressed string and returns the original decompressed string.

Example:
```java
String decompressed = Compressor.decompress("A±5B±3#±5");
```

## Error Handling

The compression and decompression methods include error handling to ensure the input adheres to the expected format. If an invalid character, digit, or format is detected, an `IllegalArgumentException` is thrown with a descriptive error message.

## Examples

```java
String original = "ABCDE";
String compressed = Compressor.compress(original);
String decompressed = Compressor.decompress(compressed);

System.out.println("Original: " + original);
System.out.println("Compressed: " + compressed);
System.out.println("Decompressed: " + decompressed);
```

## Unit Tests

Unit tests are recommended to validate the correctness of the compression and decompression methods. Sample unit tests are provided in the `CompressorTest` class.

```java
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CompressorTest {

    @Test
    void testCompress() {
        // tests
    }

    @Test
    void testDecompress() {
        // tests
    }
}
