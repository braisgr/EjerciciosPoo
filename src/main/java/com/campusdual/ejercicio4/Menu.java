package com.campusdual.ejercicio4;

import java.util.ArrayList;
import java.util.Scanner;

/*
* --Escribe un programa que utilice la clase Dieta y despliegue un menú donde puedas añadir alimentos. El menú tendrá las siguientes opciones.
	-1. Crear/reiniciar dieta: crea o remplaza la dieta inicial
		-a. Sin limite
		-b. Por calorías
		-c. Por macronutrientes
		-d. Por datos personales
	-2. Mostrar información: muestra calorías y macronutrientes de la dieta
	-3. Agregar alimento: agrega un alimento a la dieta actual y añade ese alimento a la lista de alimentos disponible
		-a. Nuevo alimento
		-b. Alimento existente
	-4. Salir
* */
public class Menu {

    static Scanner sc = new Scanner(System.in);
    static Diet diet = null;
    static ArrayList<Food> foods = new ArrayList<>();
    static Food food = null;

    public static void mainMenu(){

        int option;

        do {

            System.out.println("Selecciona una opción de la lista:");
            System.out.println("1- Crear/reiniciar dieta (Crear dieta nueva o reemplazar la existente)");
            System.out.println("2- Mostrar información de la dieta");
            System.out.println("3- Agregar alimento a la dieta");
            System.out.println("4- Salir");
            option = sc.nextInt();

            switch (option) {

                case 1:
                    createDietMenu();
                    break;

                case 2:
                    Diet.showActualDietDetails();
                    break;

                case 3:
                    addFoodMenu();
                    break;

                case 4:
                    System.out.println("Gracias! Hasta luego");
                    sc.close();
                    System.exit(0);
                    break;

                default:
            }
        }while(option != 4);
    }

    public static void createDietMenu(){

        int option;

        do{

            System.out.println("Selecciona una opción para crear una dieta:");
            System.out.println("1- Sin límite de kcal");
            System.out.println("2- Por calorias máximas");
            System.out.println("3- Por macronutrientes");
            System.out.println("4- Por datos personales");
            System.out.println("5- Volver al menú principal");
            option = sc.nextInt();
            sc.nextLine();

            switch (option){

                case 1:
                    diet = new Diet();
                    System.out.println("Dieta sin límite de calorías creada");
                    Diet.resetDietFoodAndWeights();
                    break;

                case 2:
                    System.out.println("Ingresa el límite máximo de kcal con la que deseas crear la dieta:");
                    Integer maxCalories = sc.nextInt();

                    diet = new Diet(maxCalories);
                    System.out.println("\nDieta creada con un límite de " +maxCalories+ " kcal");
                    Diet.resetDietFoodAndWeights();
                    break;

                case 3:
                    System.out.println("Ingresa el límite máximo de carbohidratos de la dieta (En gramos):");
                    Integer maxCarbs = sc.nextInt();
                    System.out.println("Ingresa el límite máximo de grasas de la dieta (En gramos):");
                    Integer maxFats = sc.nextInt();
                    System.out.println("Ingresa el límite máximo de proteínas de la dieta (En gramos):");
                    Integer maxProteins = sc.nextInt();

                    diet = new Diet(maxCarbs, maxFats, maxProteins);

                    System.out.println("\nDieta creada con un límite de " +maxCarbs+ "g de carbohidratos, " +
                            maxFats+ "g de grasas y " +maxProteins+ "g de proteínas");
                    Diet.resetDietFoodAndWeights();
                    break;

                case 4:
                    String sex;
                    do{
                        System.out.println("\nIntroduce si el sexo es masculino o femenino (Escriba M o F):");
                        sex = sc.nextLine();
                    }while((!sex.equalsIgnoreCase("M") && !sex.equalsIgnoreCase("F")) || sex.length()>1);

                    Boolean woman;
                    if(sex.equalsIgnoreCase("F")){
                        woman = true;
                    }else{
                        woman= false;
                    }

                    System.out.println("Introduce la edad:");
                    Integer age = sc.nextInt();
                    System.out.println("Introduce la altura (En cm):");
                    Integer height = sc.nextInt();
                    System.out.println("Introduce el peso (En kg):");
                    Integer weight = sc.nextInt();

                    Integer maxKcal = Diet.metabolismCalc(woman, age, height, weight);

                    diet = new Diet(maxKcal, woman, age, height, weight);
                    System.out.println("\nDieta creada con un límite de " +maxKcal+ " kcal");
                    Diet.resetDietFoodAndWeights();
                    break;

                case 5:
                    break;

                default:
                    System.out.println("Por favor, selecciona una opción correcta.");
                    break;
            }

        }while(option != 5);


    }

    public static void addFoodMenu(){

        int option;

        do{
            System.out.println("Selecciona una opción para agregar alimentos a la dieta:");
            System.out.println("1- Agregar un alimento nuevo");
            System.out.println("2- Agregar un alimento existente");
            System.out.println("3- Volver al menú principal");
            option = sc.nextInt();
            String name = "";
            Integer weight = null;

            switch(option){

                case 1:

                    if (diet == null){
                        System.out.println("Primero debes de dar de alta una nueva dieta\n");
                    }else{
                        System.out.println("Indica el nombre del alimento:");
                        sc.nextLine();
                        name = sc.nextLine();
                        System.out.println("Indica la cantidad de carbohidratos por cada 100g de producto (En gramos):");
                        Integer carbos = sc.nextInt();
                        System.out.println("Indica la cantidad de grasas por cada 100g de producto (En gramos):");
                        Integer fats = sc.nextInt();
                        System.out.println("Indica la cantidad de proteinas por cada 100g de producto (En gramos):");
                        Integer proteins = sc.nextInt();
                        System.out.println("Indica el peso total (En gramos):");
                        weight = sc.nextInt();

                        food = new Food(name, carbos, fats, proteins);
                        foods.add(food);
                        Diet.addFood(food, weight);

                    }
                    break;

                case 2:

                    if (diet == null){
                        System.out.println("Primero debes de dar de alta una nueva dieta\n");
                    }else{
                        System.out.println("**LISTA DE ALIMENTOS**");

                        for(int i=0; i<foods.size(); i++){

                            System.out.println((i + 1) + "** " + foods.get(i).getName());
                        }

                        Boolean validFood = false;
                        Food selectedFood = null;
                        do{
                            System.out.println("\nIngresa el nombre del alimento que deseas introducir en la dieta:");
                            sc.nextLine();
                            name = sc.nextLine();


                            for(Food f:foods){
                                if (f.getName().equalsIgnoreCase(name)){
                                    selectedFood = f;
                                    validFood = true;
                                }else{
                                    System.out.println("El alimento introducido no se encuentra registrado en la lista de alimentos");
                                }
                            }
                        }while(!validFood);

                        System.out.println("Ingresa la cantidad en gramos:");
                        weight = sc.nextInt();

                        Diet.addFood(selectedFood, weight);
                        System.out.println("Se han añadido " +weight+ "g de " +name+ " a la dieta actual");
                    }
                    break;


                case 3:
                    break;
            }
        }while(option != 3);
    }

}
