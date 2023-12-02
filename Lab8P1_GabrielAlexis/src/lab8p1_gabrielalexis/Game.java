package lab8p1_gabrielalexis;

import java.util.Random;
import java.util.Scanner;
import java.util.ArrayList;

public class Game {

    public Scanner input = new Scanner(System.in);
    public Random rand = new Random();
    public int[][] tablero = new int[10][10];
    public ArrayList<Integer> Cords = new ArrayList<>();

    public void iniciar() {
        String resp = "s";
        while (resp.equalsIgnoreCase("s")) {
            System.out.println("Ingrese el numero de rondas: ");
            int rondas = input.nextInt();
            while (rondas <= 0) {
                System.out.println("El número de rondas debe ser positivo. Inténtalo de nuevo.");
                rondas = input.nextInt();
            }
            jugar(rondas);
            System.out.println("¿Desea hacer otra simulación? (s/n)");
            resp = input.next();
        }
    }

    public void imprimir(int[][] matriz) {
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[i].length; j++) {
                System.out.print("[" + matriz[i][j] + "]");
            }
            System.out.println();
        }
    }

    public int[][] llenarRandom() {
        int[][] temporal = new int[10][10];
        for (int i = 0; i < temporal.length; i++) {
            for (int j = 0; j < temporal.length; j++) {
                if (i == 0 || j == 0 || i == temporal.length - 1 || j == temporal.length - 1) {
                    temporal[i][j] = 0;
                } else {
                    temporal[i][j] = rand.nextInt(2);
                }
            }
        }
        return temporal;
    }

    public void jugar(int rondas) {
        ArrayList<String> cordVivas = new ArrayList<>();
        for (int i = 0; i <= rondas; i++) {
            if (i == 0) {
                tablero = llenarRandom();
                cordVivas = getCords(tablero);
                System.out.println("Coordenadas de los 1:");
                for (int j = 0; j < cordVivas.size(); j++) {
                    System.out.print(cordVivas.get(j) + " ");
                }
                System.out.println("\n");
                imprimir(tablero);
            } else {
                System.out.println("Coordenadas de los 1:");
                for (int j = 0; j < cordVivas.size(); j++) {
                    System.out.print(cordVivas.get(j) + " ");
                }
                System.out.println("");
                System.out.println("Ronda " + i);
                tablero = nextGen(tablero);
                cordVivas = getCords(tablero);
                imprimir(tablero);
            }
        }
    }

    public ArrayList<String> getCords(int matriz[][]) {
        ArrayList<String> cordVivas = new ArrayList<>();
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[i].length; j++) {
                if (matriz[i][j] == 1) {
                    cordVivas.add(i + ":" + j);
                }
            }
        }
        return cordVivas;
    }

    public int numVecinos(int matriz[][], int posx, int posy) {
        int vecinos = 0;

        if (posx - 1 >= 0 && matriz[posx - 1][posy] == 1) {
            vecinos++;
        }
        if (posx + 1 < matriz.length && matriz[posx + 1][posy] == 1) {
            vecinos++;
        }
        if (posy - 1 >= 0 && matriz[posx][posy - 1] == 1) {
            vecinos++;
        }
        if (posy + 1 < matriz[0].length && matriz[posx][posy + 1] == 1) {
            vecinos++;
        }
        if (posx + 1 < matriz.length && posy - 1 >= 0 && matriz[posx + 1][posy - 1] == 1) {
            vecinos++;
        }
        if (posx - 1 >= 0 && posy - 1 >= 0 && matriz[posx - 1][posy - 1] == 1) {
            vecinos++;
        }
        if (posx + 1 < matriz.length && posy + 1 < matriz[0].length && matriz[posx + 1][posy + 1] == 1) {
            vecinos++;
        }
        if (posx - 1 >= 0 && posy + 1 < matriz[0].length && matriz[posx - 1][posy + 1] == 1) {
            vecinos++;
        }

        return vecinos;
    }

    public int[][] nextGen(int actual[][]) {
        int temporal[][] = new int[actual.length][actual[0].length];
        for (int i = 0; i < actual.length; i++) {
            for (int j = 0; j < actual[i].length; j++) {
                if (i == 0 || j == 0 || i == actual.length - 1 || j == actual[i].length - 1) {
                    temporal[i][j] = 0;
                } else {
                    int vecinos = numVecinos(actual, i, j);
                    if (actual[i][j] == 1 && vecinos < 2) {
                        temporal[i][j] = 0;
                    } else if (actual[i][j] == 1 && (vecinos == 2 || vecinos == 3)) {
                        temporal[i][j] = 1;
                    } else if (actual[i][j] == 1 && vecinos > 3) {
                        temporal[i][j] = 0;
                    } else if (actual[i][j] == 0 && vecinos == 3) {
                        temporal[i][j] = 1;
                    } else {
                        temporal[i][j] = actual[i][j];
                    }
                }
            }
        }
        return temporal;
    }
}
