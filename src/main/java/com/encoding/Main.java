package com.encoding;

import com.encoding.huffman.HuffmanCompressor;
import com.encoding.huffman.HuffmanDecompressor;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        new HuffmanCompressor("file/lorem.txt").compress();
        new HuffmanDecompressor("file/compress.bin", "file/dico.bin").decompress();
    }
}
