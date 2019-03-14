import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class Output {
    private final static String FILENAME = "output.txt";

    public static void generateOutput(Map<Particle, List<Particle>> neighbors){
        if (neighbors == null) return; //TODO: Throw exception
        try{
            FileWriter fileWriter = new FileWriter(FILENAME);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            for(Map.Entry<Particle, List<Particle>> entry : neighbors.entrySet()){
                bufferedWriter.write("[ " + entry.getKey().getId() + "  ");
                entry.getValue().stream().forEach(particle -> {
                    try {
                        bufferedWriter.write(particle.getId() + " ");
                    }catch (IOException e){
                        // TODO: Handle IO Execption
                    }
                });
                bufferedWriter.write("]");
                bufferedWriter.newLine();
            }
            bufferedWriter.flush();
            bufferedWriter.close();
            fileWriter.close();
        }catch (IOException e){
            // TODO: Handle IO Execption
        }
    }

    public static void printGrid(Grid grid){
        System.out.println("Side Cells Quantity: " + grid.getSideCellsQuantity());
        System.out.println("Side Length: " + grid.getSideLength());
        System.out.println("Cell Side Length: " + Double.valueOf(grid.getSideLength()) / Double.valueOf(grid.getSideCellsQuantity()));
        for (int i = 0 ; i < grid.getSideCellsQuantity() ; i++){
            for (int j = 0 ; j < grid.getSideCellsQuantity() ; j++){
                System.out.print("|"+String.format("%02d", grid.getCell(i, j).getParticlesQuantity())+"|");
            }
            System.out.println("");
        }
    }

    public static void printResult(Map<Particle, List<Particle>> result){
        System.out.println("Result: ");
        result.forEach((particle, particles) -> {
            System.out.print("["+particle.getId()+" ");
            particles.forEach(neighbor -> System.out.print(neighbor.getId() + " "));
            System.out.println("]");
        });
    }

    public static void printParticlesInfo(List<Particle> particles, int state){
        particles.forEach(particle -> {
            System.out.println("ID: " + particle.getId() + " | Radio: " + particle.getRadio() + " | Location: (" +
                    particle.getStates().get(state).getX() + "," + particle.getStates().get(state).getY() + ")");
        });
    }
}
