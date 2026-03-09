package Menus;

public class timer {
public double startTime=0;
public boolean timeDone=false;
    public boolean wait(double time, double delta) {
        startTime+=delta;
        if (startTime>=time) {
            timeDone=true;
        }
        return timeDone;
    }
}
