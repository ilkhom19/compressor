import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CompressorTest {

    @Test
    void testCompress() {
        assertEquals("A±5B±3#±5", Compressor.compress("AAAAABBB#####"));
        assertEquals("", Compressor.compress(""));
        assertEquals("A±1", Compressor.compress("A"));
        assertEquals("A±1B±1C±1D±1E±1", Compressor.compress("ABCDE"));
        assertEquals("A±4B±3C±2D±1A±2", Compressor.compress("AAAABBBCCDAA"));
        assertEquals("A±1B±100000C±1", Compressor.compress("A" + "B".repeat(100000) + "C"));

        // Additional Tests
        assertEquals("A±1B±1C±1D±6E±2F±2G±7", Compressor.compress("ABCDDDDDDEEFFGGGGGGG"));
        assertEquals("A±2B±2C±2D±2E±2F±2G±2H±2I±1J±1K±1L±1M±1N±1O±1P±1Q±1R±1S±1T±1U±1V±1W±1X±1Y±1Z±1", Compressor.compress("AABBCCDDEEFFGGHHIJKLMNOPQRSTUVWXYZ"));
        assertEquals("A±1B±2C±3D±4E±5F±6G±7H±8I±9J±9K±11L±12M±13N±13O±12P±13Q±14R±15S±15T±15U±16V±16W±16X±20Y±18Z±29", Compressor.compress("ABBCCCDDDDEEEEEFFFFFFGGGGGGGHHHHHHHHIIIIIIIIIJJJJJJJJJ" +
                "KKKKKKKKKKKLLLLLLLLLLLLMMMMMMMMMMMMMNNNNNNNNNNNNNOOOOOOOOOOOO" +
                "PPPPPPPPPPPPPQQQQQQQQQQQQQQRRRRRRRRRRRRRRRSSSSSSSSSSSSSSSTTTTTTTTTTTTTTTUUUUUUUUUUUUUUUUVVVVVVVVVVVVVVVVWWWWWWWWWWWWWWWWXXX" +
                "XXXXXXXXXXXXXXXXXYYYYYYYYYYYYYYYYYYZZZZZZZZZZZZZZZZZZZZZZZZZZZZZ"));
        assertEquals("A±10", Compressor.compress("A".repeat(10)));
        assertEquals("A±10B±10C±10D±10E±10F±10G±10H±10I±10J±10K±10L±10M±10N±10O±10P±10Q±10R±10S±10T±10U±10V±10W±10X±10Y±10Z±10", Compressor.compress("A".repeat(10) + "B".repeat(10) + "C".repeat(10) +
                "D".repeat(10) + "E".repeat(10) + "F".repeat(10) + "G".repeat(10) + "H".repeat(10) + "I".repeat(10) + "J".repeat(10) +
                "K".repeat(10) + "L".repeat(10) + "M".repeat(10) + "N".repeat(10) + "O".repeat(10) + "P".repeat(10) + "Q".repeat(10) +
                "R".repeat(10) + "S".repeat(10) + "T".repeat(10) + "U".repeat(10) + "V".repeat(10) + "W".repeat(10) + "X".repeat(10) +
                "Y".repeat(10) + "Z".repeat(10)));

        // Test compression with invalid input
        assertThrows(IllegalArgumentException.class, () -> Compressor.compress("A1B2C3D4E5F6G7H8I9J9K11L12M13N13O12P13Q14R15S15T15U16V16W16X20Y18Z29"));
        assertThrows(IllegalArgumentException.class, () -> Compressor.compress("C±3D4E5F6G7H8I9J9K11L12M13N13O12P13Q14R15S15T15U16V16W16X20Y18Z29"));
    }

    @Test
    void testDecompress() {
        assertEquals("AAAAABBB#####", Compressor.decompress("A±5B±3#±5"));
        assertEquals("", Compressor.decompress(""));
        assertEquals("", Compressor.decompress(null));
        assertEquals("A", Compressor.decompress("A±1"));
        assertEquals("ABCDE", Compressor.decompress("A±1B±1C±1D±1E±1"));
        assertEquals("AAAABBBCCDAA", Compressor.decompress("A±4B±3C±2D±1A±2"));

        // Test decompression with invalid input (missing separator)
        assertThrows(IllegalArgumentException.class, () -> Compressor.decompress("A±5B3#±5"));

        // Test decompression with invalid input (missing count)
        assertThrows(IllegalArgumentException.class, () -> Compressor.decompress("A±5B±#"));

        // Test decompression with invalid input (non-digit count)
        assertThrows(IllegalArgumentException.class, () -> Compressor.decompress("A±5B±CD"));

        // Test decompression with invalid input
        assertThrows(IllegalArgumentException.class, () -> Compressor.decompress("±2A±1B±2C±1D±2E±1F±2G±1H±2I±1J±2K±1L±2M±1N±2O±1P±2Q±1R±2S±1T±2U±1V±2W±1X±2Y±1Z"));

        assertEquals("ABCDDDDDEEFGGGGGGG", Compressor.decompress("A±1B±1C±1D±5E±2F±1G±7"));
        assertEquals("A".repeat(10), Compressor.decompress("A±10"));
        assertEquals("A".repeat(10) + "B".repeat(10) + "C".repeat(10) +
                "D".repeat(10) + "E".repeat(10) + "F".repeat(10) + "G".repeat(10) + "H".repeat(10) + "I".repeat(10) + "J".repeat(10) +
                "K".repeat(10) + "L".repeat(10) + "M".repeat(10) + "N".repeat(10) + "O".repeat(10) + "P".repeat(10) + "Q".repeat(10) +
                "R".repeat(10) + "S".repeat(10) + "T".repeat(10) + "U".repeat(10) + "V".repeat(10) + "W".repeat(10) + "X".repeat(10) +
                "Y".repeat(10) + "Z".repeat(10), Compressor.decompress("A±10B±10C±10D±10E±10F±10G±10H±10I±10J±10K±10L±10M±10N±10O±10P±10Q±10R±10S±10T±10U±10V±10W±10X±10Y±10Z±10"));
    }
}
