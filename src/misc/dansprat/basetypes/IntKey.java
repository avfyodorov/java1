package misc.dansprat.basetypes;

import java.util.Objects;

public class IntKey implements HashValue {
    Integer val;

    public IntKey(int val){
        this.val=val;
    }
    @Override
    public int getHash() {
        return val;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof IntKey)) return false;
        IntKey intKey = (IntKey) o;
        return val.equals(intKey.val);
    }

    @Override
    public int hashCode() {
        return Objects.hash(val);
    }
}
