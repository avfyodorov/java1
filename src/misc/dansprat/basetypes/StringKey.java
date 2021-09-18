package misc.dansprat.basetypes;

public class StringKey implements HashValue{
    String value;

    public StringKey(String value){
        this.value = value;
    }
    @Override
    public int getHash() {
        long b = 378551;
        long a = 63689;
        long hash =0;
        for(char x:value.toCharArray()){
            hash = DoubleHashTable.unsignedInt(hash*a+x);
            a = DoubleHashTable.unsignedInt(a*b);
        }
        return DoubleHashTable.toInt(hash);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof StringKey)) return false;
        StringKey stringKey = (StringKey) o;
        return value.equals(stringKey.value);
    }

    @Override
    public int hashCode() {
        return 0;
    }
}
