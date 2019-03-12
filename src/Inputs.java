import com.sun.javaws.exceptions.InvalidArgumentException;

import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Inputs {

    private Long particlesQuantity;

    private Long cellSideQuantity;

    private final Long systemSideLength = Long.valueOf(20);

    private final Double interactionRadio = 0.25;

    private List<Particle> particles;

    public Inputs(String staticFileName, String dinamicFileName) throws IOException{
        BufferedReader staticFileReader, dinamicFileReader;
        try{
            // Static file
            staticFileReader = new BufferedReader(new FileReader(staticFileName));
            dinamicFileReader = new BufferedReader(new FileReader(dinamicFileName));
            this.particlesQuantity = Long.valueOf(staticFileReader.readLine());
            this.cellSideQuantity = Long.valueOf(staticFileReader.readLine());
            this.particles = new ArrayList<>();
            dinamicFileReader.readLine();  //Discard first time notation
            while(staticFileReader.ready()){    //Only time zero for dinamic file
                String[] staticLineValues = staticFileReader.readLine().split(" ");
                String[] dinamicLineValues = staticFileReader.readLine().split(" ");
                particles.add(new Particle(
                        Long.valueOf(staticLineValues[0]),
                        staticLineValues[1],
                        Double.valueOf(dinamicLineValues[0]),
                        Double.valueOf(dinamicLineValues[1]),
                        Double.valueOf(dinamicLineValues[2]),
                        Double.valueOf(dinamicLineValues[3])
                ));
            }
            if (particles.size() != particlesQuantity)
                throw new IllegalArgumentException();
//            TODO: More than one state per particle
//            while(dinamicFileReader.ready()){
//                 dinamicFileReader.readLine();  //Discard time notation
//                 particles.add(new Particle())
//            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public Long getParticlesQuantity() {
        return particlesQuantity;
    }

    public Long getCellSideQuantity() {
        return cellSideQuantity;
    }

    public Long getSystemSideLength() {
        return systemSideLength;
    }

    public Double getInteractionRadio() {
        return interactionRadio;
    }

    public List<Particle> getParticles() {
        return particles;
    }
}
