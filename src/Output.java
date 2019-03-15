import sun.security.krb5.internal.PAData;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Output {
    private final static String FILENAME = "output.txt";
    private final static String FILENAME2 = "positions.xyz";

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

    public static void generatePositionOutput(List<Particle> particles){
        if (particles == null) return; //TODO: Throw exception
        try{
            FileWriter fileWriter = new FileWriter(FILENAME2);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write(Integer.valueOf(particles.size()).toString());
            bufferedWriter.newLine();
            bufferedWriter.newLine();
            for (int i = 0 ; i < particles.size() ; i++){
                bufferedWriter.write(particles.get(i).getId() + " " + particles.get(i).getStates().get(0).getX()
                        + " " + particles.get(i).getStates().get(0).getY() + " "
                        + " " + particles.get(i).getRadio() + " ");
                if (i < particles.size() - 1)
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

    public static void generatePositionOutput(Map<Particle,List<Particle>> result, Particle selectedParticle) {
        Set<Particle> particles = result.keySet();
        if (particles == null) return; //TODO: Throw exception
        try{
            FileWriter fileWriter = new FileWriter(FILENAME2);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write(Integer.valueOf(particles.size()).toString());
            bufferedWriter.newLine();

            printToFile(bufferedWriter, selectedParticle,255,0,0);
            for (Particle particle: result.get(selectedParticle)){
                printToFile(bufferedWriter,particle,255,255,255);
                particles.remove(particle);
            }
            particles.remove(selectedParticle);
            for (Particle particle: particles){
                printToFile(bufferedWriter,particle,0,255,255);
            }
            bufferedWriter.flush();
            bufferedWriter.close();
            fileWriter.close();
        }catch (IOException e){
            // TODO: Handle IO Execption
        }
    }

    public static void printToFile(BufferedWriter bufferedWriter, Particle particle, Integer r,Integer g, Integer b) throws IOException {
        bufferedWriter.newLine();
        String print = particle.getId() + " " + particle.getStates().get(0).getX()
                + " " + particle.getStates().get(0).getY()
                + " " + particle.getRadio() + " " + r.toString() + " " + g.toString() + " " + b.toString() + " ";
        bufferedWriter.write(print);
    }
}
