package com.example.rom4uk.tictactoe_javaconsole;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.Scanner;

public class MainActivity extends AppCompatActivity {
        public static final String EMPTY="   ";
        public static final String TIC=" X ";
        public static final String TAC=" O ";

        public static String ActivePlayer;

        public static final int LINES=3;
        public static final int COLUMNS=3;

        public static String [][] playingfield = new String[LINES][COLUMNS];

        public static int GameStatus;
        public static final int GAME_IS_RUNNING=1;
        public static final int STATUS_DRAW=2;
        public static final int STATUS_WINNER_X=3;
        public static final int STATUS_WINNER_O=4;

        public static Scanner in = new Scanner(System.in);

        public static void main (String[] args){
            BeginGame();
            do {
                GetPlayerInput();
                GameAnalysis();
                BringOutPlayfield();
                if (GameStatus == STATUS_WINNER_X){
                    System.out.println("Переміг Х. Вітаємо:)");
                } else if (GameStatus == STATUS_WINNER_O){
                    System.out.println("Переміг О. Вітаємо:)");
                } else  if (GameStatus == STATUS_DRAW){
                    System.out.println("Гра закінчилась внічию. До побачення!");
                }
                ActivePlayer = (ActivePlayer==TIC?TAC:TIC);
            }while (GameStatus == GAME_IS_RUNNING);
        }
        public static void BeginGame (){
            for (int line=0; line<LINES; line++){
                for(int column=0; column<COLUMNS; column++){
                    playingfield [line][column] = EMPTY;
                }
            }
            ActivePlayer=TIC;
            BringOutPlayfield();
        }
        public static void GetPlayerInput(){
            boolean TrueInput = false;
            do {
                System.out.println("Гравець" +ActivePlayer+ "введіть рядок (1-3) і стовпчик (1-3) через пробіл");
                int line = in.nextInt()-1;
                int column = in.nextInt()-1;
                if (line>=0 && line<LINES && column>=0 && column<COLUMNS && playingfield[line][column]==EMPTY){
                    playingfield[line][column] = ActivePlayer;
                    TrueInput = true;
                }else{
                    System.out.println("Вибране розміщення (" + (line+1) + "," + (column+1) + ")не може бути використане. Спробуйте ще раз");
                }
            }while (!TrueInput);
        }
        public static void GameAnalysis(){
            String winner = FindWinner();
            if (winner.equals(TIC)){
                GameStatus = STATUS_WINNER_X;
            } else if (winner.equals(TAC)){
                GameStatus = STATUS_WINNER_O;
            } else  if (AreAllCellsField()){
                GameStatus = STATUS_DRAW;
            } else {
                GameStatus = GAME_IS_RUNNING;
            }
        }
        public static boolean AreAllCellsField(){
            for (int line = 0; line<LINES; line ++){
                for (int column=0; column<COLUMNS; column++){
                    if (playingfield[line][column] == EMPTY){
                        return false;
                    }
                }
            }
            return true;
        }
        public static String FindWinner(){
            int NumberOfIdentical;
            for ( int line = 0; line<LINES; line++){
                NumberOfIdentical = 0;
                for ( int column = 0; column<COLUMNS; column++){
                    if (playingfield[line][0]!=EMPTY && playingfield[line][0] == playingfield[line][column]){
                        NumberOfIdentical++;
                    }
                }
                if (NumberOfIdentical == 3){
                    return playingfield[line][0];
                }
            }
            for ( int column = 0; column<COLUMNS; column++){
                NumberOfIdentical = 0;
                for ( int line = 0; line<LINES; line++){
                    if (playingfield[0][column]!=EMPTY && playingfield[0][column] == playingfield[line][column]){
                        NumberOfIdentical++;
                    }
                }
                if (NumberOfIdentical == 3){
                    return playingfield[0][column];
                }
            }
            if (playingfield[0][0]!=EMPTY && playingfield[0][0] == playingfield[1][1] && playingfield [0][0] ==playingfield[2][2]){
                return playingfield [0][0];
            }
            if (playingfield[0][2]!=EMPTY && playingfield[1][1] == playingfield[0][2] && playingfield [2][2] ==playingfield[2][2]){
                return playingfield [0][2];
            }
            return EMPTY;
        }
        public static void BringOutPlayfield(){
            for (int line = 0; line<LINES; line++){
                for(int column=0; column<COLUMNS; column++){
                    System.out.print(playingfield[line][column]);
                    if (column!=COLUMNS-1){
                        System.out.print("|");
                    }
                }
                System.out.println();
                if (line!=LINES-1){
                    System.out.println("-----------");
                }
            }
            System.out.println();
        }
    }
    
