package dyve.aoc2021.day.day12;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Cave {

    final String name;

    final boolean small;

    Set<Cave> neighbours = new HashSet<>();

    public Cave(String name) {
        this.name = name;
        this.small = !name.equals(name.toUpperCase());
    }

    public static Cave of(String name){
        return new Cave(name);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cave cave = (Cave) o;
        return name.equals(cave.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return name;
    }
}
