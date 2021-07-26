package GameEngine;

import java.util.Random;
import java.util.Scanner;

public class Board {
    //locations of user and players.
    //players will be computer
    //also, chars will be how players and computer are represented
    public int player1Location = 0;
    public char player1Char = '1';
    public int player2Location = 0;
    public char player2Char = '2';
    public int player3Location = 0;
    public char player3Char = '3';
    public int player4Location = 0;
    public char player4Char = '4';

    //representation of Snakes and Ladders
    public char snake = 'S';
    public char ladder = 'L';

    //set up Array for board locations.
    //Also a visual of player/computer locations
   // public String[][] boardLocations = new String[10][10];
    //above is for finding locations on board in relation to array. Keeping because it worked too nicely


    //initialize the board and set all locations to '-'
    public String[][] boardLocations2 = new String[10][10];

    public void boardInit2(){
        int level = 1;
        int something = 10;
        for(int i = 0; i < 10; i++){
            for(int j = 0; j < 10; j++){
                if((i+1)%2==0){
                    boardLocations2[i][j] = something + " and " + i + ", " +j;
                }else{
                    boardLocations2[i][j] = level + " and " + i + ", " +j;
                }
                //boardLocations2[i][j] =  " " + level;
                level++;
                something--;
            }
            something += 20;
        }
    }

    public void printBoard2(){
        System.out.println("\t1\t2\t3\t4\t5\t6\t7\t8\t9\t10");
        for(int i = 9; i >= 0; i--){
            System.out.print(i+1 + " ");
            for(int j = 0; j < 10; j++){
                System.out.print("\t" + boardLocations2[i][j] + "  ");
            }
            System.out.println();
        }
        // this is where the print player location function will go.
    }

    //moving this one down here to keep the code clean. The above one for location finding.
    public char[][] boardLocations = new char[10][10];

    public void boardInit(){
        for(int i = 0; i < 10; i++){
            for(int j = 0; j < 10; j++){
                boardLocations[i][j] = '-';
            }
        }

        boardLocations[0][0] = ladder; boardLocations[0][3] = ladder; boardLocations[0][8] = ladder;
        boardLocations[2][0] = ladder; boardLocations[2][7] = ladder; boardLocations[5][9] = ladder;
        boardLocations[7][9] = ladder; boardLocations[7][0] = ladder;

        boardLocations[1][3] = snake; boardLocations[5][6] = snake; boardLocations[6][3] = snake;
        boardLocations[6][1] = snake; boardLocations[8][6] = snake; boardLocations[9][7] = snake;
        boardLocations[9][5] = snake; boardLocations[9][2] = snake;
    }



    //function to print off the board.
    //will be used a lot.
    public void printBoard(){
        System.out.println("\t1\t2\t3\t4\t5\t6\t7\t8\t9\t10");
        for(int i = 9; i >= 0; i--){
            System.out.print(i+1 + " ");
            for(int j = 0; j < 10; j++){
                System.out.print("\t" + boardLocations[i][j] + "  ");
            }
            System.out.println();
        }
        // this is where the print player location function will go.
    }

    //boolean to know if a movement lands on a snake or ladder
    //this function will be used in another function to determine if action needs to be taken
    //changed it from boolean to int because of 3 possible outcomes.
    //1 is for ladders, 2 is for snakes, 3 is to do nothing
    public static int snakeOrLadder(int location){
        if(location == 1 || location == 4 || location == 9 || location == 21 ||
            location == 28 || location == 51 || location == 71 || location == 80){
            return 1;
        }else if(location == 17 || location == 54 || location == 64 || location == 62 ||
                    location == 87 || location == 93 || location == 95 || location == 98){
            return 2;
        }else{
            return 3;
        }
    }

    //function for when the player lands on a ladder and needs to move up
    public static int moveLadder(int location){
        switch(location){
            case 1:
                location = 38;
                return location;
            case 4:
                location = 14;
                return location;
            case 9:
                location = 31;
                return location;
            case 21:
                location = 42;
                return location;
            case 28:
                location = 84;
                return location;
            case 51:
                location = 67;
                return location;
            case 71:
                location = 91;
                return location;
            case 80:
                location = 99;
                return location;
        }
        return location;
    }

    //function for when the player lands on a snake and needs to move down
    //for reasons I cannot think of, I made these seperate and not the same
    public static int moveSnake(int location){
        switch(location){
            case 17:
                location = 7;
                return location;
            case 54:
                location = 34;
                return location;
            case 64:
                location = 60;
                return location;
            case 62:
                location = 19;
                return location;
            case 87:
                location = 24;
                return location;
            case 93:
                location = 95;
                return location;
            case 95:
                location = 75;
                return location;
            case 98:
                location = 79;
                return location;
        }
        return location;
    }

    //function to do all the math of where a user lands
    public static void verifyLocation(char user, int location){
        switch(location){
            case 1:
                System.out.println("Player " + user + " landed on a ladder.");
                System.out.println("Player " + user + " moved up to location " + moveLadder(location));
                //need to find a way to update player/computer
            case 2:
                System.out.println("Player " + user + " landed on a snake.");
                System.out.println("Player " + user + " moved up to location " + moveSnake(location));
                //need to find a way to update player/computer
            case 3:
                //do nothing, because 3 is neither snake nor ladder
        }
    }

    //dice roll
    public static int diceRoll(){
        Random dice = new Random();
        return dice.nextInt(6) + 1;
    }

    public void movePlayer1(){player1Location += diceRoll();}
    public void movePlayer2(){player2Location += diceRoll();}
    public void movePlayer3(){player3Location += diceRoll();}
    public void movePlayer4(){player4Location += diceRoll();}

    public void playerLocation(char player, int location){
        int x = location / 10;
        int y = location % 10;

        if((x % 2 == 0) && y == 0){ //this is for 20, 40, 60, 80, 100
            x -= 1;
            boardLocations[x][y] = player;
        }else if((x % 2 != 0) && y == 0){ // this is for 10, 30, 50, 70
            x -= 1;
            y = 9;
            boardLocations[x][y] = player;
        }else if(x % 2 == 0){ // Everyline that goes left to right
            y -= 1;
            boardLocations[x][y] = player;
        }else{ //Everything else that goes right to left. ie lines that start at 20 and end at 11
            y = 10 - y;
            boardLocations[x][y] = player;
        }

        //boardLocations[x][y] = player;
    }


    public boolean isWinner(){
        if(player1Location >= 100 || player2Location >= 100 || player3Location >= 100 || player4Location >= 100){
            return false;
        }else{
            return true;
        }
    }

    public void runGame(){
        boardInit();
        printBoard();
        Scanner sc = new Scanner(System.in);

        while(isWinner()){
            //printBoard();
            System.out.println("Type roll to roll your dice...");
            String roll = sc.nextLine();

            if(roll.equals("roll")){
                movePlayer1();
                if(snakeOrLadder(player1Location) == 1){
                    player1Location = moveLadder(player1Location);
                }else if(snakeOrLadder(player1Location) == 2){
                    player2Location = moveSnake(player1Location);
                }else{

                }
            }else{
                System.out.println("Enter a valid input");
            }

            playerLocation(player1Char, player1Location);
            printBoard();
            System.out.println("Current Location is " + player1Location);
        }
    }
}
