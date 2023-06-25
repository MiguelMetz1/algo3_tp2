package Advancer;

public class AntAdvancer extends Advancer {

    public  AntAdvancer() {
        super(1);
    }

    public void advancePosition(){
        this.actualPosition += 1;
    }

}
