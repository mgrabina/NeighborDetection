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
                bufferedWriter.write("[ " + entry.getKey().getId() + entry.getValue().stream().map(
                        particle -> " " + particle.getId()) + "]");
            }
        }catch (IOException e){
            // TODO: Handle IO Execption
        }
    }

    public static void printGrid(Grid grid){
        System.out.println("Side Cells Quantity: " + grid.getSideCellsQuantity());
        System.out.println("Side Length: " + grid.getSideLength());
        System.out.println("Cell Side Length: " + grid.getSideLength() / grid.getSideCellsQuantity());
        for (int i = 0 ; i < grid.getSideCellsQuantity() ; i++){
            for (int j = 0 ; j < grid.getSideCellsQuantity() ; j++){
                System.out.print("|"+grid.getCell(i, j).getParticlesQuantity()+"|");
            }
            System.out.println("");
        }
    }
}
