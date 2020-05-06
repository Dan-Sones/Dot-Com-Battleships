import java.util.*;

public class DotComBust {
    //Declare and initalize all the variables we will need
    private GameHelper helper = new GameHelper();
    private ArrayList<DotCom> dotComsList = new ArrayList<DotCom>();
    private int numOfGuesses = 0;

    private void setupGame(){
        //make three DotCom objects and give them names
        DotCom one = new DotCom();
        one.setName("Pets.com");
        DotCom two = new DotCom();
        two.setName("eToys.com");
        DotCom three = new DotCom();
        three.setName("Go2.com");
        //Add the three objects to the array list
        dotComsList.add(one);
        dotComsList.add(two);
        dotComsList.add(three);
        //Print out brief instructions for the user
        System.out.println("Your Goal is to sink three DotComs/Ships.");
        System.out.println("Pets.com, eToys.com, Go2.com");
        System.out.println("Try to sink them in the fewest number of guesses");
        System.out.println("The format for guesssing is a-g followed by 0-6. For example A0 or B2)");

        for (DotCom dotComSet : dotComsList){ //Repeat with each DotCom in the ArrayList
            ArrayList<String> newLocation = helper.placeDotCom(3); //Ask the helper for a DotCom location
            dotComSet.setLocationCells(newLocation); //call the setter method on this dotcom to give it the location you just got from the helper
        }//For loop
    }//SetupGame


    private void startPlaying(){
        //While the dotcom list is NOT empty
        while(!dotComsList.isEmpty()){
            String userGuess = helper.getUserInput("Enter a guess");//get user input
            checkUserGuess(userGuess);//call the check user guess method
        }
        finishGame(); // call our FinishGame method
    }//Start playing

    private void checkUserGuess(String userGuess){
        numOfGuesses++; //increment the number of guesses the user has made
        String result = "miss"; //Assume it's a miss unless told otherwise further on
        for(DotCom dotComToTest : dotComsList){ //repear with all DotComs in the list
            result = dotComToTest.checkYourself(userGuess);
            if(result.equals("hit")){
                break; //get out of the loop early, no use in testing the others
            }
            if (result.equals("kill")){
                //Dead so remove him from the list and break out of the loop
                dotComsList.remove(dotComToTest);
                break;
            }
        }// for
        System.out.println(result);//print the result for the user
    } // check user guess

    private void finishGame(){
        //print a message telling the user how they did in the game
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
        DotComBust game = new DotComBust(); //Create the game object
        game.setupGame(); //tell the game object to set up the game
        game.startPlaying(); //tell the game object to start the main gameplay loop (keeps asking for User input and checking the guess)
    }

}//class
