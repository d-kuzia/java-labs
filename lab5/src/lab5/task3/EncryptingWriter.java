package lab5.task3;

import java.io.FilterWriter;
import java.io.IOException;
import java.io.Writer;

public class EncryptingWriter extends FilterWriter {
    private final int key;

    public EncryptingWriter(Writer out, char keyChar) {
        super(out);
        this.key = keyChar;
    }

    @Override
    public void write(int c) throws IOException {
        int enc = (c + key) & 0xFFFF;
        super.write(enc);
    }

    @Override
    public void write(char[] cbuf, int off, int len) throws IOException {
        for (int i = off; i < off + len; i++) {
            cbuf[i] = (char) ((cbuf[i] + key) & 0xFFFF);
        }
        super.write(cbuf, off, len);
    }

    @Override
    public void write(String str, int off, int len) throws IOException {
        char[] arr = str.substring(off, off + len).toCharArray();
        write(arr, 0, arr.length);
    }
}

