public class CompressorDemo {
    public static void main(String[] args) {
        String input = "A$$@S!NNNssss";
        String compressed = Compressor.compress(input);
        String decompressed = Compressor.decompress(compressed);
        System.out.println("Input: " + input);
        System.out.println("Compressed: " + compressed);
        System.out.println("Decompressed: " + decompressed);
    }
}
