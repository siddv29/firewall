package scribles.illumio.core;

import java.util.ArrayList;
import java.util.List;

public class Range<T> {
    private final List<T> range;


    public Range(T start) {
        this.range = new ArrayList<>();
        this.range.add(start);
    }

    public Range(T start, T end) {
        this(start);
        this.range.add(end);

    }

    public boolean hasEnd(){
        return range.size() == 2;
    }

    public T getStart(){
        return range.get(0);
    }

    public T getEnd(){//throws out of bounds exception if called wrong
        return range.get(1);
    }
}
