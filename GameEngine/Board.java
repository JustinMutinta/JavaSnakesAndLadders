package GameEngine;

import java.util.Random; //used for the dice roll function.
import java.util.Scanner; //used for user input.

public class Board {
    //Assign variables for user locations and characters to show up on the 'board'
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
    public char blank = '-';

    //boardLocations2 is used for testing and more importantly,
    //to get a visual of where board numbers fall on the array
    //used to calculate playerLocation and array representation
    public String[][] boardLocations2 = new String[10][10];

    //assigns values to the boardLocations2.
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

    //prints boardLocations2.
    //this will not print/display unless an object is created in the Main class
    //and initialized from there
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

    //This is the board that will be used in the game
    public char[][] boardLocations = new char[10][10];

    //Initializes the board
    public void boardInit(){
        for(int i = 0; i < 10; i++){
            for(int j = 0; j < 10; j++){
                boardLocations[i][j] = '-';
            }
        }

        //Adds the 'L' for Ladder locations on the board
        boardLocations[0][0] = ladder; boardLocations[0][3] = ladder; boardLocations[0][8] = ladder;
        boardLocations[2][0] = ladder; boardLocations[2][7] = ladder; boardLocations[5][9] = ladder;
        boardLocations[7][9] = ladder; boardLocations[7][0] = ladder;

        //Adds the 'S' for Snake locations on the board.
        boardLocations[1][3] = snake; boardLocations[5][6] = snake; boardLocations[6][3] = snake;
        boardLocations[6][1] = snake; boardLocations[8][6] = snake; boardLocations[9][7] = snake;
        boardLocations[9][5] = snake; boardLocations[9][2] = snake;
    }



    //function to print the board.
    public void printBoard(){
        System.out.println("\t1\t2\t3\t4\t5\t6\t7\t8\t9\t10");
        for(int i = 9; i >= 0; i--){
            System.out.print(i+1 + " ");
            for(int j = 0; j < 10; j++){
                System.out.print("\t" + boardLocations[i][j] + "  ");
            }
            System.out.println();
        }
        //Player icons/locations are added to the board in another function.
    }

    //Helps determine if user's location is a snake/ladder or none
    //this function will be used in another function to determine if action needs to be taken
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
            case 1:                 //if user lands on square '1'
                location = 38;     // location is 'moved' to location '38'
                return location;    //location is returned as such
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
    //for reasons I cannot think of, I made these separate and not the same
    public static int moveSnake(int location){
        switch(location){
            case 17:                //user lands on '17'
                location = 7;       // location is changed to 7
                return location;    //new value is returned as such
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


    //dice roll
    public static int diceRoll(){
        Random dice = new Random();
        return dice.nextInt(6) + 1;
    }

    public void movePlayer1(){
        if(player1Location == 0){
            //blank because if location is '0' I do not want it to try and assign a char to the array.
            //this will cause an exception error
        }else{
            playerLocation(blank, player1Location);         //clears out the current location to '-'
        }
        player1Location += diceRoll();                      //adds dice roll value to location
        if(snakeOrLadder(player1Location) == 1){            //function from earlier to check if lands on ladder
            player1Location = moveLadder(player1Location);  //updates location for 'moving up the ladder'
            playerLocation(player1Char, player1Location);   // updates char location on board
        }else if(snakeOrLadder(player1Location) == 2){      //function to check if lands on snake
            player1Location = moveSnake(player1Location);   //updates location for getting bit by snake
            playerLocation(player1Char, player1Location);   //updates char location on board
        }else{
            playerLocation(player1Char, player1Location);   //sets new location to player char.
        }
    }

    public void movePlayer2(){
        if(player2Location == 0){
            //blank because if '0' do not want it to try and assign a char to the array.
            //this will cause an exception error
        }else{
            playerLocation(blank, player2Location);
        }
        player2Location += diceRoll();

        if(snakeOrLadder(player2Location) == 1){
            player2Location = moveLadder(player2Location);
            playerLocation(player2Char, player2Location);
        }else if(snakeOrLadder(player2Location) == 2){
            player2Location = moveSnake(player2Location);
            playerLocation(player2Char, player2Location);
        }else{
            playerLocation(player2Char, player2Location);
        }
    }
    public void movePlayer3(){
        if(player3Location == 0){
            //blank because if '0' do not want it to try and assign a char to the array.
            //this will cause an exception error
        }else{
            playerLocation(blank, player3Location); //clears out the current location to '-'
        }
        player3Location += diceRoll();
        if(snakeOrLadder(player3Location) == 1){
            player3Location = moveLadder(player3Location);
            playerLocation(player3Char, player3Location);
        }else if(snakeOrLadder(player3Location) == 2){
            player3Location = moveSnake(player3Location);
            playerLocation(player3Char, player3Location);
        }else{
            playerLocation(player3Char, player3Location); //sets new location to player char.
        }
    }
    public void movePlayer4(){
        if(player4Location == 0){
            //blank because if '0' do not want it to try and assign a char to the array.
            //this will cause an exception error
        }else{
            playerLocation(blank, player4Location); //clears out the current location to '-'
        }
        player4Location += diceRoll();
        if(snakeOrLadder(player4Location) == 1){
            player4Location = moveLadder(player4Location);
            playerLocation(player4Char, player4Location);
        }else if(snakeOrLadder(player4Location) == 2){
            player4Location = moveSnake(player4Location);
            playerLocation(player4Char, player4Location);
        }else{
            playerLocation(player4Char, player4Location); //sets new location to player char.
        }
    }

    //trickiest part of the build
    //matching the playerLocation numbers to where they need to be on the board.
    public void playerLocation(char player, int location){
        int x = location / 10;
        int y = location % 10;

        if((x % 2 == 0) && y == 0){             //this is for 20, 40, 60, 80, 100
            x -= 1;                             //figured out that their locations were the first number reduced by one
            boardLocations[x][y] = player;
        }else if((x % 2 != 0) && y == 0){       //this is for 10, 30, 50, 70
            x -= 1;                             //first number reduced by 1, and the second value is 9
            y = 9;
            boardLocations[x][y] = player;
        }else if(x % 2 == 0){                   // Everyline that goes left to right, first row, third, fifth, etc
            y -= 1;                             //3 is [0][2], 28 is [2][7], 45 is [4][4], etc
            boardLocations[x][y] = player;
        }else{                        //Everything else that goes right to left. ie lines that start at 20 and end at 11
            y = 10 - y;                 //this one was mad, since they went from right to left, the values were inverse.
            boardLocations[x][y] = player;      //figured out that subtracting from 10, gave me the value I needed
        }                                       //19 is [1][1], 11 is [1][9], 39 is [3][1], 34 is [3][6]
    }


    public boolean isWinner(){ //boolean value to see if anyone has won yet
        if(player1Location >= 100 || player2Location >= 100 || player3Location >= 100 || player4Location >= 100){
            return false;
        }else{
            return true;
        }
    }

    public void currentLocation(){ //works with checkCurrentLocation(location) to display user progress
        System.out.println(player1Char + checkCurrentLocation(player1Location));
        System.out.println(player2Char + checkCurrentLocation(player2Location));
        System.out.println(player3Char + checkCurrentLocation(player3Location));
        System.out.println(player4Char + checkCurrentLocation(player4Location));
    }

    public String checkCurrentLocation(int location){ //this work with currentLocation function to print user status
        if(location == 38 || location == 14 || location == 31 || location == 42 || location == 84 || location == 67
            || location == 91 || location == 99){
            return " climbed a ladder and is at location " + location;
        }else if(location == 7 || location == 34 || location == 60 || location == 19 || location == 24
            || location == 73 || location == 75 || location == 79){
            return " got bit by a snake and is at location " + location;
        }else{
            return " is at location " + location;
        }
    }

    //this is where it all comes together
    public void runGame(){  //function to run the game
        boardInit();        //board is initialized, values added, etc
        printBoard();       //board is printed/displayed
        Scanner sc = new Scanner(System.in);    //Scanner class for user input, this is how we know to roll the dice

        try {       //using the try because when the locations are over 100, we get an exception error
            while (isWinner()) {    //while loop to keep the game going until we have someone over 100
                System.out.println("Type roll to roll your dice, or quit to end the game...");  //instructions for the user
                String roll = sc.nextLine(); //takes user input. user has to type and enter 'roll'

                if (roll.equals("roll")) { //if user enters the correct value, the game goes on
                    movePlayer1();  //rolls for user1
                    movePlayer2();  //rolls for user2
                    movePlayer3();  //rolls for user3
                    movePlayer4();  //rolls for user4
                    printBoard();   //prints updated board
                    currentLocation();  //prints off current user locations AND if they used a ladder/snake
                } else if(roll.equals("quit")){        //option to quit the game
                    break;
                }else{                          //if incorrect input is entered
                    System.out.println("Enter a valid input");
                }
            }
        }catch(Exception e){  //once the exception is reached, it means one of the locations is over 100
            if(player1Location >= 100){
                System.out.println("Player 1 won the game");
            }else if(player2Location >= 100){
                System.out.println("Player 2 won the game");
            }else if(player3Location >= 100){
                System.out.println("Player 3 won the game");
            }else{
                System.out.println("Player 4 won the game");
            }
        }

    }
}
