package rocks.zipcode;

import java.util.Locale;

public class Main {

public static void main(String[] args) {
    String wannaPlay = "YES";
    String playerGuess;
    boolean end = false;
    do{
        System.out.println("Let's play some hangman!");
        Hangman newGame = new Hangman();
        newGame.showPlayer();
        while(end==false) {
            playerGuess = newGame.takeValidInput();
            end = newGame.checkGameEnd(newGame.guessCheck(playerGuess));
        }
        end=true;
        wannaPlay=newGame.quit();
    }while (wannaPlay.equals("YES")||wannaPlay.equals("Y"));
}
}
