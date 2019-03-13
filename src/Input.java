import com.sun.javaws.exceptions.InvalidArgumentException;

import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Input {

    // Defined values
    private final int systemSideLength = 20;
    private final Double interactionRadio = 1.0;


    private Long particlesQuantity;

    private int cellSideQuantity;

    private List<Particle> particles;

    private Boolean contornCondition;

    public Input(String staticFileName, String dinamicFileName, Boolean contornCondition) throws IOException{
        this.contornCondition = contornCondition;
        BufferedReader staticFileReader, dinamicFileReader;
        try{
            // Static file
            staticFileReader = new BufferedReader(new FileReader(staticFileName));
            dinamicFileReader = new BufferedReader(new FileReader(dinamicFileName));
            this.particlesQuantity = Long.valueOf(staticFileReader.readLine());
            this.cellSideQuantity = Integer.valueOf(staticFileReader.readLine());
            this.particles = new ArrayList<>();
            dinamicFileReader.readLine();  //Discard first time notation
            while(staticFileReader.ready()){    //Only time zero for dinamic file
                String[] staticLineValues = staticFileReader.readLine().split(" ");
                String[] dinamicLineValues = dinamicFileReader.readLine().split(" ");
                particles.add(new Particle(
                        Double.valueOf(staticLineValues[0]),
                        staticLineValues[1],
                        Double.valueOf(dinamicLineValues[0]),
                        Double.valueOf(dinamicLineValues[1]),
                        Double.valueOf(dinamicLineValues[2]),
                        Double.valueOf(dinamicLineValues[3])
                ));
            }
            if (particles.size() != particlesQuantity)
                throw new IllegalArgumentException();
            // TODO: Validar L/M > Rc
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

    public int getCellSideQuantity() {
        return cellSideQuantity;
    }

    public int getSystemSideLength() {
        return systemSideLength;
    }

    public Double getInteractionRadio() {
        return interactionRadio;
    }

    public List<Particle> getParticles() {
        return particles;
    }

    public Boolean getContornCondition() {
        return contornCondition;
    }
}
