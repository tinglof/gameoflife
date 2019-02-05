public class Cell {

    private boolean alive;
    private int nextState = -1;


    public Cell(){
        alive = Math.random() > 0.5 ? true : false;
    }

    public boolean getAlive(){
        return alive;
    }

    public int getNumericStateRepresentation(){
       return alive ? 1 : 0;
    }

    public void updateState(){
        if(nextState == 1) {
            alive = true;
        }

        if(nextState == 0) {
            alive = false;
        }

        if(nextState == -1) {
            System.out.println("Error, state not updated correctly.");
        }
    }

    protected void calculateNextState(int aliveNeighbours) {
    	//System.out.println(aliveNeighbours);
        if (alive) {
            if (aliveNeighbours < 2){
                nextState = 0;
            }
            if (aliveNeighbours == 2 || aliveNeighbours == 3){
            	//System.out.println("2 or 3 triggered");
                nextState = 1;
            }
            if (aliveNeighbours > 3){
            	//System.out.println("Bigger than 3 triggered");
                nextState = 0;
            }
        } else {
            if (aliveNeighbours == 3){
                nextState = 1;
            } else {
                nextState = 0;
            }
        }
        
        //System.out.println("Next state:" + nextState);
    }




}
