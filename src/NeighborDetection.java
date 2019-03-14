import javafx.util.Pair;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class NeighborDetection {
    // Program Arguments: "./NeighborDetection/resources/sample_input_static.txt" "./NeighborDetection/resources/sample_input_dinamic.txt"
    public static void main(String[] args) {
        Input input;
        Grid grid;
        try{
            input = new Input(args[0], args[1], false);
        }catch (IOException e){
            input = null;
//            Handle Exception
        }

        for (int repetitions = 0 ; repetitions < 5 ; repetitions++ ){
            input = new Input();
            grid = new Grid(input.getCellSideQuantity(), input.getSystemSideLength());
            grid.setParticles(input.getParticles());
            Map<Particle, List<Particle>> result = getNeighbors(grid, grid.getUsedCells(), input.getInteractionRadio(), input.getContornCondition());
            Output.printGrid(grid);
            Output.printParticlesInfo(input.getParticles(), 0);
            Output.printResult(result);
            Output.generateOutput(result);
        }
    }

    /**
     * Returns a map with the neighbors for each particle using the "Cell Index Method".
     *
     * @param grid                  The current grid with all the particles loaded.
     * @param usedCells             A set of pairs of coordenates of the used cells (for optimization)
     * @param interactionRadio      The max distance between two particles to be neighbors.
     * @param contornCondition      True if the contorn condition is on.
     * @return  A Map with a {@link List} of {@link Particle}s for each Particle.
     */
    private static Map<Particle, List<Particle>> getNeighbors(Grid grid, HashSet<Pair<Integer, Integer>> usedCells, Double interactionRadio, Boolean contornCondition){
        Map<Particle, List<Particle>> result = new HashMap<>();
        // Foreach cell with particles
        usedCells.forEach(pair -> {
            int i = pair.getKey(), j = pair.getValue();
            for (Particle current : grid.getCell(i, j).getParticles()){
                List<Particle> currentNeighbors = new ArrayList<>();
                List<Particle> sameCell = new ArrayList<>();

                //get the neighboor added before or a new linked list
                final List<Particle> neighbors = result.getOrDefault(current, new LinkedList<>());

                //Check the four neighbors taking advantage of the simetry.
                if (i != 0)
                    currentNeighbors.addAll(getNeighborParticles(current,
                            grid.getCell(i-1, j), interactionRadio));
                if (i != 0 && j != grid.getSideCellsQuantity() - 1)
                    currentNeighbors.addAll(getNeighborParticles(current,
                            grid.getCell(i-1, j+1), interactionRadio));
                if (j != grid.getSideCellsQuantity() - 1)
                    currentNeighbors.addAll(getNeighborParticles(current,
                            grid.getCell(i, j+1), interactionRadio));
                if (j != grid.getSideCellsQuantity() - 1 && i != grid.getSideCellsQuantity() - 1)
                    currentNeighbors.addAll(getNeighborParticles(current,
                            grid.getCell(i+1, j+1), interactionRadio));

                //check same cell
                sameCell.addAll(getNeighborParticles(current,
                        grid.getCell(i, j), interactionRadio));

/*                if(j == 0 && i == grid.getSideCellsQuantity() - 1 && contornCondition){ // Top Right Corner
                    currentNeighbors.addAll(getNeighborParticles(current,
                            grid.getCell(0, grid.getSideCellsQuantity()-1), interactionRadio));
                }else if(j == 0 && contornCondition){                                   // Superior
                    currentNeighbors.addAll(getNeighborParticles(current,
                            grid.getCell(i, grid.getSideCellsQuantity()-1), interactionRadio));
                }else if(i == grid.getSideCellsQuantity() - 1 && contornCondition){     // Right border
                    currentNeighbors.addAll(getNeighborParticles(current,
                            grid.getCell(0, j), interactionRadio));
                }else if(j == grid.getSideCellsQuantity() - 1 &&
                        i == grid.getSideCellsQuantity() - 1 &&  contornCondition){     // Bottom Right Corner
                    currentNeighbors.addAll(getNeighborParticles(current,
                            grid.getCell(0, 0), interactionRadio));
                }*/

                //add all to the neighbors
                neighbors.addAll(currentNeighbors);
                neighbors.addAll(sameCell);

                //for each neighbors add current to the relation
                for (Particle newRelation : currentNeighbors) {
                    final List<Particle> anotherNeighbors = result.getOrDefault(newRelation, new LinkedList<>());
                    anotherNeighbors.add(current);
                    result.put(newRelation, anotherNeighbors);
                }

                result.put(current, neighbors);
            }
        });
        return result;
    }


    /**
     * This function returns a list of the particles that are near than the interaction radio from the current Particle.
     *
     * @param current The particle looking for neighbors
     * @param cell    The cell under lookup.
     * @param interactionRadio The max length of the distance from the current particle.
     * @return
     */
    private static List<Particle> getNeighborParticles(Particle current, Cell cell, Double interactionRadio){
        return cell.getParticles().stream()
                .parallel()
                .filter(another -> getDistance(current, another) <= interactionRadio)
                .filter(another -> !current.equals(another))
                .collect(Collectors.toList());
    }

    private static Double getDistance(Particle p1, Particle p2){
        double y = Math.abs(p2.getStates().get(0).getY() - p1.getStates().get(0).getY());
        double x = Math.abs(p2.getStates().get(0).getX() - p1.getStates().get(0).getX());
        return Math.hypot(y, x);
    }
}
