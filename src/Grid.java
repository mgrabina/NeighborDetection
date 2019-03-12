import java.util.List;

public class Grid {
    private Cell[][] cells;
    private Long sideCellsQuantity;

    public Grid(Long sideCellsQuantity) {
        this.sideCellsQuantity = sideCellsQuantity;
        this.cells = new Cell[sideCellsQuantity.intValue()][sideCellsQuantity.intValue()];
    }

    public Cell getCell(int x, int y){
        return cells[x][y];
    }

    public void setCell(int x, int y, Cell cell){
        cells[x][y] = cell;
    }

    public void setParticles(List<Particle> particles){
        for (Particle particle : particles){
            
        }
    }
}
