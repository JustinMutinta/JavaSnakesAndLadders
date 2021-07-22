package GameEngine;


public class Main{

    public static void main(String[] args){
        Board test = new Board();

        int num = test.player1Location;

        System.out.println(num);

        test.boardInit();
        test.printBoard();
        //System.out.println(test.boardLocations[5][5]);
    }
}
