package GameEngine;


public class Main{

    public static void main(String[] args){
        Board test = new Board();



       // test.boardInit2();
        test.boardInit();
       // test.printBoard2();
        test.movePlayer1();
        test.movePlayer1();
        test.movePlayer1();
        test.movePlayer1();
        test.movePlayer1();
        test.playerLocation(test.player1Char, test.player1Location);
        test.printBoard();
        System.out.println("Player location is " + test.player1Location);



    }
}
