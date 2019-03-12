import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class NeighborDetection {

    public static void main(String[] args) {
        Input input;
        Grid grid;
        try{
            input = new Input(args[0], args[1], false);
        }catch (IOException e){
            input = null;
//            Handle Exception
        }
        grid = new Grid(input.getCellSideQuantity(), input.getSystemSideLength());
        grid.setParticles(input.getParticles());
        Output.printGrid(grid);
        Output.generateOutput(getNeighbors(grid, input.getInteractionRadio(), input.getContornCondition()));
    }

    private static Map<Particle, List<Particle>> getNeighbors(Grid grid, Double interactionRadio, Boolean contornCondition){

        return null;
    }

}
