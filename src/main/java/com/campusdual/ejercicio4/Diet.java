package com.campusdual.ejercicio4;

import java.util.HashMap;
import java.util.Map;

/*
* Escribe una clase dieta, que teniendo en cuenta una serie de alimentos, vaya sumando cantidades en gramos
* y calcule cuentas calorías, carbohidratos, proteinas y grasas genera la ingesta
La clase dieta tiene que tener las siguientes funcionalidades:
	-Diet(): crea una dieta sin límite de calorías
	-Diet(Integer maxCalories): crea una dieta con un máximo de calorías, en cuanto se supera ese máximo cuando se adjunta un alimento se despliega un error
	-Diet(Integer maxFats, Integer maxCarbs, Integer maxProtein): crea una dieta con un máximo de estos tres valores, si se supera alguno de ellos cuando se adjunta un alimento se indicara que macronutriente y desplegará un error
	-Diet(Boolean women, Integer age, Integer height, Integer weight): Calcula el metabolismo basal de la persona y lo asigna como máximo de calorías en la dieta
		--CALCULAR METABOLISMO BASAL
			E = edad
			A = altura en centímetros
			P = peso en kilos

			Fórmula Hombres: TMB = 10P + 6,25A – 5E + 5
			Fórmula Mujeres: TMB = 10P + 6,25A – 5E – 161
	-addFood(Food,Integer): agrega un alimento y una cantidad en gramos
	-getTotalCalories(): devuelve el total de calorías
	-getTotalCarbs(): devuelve el total de carbohidratos
	-getTotalFats(): devuelve el total de grasas
	-getTotalProtein(): devuelve el total de proteinas
*
* */
public class Diet {
    //Atributos de clase
    private Integer maxCalories;
    private Integer maxFats;
    private Integer maxCarbs;
    private Integer maxProteins;
    private static Map<Food, Integer> dietFoodAndWeights;


    //Constructores
    public Diet(){
        dietFoodAndWeights = new HashMap<>();
    }

    public Diet(Integer maxCalories){
        this.maxCalories = maxCalories;
        dietFoodAndWeights = new HashMap<>();
    }

    public Diet(Integer maxCarbs, Integer maxFats, Integer maxProteins){

        this.maxCarbs = maxCarbs;
        this.maxFats = maxFats;
        this.maxProteins = maxProteins;
        dietFoodAndWeights = new HashMap<>();
    }

    //Getters y setters
    public Integer getMaxCalories() {
        return maxCalories;
    }

    public void setMaxCalories(Integer maxCalories) {
        this.maxCalories = maxCalories;
    }

    public Integer getMaxFats() {
        return maxFats;
    }

    public void setMaxFats(Integer maxFats) {
        this.maxFats = maxFats;
    }

    public Integer getMaxCarbs() {
        return maxCarbs;
    }

    public void setMaxCarbs(Integer maxCarbs) {
        this.maxCarbs = maxCarbs;
    }

    public Integer getMaxProteins() {
        return maxProteins;
    }

    public void setMaxProteins(Integer maxProteins) {
        this.maxProteins = maxProteins;
    }

    //Otros métodos

    public static Integer metabolismCalc(Boolean woman, Integer age, Integer height, Integer weight){

        if(woman == true){
            return (weight*10) + (int)(height*6.25) - (age*5) - 161;
        }
        else{
            return (weight*10) + (int)(height*6.25) - (age*5) + 5;
        }
    }

    public static void addFood(Food food, Integer weight){

        Integer totalCaloriesBeforeAdding = getTotalCalories();
        Integer totalCarbsBeforeAdding = getTotalCarbs();
        Integer totalFatsBeforeAdding = getTotalFats();
        Integer totalProteinsBeforeAdding = getTotalProteins();

        // Calcular las calorías del nuevo alimento ajustadas por el peso en gramos
        Integer caloriesOfNewFood = (food.getCalories(100) * weight) / 100;
        Integer carbsOfNewFood = (food.getCarbos() * weight) / 100;
        Integer fatsOfNewFood = (food.getFats() * weight) / 100;
        Integer proteinsOfNewFood = (food.getProteins() * weight) / 100;

        // Verificar si se supera el límite máximo de calorías
        if (Menu.diet.maxCalories != null && totalCaloriesBeforeAdding + caloriesOfNewFood > Menu.diet.maxCalories) {
            System.out.println("No se puede agregar el alimento. Se supera el límite de calorías permitido.");
        }
        else if (Menu.diet.maxCarbs != null && totalCarbsBeforeAdding + carbsOfNewFood > Menu.diet.maxCarbs){
            System.out.println("No se puede agregar el alimento. Se supera el límite de carbohidratos permitido.");
        }
        else if (Menu.diet.maxFats != null && totalFatsBeforeAdding + fatsOfNewFood > Menu.diet.maxFats) {
            System.out.println("No se puede agregar el alimento. Se supera el límite de grasas permitido.");
        }
        else if (Menu.diet.maxProteins != null && totalProteinsBeforeAdding + proteinsOfNewFood > Menu.diet.maxProteins) {
            System.out.println("No se puede agregar el alimento. Se supera el límite de proteínas permitido.");
        }
        else {
            dietFoodAndWeights.put(food, weight);
            System.out.println("Se han añadido " +weight+ "g de " +food.getName()+ " a la dieta actual");
        }
    }

    public static Integer getTotalCalories(){

        Integer totalCalories = 0;

        for (Map.Entry<Food, Integer> entry : dietFoodAndWeights.entrySet()) {
            Food food = entry.getKey();
            int weight = entry.getValue();

            // Calcular las calorías del alimento y sumarlas al total
            Integer calories = food.getCalories(100);
            totalCalories += (calories * weight) / 100; // Ajustar por el peso en gramos
        }

        return totalCalories;
    }

    public static Integer getTotalCarbs(){

        Integer totalCarbs = 0;

        for(Map.Entry<Food, Integer> entry : dietFoodAndWeights.entrySet()){
            Food food = entry.getKey();
            int weight = entry.getValue();

            int carbs = (food.getCarbos()*weight)/100;
            totalCarbs += carbs;
        }
        return totalCarbs;
    }

    public static Integer getTotalFats(){

        Integer totalFats = 0;

        for(Map.Entry<Food, Integer> entry : dietFoodAndWeights.entrySet()){
            Food food = entry.getKey();
            int weight = entry.getValue();

            int fats = (food.getFats()*weight)/100;
            totalFats += fats;
        }
        return totalFats;
    }

    public static Integer getTotalProteins(){

        Integer totalProteins = 0;

        for(Map.Entry<Food, Integer> entry : dietFoodAndWeights.entrySet()){
            Food food = entry.getKey();
            int weight = entry.getValue();

            int proteins = (food.getProteins()*weight)/100;
            totalProteins += proteins;
        }
        return totalProteins;
    }

    public static void resetDietFoodAndWeights(){
        dietFoodAndWeights.clear();
    }

    public static void showActualDietDetails() {

        if (Menu.diet == null) {
            System.out.println("Por favor, da de alta una dieta en primer lugar");
        }
            else{
                System.out.println("***Detalles de la dieta actual***");

                System.out.println("\nAlimentos en la dieta:");
                for (Map.Entry<Food, Integer> entry : dietFoodAndWeights.entrySet()) {
                    Food food = entry.getKey();
                    int weight = entry.getValue();
                    System.out.println("- " + food.getName() + ": " + weight + " gramos");
                }

                // Mostrar totales de calorías, carbohidratos, grasas y proteínas
                int totalCalories = getTotalCalories();
                int totalCarbs = getTotalCarbs();
                int totalFats = getTotalFats();
                int totalProteins = getTotalProteins();

                System.out.println("Total de calorías: " + totalCalories + " kcal");
                System.out.println("Total de carbohidratos: " + totalCarbs + " gramos");
                System.out.println("Total de grasas: " + totalFats + " gramos");
                System.out.println("Total de proteínas: " + totalProteins + " gramos");
            }
    }

}
