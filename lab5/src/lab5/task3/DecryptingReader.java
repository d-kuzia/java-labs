package lab5.task3;

import java.io.FilterReader;
import java.io.IOException;
import java.io.Reader;

public class DecryptingReader extends FilterReader {
    private final int key;

    public DecryptingReader(Reader in, char keyChar) {
        super(in);
        this.key = keyChar;
    }

    @Override
    public int read() throws IOException {
        int r = super.read();
        if (r == -1) return -1;
        return (r - key) & 0xFFFF;
    }

    @Override
    public int read(char[] cbuf, int off, int len) throws IOException {
        int read = super.read(cbuf, off, len);
        if (read > 0) {
            for (int i = off; i < off + read; i++) {
                cbuf[i] = (char) ((cbuf[i] - key) & 0xFFFF);
            }
        }
        return read;
    }
}

