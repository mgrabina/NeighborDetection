import javafx.util.Pair;

import java.util.HashSet;
import java.util.List;
import java.util.Map;

public class Grid {
    private Cell[][] cells;
    private HashSet<Pair<Integer, Integer>> usedCells;
    private int sideCellsQuantity;
    private int sideLength;

    public Grid(int sideCellsQuantity, int sideLength) {
        this.sideCellsQuantity = sideCellsQuantity;
        this.cells = new Cell[sideCellsQuantity][sideCellsQuantity];
        for (int i = 0 ; i < sideCellsQuantity ; i++)
            for (int j = 0 ; j < sideCellsQuantity ; j++)
                this.cells[i][j] = new Cell();
        this.sideLength = sideLength;
    }

    public Cell getCell(int x, int y){
        return cells[x][y];
    }

    public void setCell(int x, int y, Cell cell){
        cells[x][y] = cell;
    }

    public int getSideCellsQuantity() {
        return sideCellsQuantity;
    }

    public int getSideLength() {
        return sideLength;
    }

    public void setParticles(List<Particle> particles){
        usedCells = new HashSet<>();
        Double cellSideLength = Double.valueOf(sideLength) / Double.valueOf(sideCellsQuantity);
        for (Particle particle : particles){
            int row = Integer.valueOf(Math.toIntExact(Math.round(particle.getStates().get(0).getY() / cellSideLength)));
            int column = Integer.valueOf(Math.toIntExact(Math.round(particle.getStates().get(0).getX() / cellSideLength)));
            cells[row][column].addParticle(particle);
            usedCells.add(new Pair(row, column));
        }
    }

    public HashSet<Pair<Integer, Integer>> getUsedCells() {
        return usedCells;
    }
}
