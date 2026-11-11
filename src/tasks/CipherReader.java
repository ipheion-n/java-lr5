package tasks;

import java.io.FilterReader;
import java.io.IOException;
import java.io.Reader;

/** Дешифрування: код кожного символу зменшується на код ключового символу (обернена операція). */
public class CipherReader extends FilterReader {
    private final int key;

    public CipherReader(Reader in, char keyChar) {
        super(in);
        this.key = keyChar;
    }

    static int decrypt(int c, int key) {
        return c >= CipherWriter.MOD ? c : ((c - key) % CipherWriter.MOD + CipherWriter.MOD) % CipherWriter.MOD;
    }

    @Override
    public int read() throws IOException {
        int c = super.read();
        return c == -1 ? -1 : decrypt(c, key);
    }

    @Override
    public int read(char[] buf, int off, int len) throws IOException {
        int n = super.read(buf, off, len);
        for (int i = off; i < off + Math.max(n, 0); i++) buf[i] = (char) decrypt(buf[i], key);
        return n;
    }
}