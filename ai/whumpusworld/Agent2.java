package ai.whumpusworld;

public class Agent2 {
    private int direction; //1 right, 2 up, 3 left, 4 down
    private int[] position = new int[2];

    public Agent2() {
        this.direction = 1 ;
        this.position[0] = 0;
        this.position[1] = 0;
    }

    public void turn(){
        this.direction++;
        if(direction == 5){
            direction = 1;
        }
    }

    public boolean move(int direction){
        while (direction != this.direction){
            turn();
        }
        if((direction == 1 && this.position[0] == 3) || (direction == 3 && this.position[0] == 0) ||
                (direction == 2 && this.position[1] == 3) || (direction == 4 && this.position[1] == 0) ){
            return false;
        }
        if (direction==1){
            this.position[0]++;
        }
        else if(direction==2){
            this.position[1]++;
        }
        else if(direction==3){
            this.position[0]--;
        }
        else if(direction==4){
            this.position[1]--;
        }
        return true;
    }

    public boolean[] cellInfo(){
        return new boolean[5];
    }

    public void grab(){

    }

    public boolean shoot(){
        return true;
    }

}
