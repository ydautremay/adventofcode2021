package dyve.aoc2021.day.day15;

import dyve.aoc2021.day.Part;
import dyve.aoc2021.dijkstra.Dijkstra;
import dyve.aoc2021.dijkstra.Graph;
import dyve.aoc2021.dijkstra.Node;
import dyve.aoc2021.input.InputReader;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Part1 extends Part {

    public static void main(String[] args) throws Exception {
        new Part1().subMain(153);
    }

    Map<Point, Integer> map = new HashMap<>();
    Map<Point, Node> nodes = new HashMap<>();

    @Override
    public Object execute(InputReader inputReader) {
        List<String> entries = inputReader.stream().toList();
        Graph graph = new Graph();
        for(int y = 0; y < entries.size(); y++){
            for(int x = 0; x < entries.size(); x++){
                Point point = Point.of(x, y);
                Node node = new Node(point.toString());
                graph.addNode(node);
                map.put(point, Integer.valueOf("" + entries.get(y).charAt(x)));
                nodes.put(point, node);
            }
        }

        Node entree = new Node("entree");
        entree.addDestination(nodes.get(Point.of(0, 1)), map.get(Point.of(0, 1)));
        entree.addDestination(nodes.get(Point.of(1, 0)), map.get(Point.of(1, 0)));


        for (Map.Entry<Point, Integer> e : map.entrySet()) {
            Point p = e.getKey();
            int i = e.getValue();
            Node n = nodes.get(p);
            if(p.equals(Point.of(0, 0))){
                continue;
            }
            if (p.x > 0) {
                n.addDestination(nodes.get(p.left()), map.get(p.left()));
            }
            if(p.x < entries.size() - 1){
                n.addDestination(nodes.get(p.right()), map.get(p.right()));
            }
            if (p.y > 0) {
                n.addDestination(nodes.get(p.below()), map.get(p.below()));
            }
            if(p.y < entries.size() - 1){
                n.addDestination(nodes.get(p.above()), map.get(p.above()));
            }
        }

        Dijkstra.calculateShortestPathFromSource(graph, entree);
        Node sortie = nodes.get(Point.of(entries.size() - 1, entries.size() - 1));
        return sortie.getDistance();
    }
}