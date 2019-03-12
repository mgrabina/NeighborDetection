import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class Output {
    private final static String FILENAME = "output.txt";

    public static void generateOutput(Map<Particle, List<Particle>> neighbors){
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
}
