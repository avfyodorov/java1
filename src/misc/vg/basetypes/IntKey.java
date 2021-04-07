package misc.vg.basetypes;

import java.util.Objects;

public class IntKey implements HashValue {
    Integer number;

    IntKey(Integer number) {
        this.number = number;
    }

    @Override
    public int getHash() {
        return number;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        IntKey intKey = (IntKey) o;
        return Objects.equals(number, intKey.number);
    }

    @Override
    public int hashCode() {
        return Objects.hash(number);
    }

    @Override
    public String toString() {
        return Integer.toString(number);
    }
}
