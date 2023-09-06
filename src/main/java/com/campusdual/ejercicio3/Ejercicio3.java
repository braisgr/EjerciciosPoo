package com.campusdual.ejercicio3;

import java.util.Scanner;

/*Utilizando switch escribir un programa que revise un número y diga si es primo o no
* el número tiene que estar entre 1 y 20*/
public class Ejercicio3 {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        System.out.println("Introduce el número");
        Integer num = sc.nextInt();

        switch (num){

            case 2:
            case 3:
            case 5:
            case 7:
            case 11:
            case 13:
            case 17:
            case 19:
                System.out.println("Es un número primo");
                break;
            default:
                System.out.println("No es un número primo");
                break;
        }
    }
}
