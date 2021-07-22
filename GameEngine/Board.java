package GameEngine;

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
    /*
    public void boardInit(){
        int level = 1;
        int something = 10;
        for(int i = 0; i < 10; i++){
            for(int j = 0; j < 10; j++){
                if((i+1)%2==0){
                    boardLocations[i][j] = something + " and " + i + ", " +j;
                }else{
                    boardLocations[i][j] = level + " and " + i + ", " +j;
                }
                //boardLocations[i][j] =  " " + level;
                level++;
                something--;
            }
            something += 20;
        }
    }
     */
    
    //moving this one down here to keep the code clean. The above one for location finding.
    public char[][] boardLocations = new char[10][10];

    public void boardInit(){
        for(int i = 0; i < 10; i++){
            for(int j = 0; j < 10; j++){
                boardLocations[i][j] = '-';
            }
        }
    }



    //function to print off the board.
    //will be used a lot.
    public void printBoard(){
        for(int i = 9; i >= 0; i--){
            System.out.print(i+1 + " ");
            for(int j = 0; j < 10; j++){
                System.out.print("\t" + boardLocations[i][j] + "  ");
            }
            System.out.println();
        }
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
        int choice = snakeOrLadder(location);
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
}
