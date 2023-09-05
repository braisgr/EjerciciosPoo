package com.campusdual.ejercicio2;

import java.util.Scanner;

//Crear un programa que con un switch nos indique si un año es bisiesto o no
        /*Año bisiesto es el divisible entre 4, salvo que sea año secular
        -último de cada siglo, terminado en «00»-, en cuyo caso también ha de ser divisible entre 400.*/
public class Ejercicio2 {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.println("Introduce el año");
        int anho = sc.nextInt();

        if (anho%4 == 0){

            if(anho%400 != 0 && anho%100 == 0){
                System.out.println("No es bisiesto");
            }
            else{
                System.out.println("Es bisiesto");
            }
        }else{
            System.out.println("Es bisiesto");
        }





    }
}
