package edu.fiuba.algo3.Turn;

import edu.fiuba.algo3.Exceptions.InsuficientCredits;
import edu.fiuba.algo3.Exceptions.NonExistentArticle;
import edu.fiuba.algo3.Exceptions.WrongFormat;
import edu.fiuba.algo3.Exceptions.WrongPlace;
import edu.fiuba.algo3.Interface.Game;
import edu.fiuba.algo3.TypeData.Coordinate;

import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PlayerTurn implements Turn{

    private Game game;

    private static Scanner input;

    public PlayerTurn(Game game){
        this.game = game;
        input = new Scanner(System.in);
    }

    @Override
    public void executeTurn(){
        playerAction();
    }


    private void playerAction(){
        System.out.println("Please. Select one option:");
        System.out.println("1. Construct tower.");
        System.out.println("2. Change turn.");
        String choice = input.nextLine();

        switch (choice) {
            case "1":
                this.selectDefense();
                playerAction();
                break;
            case "2":
                return;
            default:
                System.out.println("Please, enter '1' to build or '2' to change turn");
                playerAction();
                break;
        }


    }

    private void selectDefense(  ){
        System.out.println("Select the defense that you want to build.");
        System.out.println("- White Tower");
        System.out.println("- Silver Tower");
        System.out.println("- Sand Trap");

        Scanner input = new Scanner(System.in);
        String choice = input.nextLine();
        System.out.println("Your choice: "+choice);
        try {
            game.buyDefense(choice);
            placeDefense();
        } catch (InsuficientCredits e) {
            System.out.println("You have not enough credits to buy the tower.");
        } catch (NonExistentArticle e) {
            System.out.println("Please. Choice one of the defenses presented in the options.");
            selectDefense();
        }
    }

    private void placeDefense(){
        System.out.print("Please place your bought defense. The format to collocate the defense is '(x, y)' " );
        System.out.print("where x is the position of the axis x, starting with zero in the left part of the screen, ");
        System.out.print("rising to right, and y is the position of the axis y, starting with zero in the superior part ");
        System.out.println("of the screen and rising to the inferior part of the screen.");
        Scanner input = new Scanner(System.in);
        String choice = input.nextLine();

        try {
            Coordinate position = transformInCoordinate(choice);
            game.locateLastBoughtDefenseIn(position);
        }catch ( RuntimeException e ){
            Logger.getLogger("Player").log(Level.SEVERE, e.getMessage());
            System.out.println("Please, be sure that the format of the position is correct.");
            placeDefense();
        } catch (WrongPlace e) {
            Logger.getLogger("Player").log(Level.WARNING, e.getMessage());
            Logger.getLogger("Player").log(Level.INFO, "Please, locate the defense in a correct place");
            placeDefense();
        }

    }


    private Coordinate transformInCoordinate( String positionString ){

        if( theCommaIsInWrongPlace(positionString) || theParenthesisAreInBadPlace(positionString) ){
            throw new WrongFormat("The format of the position is incorrect.");
        }
        String commaSeparatedNumbers = positionString.substring(1, positionString.length()-1);
        String[] numbers = commaSeparatedNumbers.split(",");

        double x = Double.parseDouble(numbers[0]);
        double y = Double.parseDouble(numbers[1]);
        Coordinate position = new Coordinate(x, y);

        return position;
    }

    private boolean theParenthesisAreInBadPlace( String strCoordinate ){
        return (strCoordinate.lastIndexOf("(") != 0 || strCoordinate.indexOf(")") != (strCoordinate.length() -1));
    }

    private boolean theCommaIsInWrongPlace( String strCoordinate ){
        return (!strCoordinate.contains(",") || strCoordinate.indexOf(",") == 1 || strCoordinate.indexOf(",") == (strCoordinate.length()-2));
    }

    public void close(){
        input.close();
    }
}
