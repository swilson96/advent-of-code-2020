package uk.co.swilson.advent.day20;

import com.google.common.collect.Lists;
import uk.co.swilson.advent.Solver;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Day20 extends Solver {
    public long solvePartOne(String input) {
        var tiles = Arrays.stream(input.split("\\r?\\n\\r?\\n"))
                .map(Tile::new)
                .collect(Collectors.toSet());
        var gridSize = (int) Math.sqrt(tiles.size()); // 26

        List<Tile> corners = Lists.newArrayList();
        for (var tile : tiles) {
            var matches = 0;
            for (var edge : tile.edgesClockwiseFromTop) {
                var edgeHasMatch = false;
                for (var otherTile : tiles) {
                    if (otherTile.id == tile.id) {
                        continue;
                    }
                    for (var otherEdge : otherTile.edgesClockwiseFromTop) {
                        if (otherEdge.signature == edge.signature || otherEdge.signature == edge.flippedSignature) {
                            edgeHasMatch = true;
                            break;
                        }
                    }
                    if (edgeHasMatch) {
                        break;
                    }
                }
                if (edgeHasMatch) {
                    ++matches;
                }
            }
            if (matches == 2) {
                corners.add(tile);
            }
        }

        return corners.stream().mapToLong(t -> t.id).reduce(1L, (a,b) -> a * b);
    }

    private static class FixedTile {
        public long id;
        public long[] edgesClockwiseFromTop;

        public FixedTile(long id, long[] edges) {
            this.id = id;
            this.edgesClockwiseFromTop = edges;
        }
    }

    public static class Tile {
        public final long id;
        // N, E, S, W, going clockwise as stated
        public final Edge[] edgesClockwiseFromTop;

        public Tile(String input) {
            var lines = input.lines().collect(Collectors.toList());
            var tileSize = lines.get(1).length();
            id = Long.parseLong(lines.get(0).substring(5, 9));
            edgesClockwiseFromTop = new Edge[] {
                    new Edge(this, lines.get(1).split("")),
                    new Edge(this, lines.stream().skip(1).map(s -> s.substring(tileSize - 1)).toArray(String[]::new)),
                    fromReverse(this, lines.get(tileSize).split("")),
                    fromReverse(this, lines.stream().skip(1).map(s -> s.substring(0, 1)).toArray(String[]::new)),
            };
        }

        public Edge fromReverse(Tile tile, String[] reverseInput) {
            for(int i = 0; i < reverseInput.length / 2; i++){
                String temp = reverseInput[i];
                reverseInput[i] = reverseInput[reverseInput.length - i - 1];
                reverseInput[reverseInput.length - i - 1] = temp;
            }
            return new Edge(tile, reverseInput);
        }

        public FixedTile orientation(int variant) {
            if (variant < 4) {
                return new FixedTile(id, rotateEdges(edgesClockwiseFromTop, variant));
            }
            return new FixedTile(id, rotateAndFlipEdges(edgesClockwiseFromTop, variant - 4));
        }

        private long[] rotateEdges(Edge[] edges, int turns) {
            return new long[] { edges[turns % 4].signature, edges[(turns + 1) % 4].signature, edges[(turns + 2) % 4].signature, edges[(turns + 3) % 4].signature };
        }

        private long[] rotateAndFlipEdges(Edge[] edges, int turns) {
            return new long[] { edges[turns % 4].flippedSignature, edges[(turns + 3) % 4].flippedSignature, edges[(turns + 2) % 4].flippedSignature, edges[(turns + 1) % 4].flippedSignature };
        }

        @Override
        public int hashCode() {
            return (int) id;
        }

        @Override
        public boolean equals(Object obj) {
            if (!(obj instanceof Tile)) {
                return false;
            }
            if (obj == this) {
                return true;
            }
            var otherTile = (Tile) obj;
            return otherTile.id == id;
        }

        public String toString() {
            return String.format("Tile[%d](%d,%d,%d,%d)", id,
                    edgesClockwiseFromTop[0].signature,
                    edgesClockwiseFromTop[1].signature,
                    edgesClockwiseFromTop[2].signature,
                    edgesClockwiseFromTop[3].signature
            );
        }

        public static class Edge {
            public final long signature;
            public final long flippedSignature;
            public final Tile tile;

            public Edge(Tile tile, String[] input) {
                this.tile = tile;
                var result = 0;
                var flippedResult = 0;
                var flippedRadix = (long) Math.pow(2, input.length - 1);
                long radix = 1;
                for (var digit : input) {
                    if (digit.equals("#")) {
                        result += flippedRadix;
                        flippedResult += radix;
                    }
                    radix *= 2;
                    flippedRadix /= 2;
                }
                this.signature = result;
                this.flippedSignature = flippedResult;
            }

            private Edge(Tile tile, long signature, long flippedSignature) {
                this.tile = tile;
                this.signature = signature;
                this.flippedSignature = flippedSignature;
            }

            public Edge flip() {
                return new Edge(tile, flippedSignature, signature);
            }

            public String toString() {
                return String.format("edge[%d/%d]", signature, flippedSignature);
            }
        }
    }

    public long solvePartTwo(String input) {
        return 0;
    }
}
