package dyve.aoc2021.day.day19;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

public class AOC2019 {
    public static void main(String[] args) throws Exception {
        new AOC2019("/day19");
    }

    record Coord(int x, int y, int z) implements Comparable<Coord> {
        static Coord of(int[] a) { return new Coord(a[0], a[1], a[2]); }
        int[] toArray() { return new int[]{x, y, z}; }
        @Override
        public int compareTo(AOC2019.Coord o) { return Arrays.compare(toArray(), o.toArray()); }

        Coord subtract(Coord other) {
            return new Coord(x - other.x, y - other.y, z - other.z);
        }

        public Coord sum(Coord other) { return new Coord(x + other.x, y + other.y, z + other.z); }

        int getDistance(Coord peer) { return Math.abs(peer.x - x) + Math.abs(peer.y - y) + Math.abs(peer.z - z); }
    }

    static Coord roll(Coord c) { return new Coord(c.x, c.z, -c.y); }

    static Coord turn(Coord c) { return new Coord(-c.y, c.x, c.z); }

    // https://stackoverflow.com/questions/16452383/how-to-get-all-24-rotations-of-a-3-dimensional-array
    static List<Coord> sequence(Coord v) {
        List<Coord> r = new ArrayList<>();
        for (int c = 0; c < 2; c++) {
            for (int s = 0; s < 3; s++) {
                v = roll(v);
                r.add(v);
                for (int i = 0; i < 3; i++) {
                    v = turn(v);
                    r.add(v);
                }
            }
            v = roll(turn(roll(v)));
        }
        return r;
    }

    record Scanner(String name, List<Coord> coords) {

        public Coord findTranslation(Scanner peer) {
            // compute all differences between all points
            Map<Coord, Integer> map = new HashMap<>();
            for (Coord c : coords) {
                for (Coord d : peer.coords) {
                    Coord diff = c.subtract(d);
                    map.merge(diff, 1, Integer::sum);
                }
            }
            // if the same difference occurs between many combinations, the two scanners scan some same beacons
            var r = map.entrySet().stream().filter($ -> $.getValue() >= 12).toList();
            if (r.size() == 0) return null;
            if (r.size() > 1) { System.out.println(r); throw new IllegalStateException("multiple matches"); }
            return r.get(0).getKey();
        }


        public List<Scanner> getAllRotations() {
            List<List<Coord>> ret = new ArrayList<>(24);
            for (int i = 0; i < 24; i++) ret.add(new ArrayList<>(coords.size()));
            for (Coord c : coords) {
                var x = sequence(c);
                for (int i = 0; i < 24; i++) ret.get(i).add(x.get(i));
            }
            return ret.stream().map(s -> new Scanner(name + "rot", s)).toList();
        }

        public void add(Scanner t) {
            for (Coord c : t.coords) if (!coords.contains(c)) coords.add(c);
            //      Collections.sort(coords);
        }

        public void add(Scanner t, Coord trans) {
            for (Coord c : t.coords) {
                c = c.sum(trans);
                if (!coords.contains(c)) coords.add(c);
            }
            //      Collections.sort(coords);
        }
    }

    public AOC2019(String fn) throws Exception {
        var pool = Arrays.stream(Files.readString(Paths.get(AOC2019.class.getResource(fn).toURI())).split("\\r\\n\\r\\n")).map(x -> {
            var s = Arrays.asList(x.split("\\r\\n"));
            return new Scanner(s.get(0),
                    s.subList(1, s.size()).stream()
                            .map(t -> Arrays.stream(t.split(","))
                                    .mapToInt(Integer::parseInt)
                                    .toArray()).map(Coord::of).sorted().collect(Collectors.toList()));
        }).collect(Collectors.toList());

        Scanner base = pool.remove(0);
        var scanners = new ArrayList<Coord>(Collections.singleton(new Coord(0, 0, 0)));
        while (pool.size() > 0) {
            //   System.out.println("pool size:" + pool.size());
            outer: for (Scanner sc : pool) {
                for (var t : sc.getAllRotations()) {
                    Coord trans = base.findTranslation(t);
                    if (trans != null) {
                        scanners.add(trans);
                        base.add(t, trans);
                        pool.remove(sc);
                        break outer;
                    }
                }
            }
        }
        System.out.println("part1:" + base.coords.size());
        int max = 0;
        for (int i = 0; i < scanners.size(); i++) {
            for (int j = 0; j < scanners.size(); j++) {
                int md = scanners.get(i).getDistance(scanners.get(j));
                if (md > max) max = md;
            }
        }
        System.out.println("part2: " + max);
    }
}