import java.util.ArrayList;
import java.util.List;

public class Particle {

    private Long radio;
    private String property;
    private List<State> states = new ArrayList<>();

    public Particle(Long radio, String property, Double x, Double y, Double vx, Double vy) {
        this.radio = radio;
        this.property = property;
        states.add(new State(x, y, vx, vy));
    }

    public Particle(Long radio, String property) {
        this.radio = radio;
        this.property = property;
    }

    public Long getRadio() {
        return radio;
    }

    public Object getProperty() {
        return property;
    }

    public List<State> getStates() {
        return states;
    }

    public void addState(Double x, Double y, Double vx, Double vy){
        states.add(new State(x,y,vx,vy));
    }

    class State{
        private final Double x;
        private final Double y;

        private final Double vx;
        private final Double vy;

        private final Double speedModule;
        private final Double speedAngle;

        public State(Double x, Double y, Double vx, Double vy) {
            this.x = x;
            this.y = y;
            this.vx = vx;
            this.vy = vy;
            this.speedModule = Math.sqrt(Math.pow(vx, 2) + Math.pow(vy, 2));
            this.speedAngle = Math.atan2(vy,vx);
        }

        public Double getX() {
            return x;
        }

        public Double getY() {
            return y;
        }

        public Double getVx() {
            return vx;
        }

        public Double getVy() {
            return vy;
        }

        public Double getSpeedModule() {
            return speedModule;
        }

        public Double getSpeedAngle() {
            return speedAngle;
        }
    }


}
