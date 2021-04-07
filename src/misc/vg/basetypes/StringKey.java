package misc.vg.basetypes;

import java.util.Objects;

public class StringKey implements HashValue {
    static final long UINT_MAX = 4_294_967_295L;
    String line;

    StringKey(String line) {
        this.line = line;
    }

    static long unsignedInt(long num) {
        return num % UINT_MAX;
    }

    public static long RSHash(String str) {
        long a = 63689;
        long b = 378551;
        long hash = 0;
        for(int i = 0; i < str.length(); i++) {
            hash = unsignedInt(hash*a + str.charAt(i));
            a = unsignedInt(a*b);
        }
        return hash;
    }

    @Override
    public int getHash() {
        return (int)RSHash(line);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StringKey stringKey = (StringKey) o;
        return Objects.equals(line, stringKey.line);
    }

    @Override
    public int hashCode() {
        return Objects.hash(line);
    }

    @Override
    public String toString() {
        return line;
    }
}
