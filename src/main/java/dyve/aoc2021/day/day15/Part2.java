package dyve.aoc2021.day.day15;

import dyve.aoc2021.day.Part;
import dyve.aoc2021.dijkstra.Dijkstra;
import dyve.aoc2021.dijkstra.Graph;
import dyve.aoc2021.dijkstra.Node;
import dyve.aoc2021.input.InputReader;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Part2 extends Part {

    public static void main(String[] args) throws Exception {
        new Part2().subMain(15);
    }

    Map<Point, Integer> map = new HashMap<>();
    Map<Point, Node> nodes = new HashMap<>();

    @Override
    public Object execute(InputReader inputReader) {
        List<String> entries = inputReader.stream().toList();
        Graph graph = new Graph();
        int size = entries.size();
        for(int y = 0; y < size; y++){
            for(int x = 0; x < size; x++){
                int value = Integer.parseInt("" + entries.get(y).charAt(x));

                for(int i = 0; i < 5; i++){
                    for(int j = 0; j < 5; j++) {
                        Point currPoint = Point.of(x + i * size, y + j * size);
                        int currValue = (value + i + j) > 9 ? (value + i + j) % 10 + 1 : value + i + j;
                        map.put(currPoint, currValue);
                        Node node = new Node(currPoint.toString());
                        graph.addNode(node);
                        nodes.put(currPoint, node);
                    }
                }
            }
        }

        Node entree = new Node("entree");
        entree.addDestination(nodes.get(Point.of(0, 1)), map.get(Point.of(0, 1)));
        entree.addDestination(nodes.get(Point.of(1, 0)), map.get(Point.of(1, 0)));


        for (Map.Entry<Point, Integer> e : map.entrySet()) {
            Point p = e.getKey();
            Node n = nodes.get(p);
            if(p.equals(Point.of(0, 0))){
                continue;
            }
            if (p.x > 0) {
                n.addDestination(nodes.get(p.left()), map.get(p.left()));
            }
            if(p.x < size * 5 - 1){
                n.addDestination(nodes.get(p.right()), map.get(p.right()));
            }
            if (p.y > 0) {
                n.addDestination(nodes.get(p.below()), map.get(p.below()));
            }
            if(p.y < size * 5 - 1){
                n.addDestination(nodes.get(p.above()), map.get(p.above()));
            }
        }

        Dijkstra.calculateShortestPathFromSource(graph, entree);
        Node sortie = nodes.get(Point.of(size * 5 - 1, size * 5 - 1));
        return sortie.getDistance();
    }
}