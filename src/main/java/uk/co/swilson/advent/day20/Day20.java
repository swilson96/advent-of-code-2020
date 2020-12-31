package uk.co.swilson.advent.day20;

import com.google.common.collect.Lists;
import uk.co.swilson.advent.Solver;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Day20 extends Solver {
    private static final int TILE_SIZE = 8;

    public long solvePartOne(String input) {
        var tiles = Arrays.stream(input.split("\\r?\\n\\r?\\n"))
                .map(Tile::new)
                .collect(Collectors.toSet());

        List<Tile> corners = linkEdges(tiles);

        return corners.stream().mapToLong(t -> t.id).reduce(1L, (a,b) -> a * b);
    }

    private List<Tile> linkEdges(Set<Tile> tiles) {
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
                            edge.match = otherEdge;
                            otherEdge.match = edge;
                            // Since we are reading the signatures clockwise around the respective parent tiles,
                            // if they _match_, then the signs of the tiles are opposite
                            edge.matchIsFlipped = otherEdge.signature == edge.signature;
                            otherEdge.matchIsFlipped = otherEdge.signature == edge.signature;
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
        return corners;
    }

    public static class Tile {
        public final long id;
        // N, E, S, W, going clockwise as stated
        public final Edge[] edgesClockwiseFromTop;
        public final String[][] payload;

        public Tile(long id, Edge[] edgesClockwiseFromTop, String[][] payload) {
            this.id = id;
            this.edgesClockwiseFromTop = edgesClockwiseFromTop;
            this.payload = payload;
        }

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
            payload = lines.stream().skip(2).limit(lines.size() - 3).map(s -> s.substring(1, s.length() - 1).split("")).toArray(String[][]::new);
        }

        public Edge fromReverse(Tile tile, String[] reverseInput) {
            return new Edge(tile, reverseStringArray(reverseInput));
        }

        public Tile flip() {
            return new Tile(id, new Edge[] {
                    edgesClockwiseFromTop[0].flip(),
                    edgesClockwiseFromTop[3].flip(),
                    edgesClockwiseFromTop[2].flip(),
                    edgesClockwiseFromTop[1].flip()
            },
            Arrays.stream(payload).map(Day20::reverseStringArray).toArray(String[][]::new));
        }

        public Tile rotate(int quarterTurns) {
                return new Tile(id, rotateEdges(edgesClockwiseFromTop, quarterTurns), rotatePayload(quarterTurns));
        }

        private Edge[] rotateEdges(Edge[] edges, int turns) {
            // Rotate clockwise
            return new Edge[] { edges[(4 - turns) % 4], edges[(5 - turns) % 4], edges[(6 - turns) % 4], edges[(7 - turns) % 4] };
        }

        private String[][] rotatePayload(int quarterTurns) {
            var ret = payload;
            for (int i = 0; i < quarterTurns; ++i) {
                ret = rotateArrayClockwise(ret);
            }
            return ret;
        }

        public int indexOfEdge(Edge edge) {
            for (int i = 0; i < 4; ++i) {
                if (edgesClockwiseFromTop[i].equals(edge)) {
                    return i;
                }
            }
            return -1;
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
            public final int signature;
            public final int flippedSignature;
            public final Tile tile; // original tile, not necessarily the same sign as this edge

            public Edge match;
            public boolean matchIsFlipped;

            public Edge(Tile tile, String[] input) {
                this.tile = tile;
                var result = 0;
                var flippedResult = 0;
                var flippedRadix = (long) Math.pow(2, input.length - 1);
                var radix = 1;
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

            private Edge(Tile tile, int signature, int flippedSignature) {
                this.tile = tile;
                this.signature = signature;
                this.flippedSignature = flippedSignature;
            }

            public Edge flip() {
                var flipped = new Edge(tile, flippedSignature, signature); // Tile is not flipped: we have to stop somewhere
                flipped.match = match;
                flipped.matchIsFlipped = !matchIsFlipped; // Note inversion
                return flipped;
            }

            @Override
            public int hashCode() {
                return signature;
            }

            @Override
            public boolean equals(Object obj) {
                if (!(obj instanceof Edge)) {
                    return false;
                }
                if (obj == this) {
                    return true;
                }
                var otherTile = (Edge) obj;
                return otherTile.signature == signature;
            }

            public String toString() {
                return String.format("Tile[%d].Edge[%d/%d]", tile.id, signature, flippedSignature);
            }
        }
    }

    private static String[] reverseStringArray(String[] input) {
        var ret = new String[input.length];
        for(int i = 0; i < input.length / 2; i++) {
            ret[i] = input[input.length - i - 1];
            ret[input.length - i - 1] = input[i];
        }
        return ret;
    }

    private static String[][] rotateArrayClockwise(String[][] input) {
        var ret = new String[input.length][input.length];
        for (int x = 0; x < input.length; ++x) {
            for (int y = 0; y < input.length; ++y) {
                ret[x][y] = input[input.length - y - 1][x];
            }
        }
        return ret;
    }

    public long solvePartTwo(String input) {
        var tiles = Arrays.stream(input.split("\\r?\\n\\r?\\n"))
                .map(Tile::new)
                .collect(Collectors.toSet());

        List<Tile> corners = linkEdges(tiles);

        // build grid
        var gridSize = (int) Math.sqrt(tiles.size()); // 26
        var grid = new Tile[gridSize][gridSize];
        var topLeft = corners.get(0);
        for (int i = 0; i < 4; ++i) {
            if (topLeft.edgesClockwiseFromTop[3 - i].match == null && topLeft.edgesClockwiseFromTop[(4 - i) % 4].match == null) {
                grid[0][0] = topLeft.rotate(i);
                break;
            }
        }

        // top row
        for (int y = 1; y < gridSize; ++y) {
            var edgeToMatch = grid[0][y - 1].edgesClockwiseFromTop[1];
            var nextTile = edgeToMatch.matchIsFlipped ? edgeToMatch.match.tile.flip() : edgeToMatch.match.tile;
            var rotations = 3 - nextTile.indexOfEdge(edgeToMatch.matchIsFlipped ? edgeToMatch.match.flip() : edgeToMatch.match);
            grid[0][y] = nextTile.rotate(rotations);

            // check top?
            if (grid[0][y].edgesClockwiseFromTop[0].match != null) {
                throw new RuntimeException("edge along top is not an edge, at (0," + y + ")");
            }
        }

        // check right?
        if (grid[0][gridSize - 1].edgesClockwiseFromTop[1].match != null) {
            throw new RuntimeException("edge along right is not an edge, at (0," + (gridSize - 1) + ")");
        }

        // remaining rows
        for (int x = 1; x < gridSize; ++x) {
            var edgeAbove = grid[x - 1][0].edgesClockwiseFromTop[2];
            var firstTileInRow = edgeAbove.matchIsFlipped ? edgeAbove.match.tile.flip() : edgeAbove.match.tile;
            var rotationsFirst = (4 - firstTileInRow.indexOfEdge(edgeAbove.matchIsFlipped ? edgeAbove.match.flip() : edgeAbove.match)) % 4;
            grid[x][0] = firstTileInRow.rotate(rotationsFirst);

            // check left?
            if (grid[x][0].edgesClockwiseFromTop[3].match != null) {
                throw new RuntimeException("edge along left is not an edge, at (" + x + ",0)");
            }

            for (int y = 1; y < gridSize; ++y) {
                var edgeToMatch = grid[x][y - 1].edgesClockwiseFromTop[1];
                var nextTile = edgeToMatch.matchIsFlipped ? edgeToMatch.match.tile.flip() : edgeToMatch.match.tile;
                var rotations = 3 - nextTile.indexOfEdge(edgeToMatch.matchIsFlipped ? edgeToMatch.match.flip() : edgeToMatch.match);
                grid[x][y] = nextTile.rotate(rotations);

                // check top?
                if (grid[x][y].edgesClockwiseFromTop[0].signature != grid[x-1][y].edgesClockwiseFromTop[2].flippedSignature) {
                    throw new RuntimeException("edge matching left did not match above, at (" + x + "," + y + ")");
                }
            }

            // check right?
            if (grid[x][gridSize - 1].edgesClockwiseFromTop[1].match != null) {
                throw new RuntimeException("edge along right is not an edge, at (" + x + "," + (gridSize - 1) + ")");
            }
        }

        // check the bottom, for completeness
        for (int y = 0; y < gridSize; ++y) {
            if (grid[gridSize - 1][y].edgesClockwiseFromTop[2].match != null) {
                throw new RuntimeException("edge along bottom is not an edge, at (" + (gridSize - 1) + "," + y + ")");
            }
        }

        // copy out the image
        String[][] image = new String[gridSize * TILE_SIZE][gridSize * TILE_SIZE];
        for (int x = 0; x < gridSize; ++x) {
            for (int tileRow = 0; tileRow < TILE_SIZE; ++tileRow) {
                for (int y = 0; y < gridSize; ++y) {
                    System.arraycopy(grid[x][y].payload[tileRow], 0, image[x * TILE_SIZE + tileRow], y * TILE_SIZE, TILE_SIZE);
                }
            }
        }

        String[][] flippedImage = Arrays.stream(image).map(Day20::reverseStringArray).toArray(String[][]::new);

        // look for monsters:
        var monsterCount = 0;
        int rotations = 0;
        while (monsterCount == 0 && rotations < 4) {
            monsterCount = countMonsters(image);
            if (monsterCount == 0) {
                monsterCount = countMonsters(flippedImage);
            }
            image = rotateArrayClockwise(image);
            flippedImage = rotateArrayClockwise(flippedImage);
            ++rotations;
        }

        var totalRoughness = Arrays.stream(image).flatMap(Arrays::stream).filter(s -> s.equals("#")).count();
        var roughnessPerMonster = 15;
        return totalRoughness - roughnessPerMonster * monsterCount;
    }

    public int countMonsters(String[][] image) {
        var count = 0;
        // a                   #
        // b #    ##    ##    ###
        // c  #  #  #  #  #  #
        //   01234567890123456789
        //   s                 e
        for (int rowA = 0; rowA < image.length - 2; ++rowA) {
            for (int ear = 18; ear < image[rowA].length - 1; ++ear) {
                if (image[rowA][ear].equals("#")) {
                    // That looks like the ear of a monster - better check!
                    var b = image[rowA + 1];
                    var c = image[rowA + 2];
                    var s = ear - 18;
                    if (b[s].equals("#") &&
                            b[s+5].equals("#") &&
                            b[s+6].equals("#") &&
                            b[s+11].equals("#") &&
                            b[s+12].equals("#") &&
                            b[s+17].equals("#") &&
                            b[s+18].equals("#") &&
                            b[s+19].equals("#") &&
                            c[s+1].equals("#") &&
                            c[s+4].equals("#") &&
                            c[s+7].equals("#") &&
                            c[s+10].equals("#") &&
                            c[s+13].equals("#") &&
                            c[s+16].equals("#")
                    ) {
                        ++count;
                    }
                }
            }
        }
        return count;
    }
}
