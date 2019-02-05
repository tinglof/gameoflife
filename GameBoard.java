public class GameBoard {

    private final int BOARD_HEIGHT;
    private final int BOARD_WIDTH;
    private Cell [][] board;
    private int roundCount = 1;

    public GameBoard(int height, int width) {
        this.BOARD_HEIGHT = height;
        this.BOARD_WIDTH = width;
        board = new Cell[BOARD_HEIGHT][BOARD_WIDTH];
    }


    private int checkNeighbours(int y, int x){
            int aliveCount = 0;

            for (int vertical = y - 1; vertical <= y + 1; vertical++){
                //If we are before the vertical start of the board, get into the board
                if (vertical < 0){
                    continue;
                }

                //If we are at the vertical end of the board, break loop
                if(vertical >= BOARD_HEIGHT) {
                    break;
                }

                for (int horizontal = x - 1; horizontal <= x + 1; horizontal++){

                    if (horizontal < 0){
                        continue;
                    }

                    if (horizontal >= BOARD_WIDTH){
                        break;
                    }

                    if (horizontal == x && vertical == y){
                        continue;
                    }

                    //System.out.println("H:" + (vertical) + " W:" + (horizontal) + " ADDED: " + board[vertical][horizontal].getNumericStateRepresentation());
                    aliveCount += board[vertical][horizontal].getNumericStateRepresentation();
                }
            }
            return aliveCount;
    }

    private void calculateNextRound(){

        for(int i=0; i < BOARD_HEIGHT; i++){
            for (int j = 0; j < BOARD_WIDTH; j++){

                int aliveNeighbours = checkNeighbours(i,j);
                //System.out.println("H:" + (i ) + " W:" + (j) + " A:" + aliveNeighbours);
                board[i][j].calculateNextState(aliveNeighbours);
            }
        }
    }

    private void updateState(){
            for (int i = 0; i < BOARD_HEIGHT; i++){
                for (int j = 0; j < BOARD_WIDTH; j++){
                    board[i][j].updateState();
                }
            }
    }

    private void printBoard(){
      System.out.print("#### THIS IS ROUND " + roundCount + " ######");

       for (int i = 0; i < BOARD_HEIGHT; i++){
           System.out.println();
           for (int j = 0; j <BOARD_WIDTH; j++){
               if(board[i][j].getAlive()){
                   System.out.print("O");
               }
               else {
                   System.out.print("X");
               }
               System.out.print("|");
           }
       }

        System.out.println();

    }

    private void initiateBoard(){
        for (int i = 0; i < BOARD_HEIGHT; i++){
            for (int j = 0; j < BOARD_WIDTH; j++){
                board[i][j] = new Cell();
            }
        }

    }

    public void run(){
        //code for first round
        initiateBoard();

        while(true){
            printBoard();
            calculateNextRound();
            updateState();
            roundCount = roundCount + 1;
            try {
                Thread.sleep(5000); // To not spam console during printboard : ) 5 sek per round
            }catch (Exception e) {
                System.err.println(e);
            }

        }
    }

    public static void main(String[] args) {

        new GameBoard(5,5).run();

    }




    }
