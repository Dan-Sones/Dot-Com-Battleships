import java.util.*;

public class DotComBust {
    private GameHelper helper = new GameHelper();
    private ArrayList<DotCom> dotComsList = new ArrayList<DotCom>();
    private int numOfGuesses = 0;

    private void setupGame(){
        //make some "dot coms" and give them locations
        DotCom one = new DotCom();
        one.setName("Pets.com");
        DotCom two = new DotCom();
        two.setName("eToys.com");
        DotCom three = new DotCom();
        three.setName("Go2.com");
        dotComsList.add(one);
        dotComsList.add(two);
        dotComsList.add(three);

        System.out.println("Yout Goal is to sink three dot coms.");
        System.out.println("Pets.com, eToys.com, Go2.com");
        System.out.println("Try to sink them in the fewest number of guesses");
        System.out.println("The format for guesssing is a-g followed by 0-6. For example A0 or B2)");

        for (DotCom dotComSet : dotComsList){
            ArrayList<String> newLocation = helper.placeDotCom(3);
            dotComSet.setLocationCells(newLocation);
        }//For loop
    }//SetupGame


    private void startPlaying(){
        while(!dotComsList.isEmpty()){
            String userGuess = helper.getUserInput("Enter a guess");
            checkUserGuess(userGuess);
        }
        finishGame();
    }//Start playing

    private void checkUserGuess(String userGuess){
        numOfGuesses++;
        String result = "miss";
        for(DotCom dotComToTest : dotComsList){
            result = dotComToTest.checkYourself(userGuess);
            if(result.equals("hit")){
                break;
            }
            if (result.equals("kill")){
                dotComsList.remove(dotComToTest);
                break;
            }
        }// for
        System.out.println(result);
    } // check user guess

    private void finishGame(){
        System.out.println("All dot coms are dead!! Your Stock is now worthless");
        if(numOfGuesses <= 18) {
            System.out.println("It only took you " + numOfGuesses + " guesses");
            System.out.println("You got out before yout options sank");
        }
        else{
            System.out.println("Took you long enough! " + numOfGuesses + " guesses");
            System.out.println("Fish are dancing with your spent shells!");
        }
    }// finishGame

    public static void main(String[] args){
        DotComBust game = new DotComBust();
        game.setupGame();
        game.startPlaying();
    }

}//class
