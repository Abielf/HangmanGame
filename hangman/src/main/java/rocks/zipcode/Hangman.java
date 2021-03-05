package rocks.zipcode;

import java.util.Locale;
import java.util.Scanner;
import java.util.Random;

public class Hangman {
    String[][] display;
    Integer guesses;
    String userGuess;



    public Hangman() {
        this.display=setup();
        this.guesses=6;
        this.userGuess="";

    }


    //Sets up the 2d array, row 1 is the answer row 2 is what the player sees.
    public String[][] setup(){
        String chosen= pickWord();
        int wordLength=chosen.length();
        String[][] answerArray = new String[2][wordLength];

        for(int i=0; i< wordLength; i++){
            answerArray[0][i]=String.valueOf(chosen.charAt(i));
            answerArray[1][i]="_";
        }
        return answerArray;
    }



    public String pickWord(){
        int difficultyLength=difficulty();
        String picked;
        do{
        String wordBank[]={"AGAIN","HEART","PIZZA","WATER","CAMEL","HOTEL","LOSER",
        "TREASURE", "ABDICATE", "PRINCESS","SANDWICH","FAVORITE","BUSINESS","MOUNTAIN",
            "VELOCIRAPTOR", "CIVILIZATION", "HIPPOPOTAMUS", "ARCHITECTURE","RESURRECTION",
            "INTELLIGENCE","CRYOSKELETON"};
        Random answerIndex = new Random();
        int pickIndex =  answerIndex.nextInt( 21);
        picked = wordBank[pickIndex];
        }while (picked.length()!=difficultyLength);
        return  picked;
    }
    public Integer difficulty(){
        String setting;
        Scanner getInput = new Scanner(System.in);
        System.out.println("Choose a difficulty setting.");
        int diffSetting=0;
        do{
            System.out.println("Easy, Medium, or Hard");
            setting=getInput.next();
        if(setting.equalsIgnoreCase("Easy")){
            diffSetting=5;
        }
        else if(setting.equalsIgnoreCase("Medium")){
            diffSetting= 8;
        }
        else if(setting.equalsIgnoreCase("Hard")){
            diffSetting= 12;
        }
        else {System.out.println("Pick a valid difficulty please.");}
        }while(diffSetting == 0);
        return diffSetting;
    }




    public boolean guessCheck(String userGuess){
        boolean goodGuess=false;
        for (int i=0;i<display[0].length;i++){
            if (userGuess.equals(display[0][i])){
                display[1][i]=userGuess;
                goodGuess=true;
            }
        }
        return goodGuess;
    }

    public boolean checkGameEnd(boolean goodGuess){
        boolean gameOver=false;
        showPlayer();
        if (goodGuess==false){
            guesses--;
            if (guesses==0){
                System.out.println("   You lose!");
                System.out.print("The word was:  ");
                showWord();
                gameOver=true;
            } else {System.out.println("    Bzzt! Wrong!");
            System.out.println("Guesses left: "+guesses);}
        } else {
            gameOver=checkWin();
            System.out.println("     Good guess!");
            gameOver=checkWin();

        }
        return gameOver;
    }

    public boolean checkWin(){
        boolean winner = true;
        for (int i=0; i< display[0].length; i++) {
            if (!(display[0][i].equals(display[1][i]))) {
                winner = false;
            }
        }
        if (winner==true) {
            System.out.println("You did it! You got the word!");
        }
        return winner;
    }


    //Prompts user for valid input
    public String takeValidInput(){
        Scanner getInput = new Scanner(System.in);
        boolean goodInput;
        do {
            System.out.println("Guess a letter!");
            userGuess=getInput.next();
            goodInput=isItValid();
        }while(!goodInput);
        userGuess=userGuess.toUpperCase();
        return userGuess;
    }

    //Verifies input is legal, one character. No repeat guesses.
    public boolean isItValid(){
        if(userGuess.length()==1) {
            char temp = userGuess.charAt(0);
            if (Character.isLetter(temp)) { return true;
            }else {System.out.println("Enter a valid letter.");
                return false;}
        }else {System.out.println("Just one letter at a time please.");
            return false;}
    }





    //Player can see their guessing progress
    public void showPlayer(){
        for(int i=0;i<display[0].length;i++){
            System.out.print(display[1][i]);
        }
    }

    //shows the top row of the array if called
    public void showWord(){
        for(int i=0;i<display[0].length;i++){
            System.out.print(display[0][i]);
        }
    }

    //Prompts user to enter whether or not they wish to quit.
    public String quit() {
        System.out.println("Did you want to play again?");
        Scanner quitInput = new Scanner(System.in);
        String quitString= quitInput.nextLine();
        if (quitString.equalsIgnoreCase("YES")||quitString.equalsIgnoreCase("Y")){
            quitString=quitString.toUpperCase();
        }
        return quitString;
    }
}
