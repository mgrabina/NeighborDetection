import java.util.List;

public class Grid {
    private Cell[][] cells;
    private Long sideCellsQuantity;
    private Long sideLength;

    public Grid(Long sideCellsQuantity, Long sideLength) {
        this.sideCellsQuantity = sideCellsQuantity;
        this.cells = new Cell[sideCellsQuantity.intValue()][sideCellsQuantity.intValue()];
        for (int i = 0 ; i < sideCellsQuantity.intValue() ; i++)
            for (int j = 0 ; j < sideCellsQuantity.intValue() ; j++)
                this.cells[i][j] = new Cell();
        this.sideLength = sideLength;
    }

    public Cell getCell(int x, int y){
        return cells[x][y];
    }

    public void setCell(int x, int y, Cell cell){
        cells[x][y] = cell;
    }

    public Long getSideCellsQuantity() {
        return sideCellsQuantity;
    }

    public Long getSideLength() {
        return sideLength;
    }

    public void setParticles(List<Particle> particles){
        Double cellSideLength = Double.valueOf(sideLength) / Double.valueOf(sideCellsQuantity);
        for (Particle particle : particles){
            int row = Integer.valueOf(Math.toIntExact(Math.round(particle.getStates().get(0).getY() / cellSideLength)));
            int column = Integer.valueOf(Math.toIntExact(Math.round(particle.getStates().get(0).getX() / cellSideLength)));
            cells[row][column].addParticle(particle);
        }
    }
}
