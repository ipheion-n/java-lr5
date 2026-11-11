package tasks;

import java.io.FilterWriter;
import java.io.IOException;
import java.io.Writer;


public class CipherWriter extends FilterWriter {
    static final int MOD = 0xD800;
    private final int key;

    public CipherWriter(Writer out, char keyChar) {
        super(out);
        this.key = keyChar;
    }

    static int encrypt(int c, int key) { return c >= MOD ? c : (c + key) % MOD; }

    @Override
    public void write(int c) throws IOException { super.write(encrypt(c, key)); }

    @Override
    public void write(char[] buf, int off, int len) throws IOException {
        for (int i = off; i < off + len; i++) write(buf[i]);
    }

    @Override
    public void write(String s, int off, int len) throws IOException {
        for (int i = off; i < off + len; i++) write(s.charAt(i));
    }
}