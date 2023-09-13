package com.campusdual.ejercicio5;

import com.campusdual.ejercicio5.exceptions.MaxCaloriesReachedException;
import com.campusdual.ejercicio5.exceptions.MaxCarbsReachedException;
import com.campusdual.ejercicio5.exceptions.MaxFatsReachedException;
import com.campusdual.ejercicio5.exceptions.MaxProteinsReachedException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DietProgram {

    private Map<String, Diet> dietList;

    private List<Food> foodList;

    private List<Customer> customerList;

    public DietProgram(){

        foodList = new ArrayList<>();
        dietList = new HashMap<>();
        customerList = new ArrayList<>();
    }

    public void showMenuProgram(){
        System.out.println("########################################################");
        System.out.println("################# Programa de dietas ###################");
        System.out.println("########################################################");
        Integer option;
        do{
            System.out.println("Selecciona una opción:");
            System.out.println("===================================");
            System.out.println("1-Gestión de Dietas");
            System.out.println("2-Gestión de Pacientes");
            System.out.println("3-Salir del programa");
            option = Kb.getOption(1,3);
            switch (option){
                case 1:
                    dietManager();
                    break;
                case 2:
                    customerManager();
                    break;
                case 3:
                    System.out.println("Gracias por usar el programa, hasta pronto :)");
                    break;
            }
        }while(option != 3);
    }

    private void customerManager() {
        System.out.println("########################################################");
        System.out.println("################# Gestión de Pacientes ###################");
        System.out.println("########################################################");

        Integer option;
        do{
            System.out.println("Selecciona una opción:");
            System.out.println("1- Añadir Paciente");
            System.out.println("2- Mostrar detalles de un Paciente");
            System.out.println("3- Asignar dieta a un Paciente");
            System.out.println("4- Dar de baja un Paciente");
            option = Kb.getOption(1, 5);

            switch (option){
                case 1:
                    addCustomer();
                    break;
                case 2:
                    showCustomerDetails();
                    break;
                case 3:
                    assignDietToCustomer();
                    break;
                case 4:
                    deleteCustomer();
                    break;
            }
        }while(option != 5);
    }

    private void addCustomer() {
        System.out.println("###########################");
        System.out.println("Añadir Paciente");
        System.out.println("###########################");
        System.out.println("Introduce el nombre del paciente:");
        String name = Kb.nextLine();
        System.out.println("Introduce los apellidos:");
        String surname = Kb.nextLine();
        System.out.println("Introduce el peso:");
        Integer weight = Kb.forceNextInt();
        System.out.println("Introduce la altura:");
        Integer height = Kb.forceNextInt();
        System.out.println("Introduce la edad");
        Integer age = Kb.forceNextInt();
        String gender;
        do {
            System.out.println("Introduce el género (h/m):");
            gender = Kb.nextLine();
            if(Customer.validateGender(gender)){
                Customer newCustomer = new Customer(name, surname, weight, height, age, gender);
                customerList.add(newCustomer);
                System.out.println("Paciente añadido con éxito");
            }else{
                System.out.println("Se ha introducido un género incorrecto");
            }
        }while(!Customer.validateGender(gender));
    }//addCostumer()

    private void showCustomerDetails() {

        if(customerList.isEmpty()){
            System.out.println("La lista de pacientes está vacía. No se puede mostrar ningún elemento");
        }else{

        }
    }

    private void assignDietToCustomer() {
    }

    private void deleteCustomer() {
        if(customerList.isEmpty()){
            System.out.println("La lista de pacientes está vacía. No se puede eliminar ningún elemento");
            return;
        }else{
            Integer customerIndex = customerSelectList();
            customerList.remove(customerIndex);
        }
    }

    private Integer customerSelectList() {
        System.out.println("Selecciona un paciente de la lista:");

        int i=1;
        for(Customer customer:customerList){
            System.out.println(i + "- " + customer.getName());
            i++;
        }
        Integer selectedCustomerIndex = Kb.forceNextInt();

        return selectedCustomerIndex-1;
    }

    private void dietManager() {
        System.out.println("########################################################");
        System.out.println("################# Gestión de Dietas ###################");
        System.out.println("########################################################");

        Integer option;
        do{
            System.out.println("Selecciona una opción:");
            System.out.println("1- Agregar dieta");
            System.out.println("2- Mostrar detalles de la dieta");
            System.out.println("3- Modificar dieta");
            System.out.println("4- Eliminar dieta");
            System.out.println("5- Volver al menú primcipal");
            option = Kb.getOption(1, 5);

            switch (option){
                case 1:
                    createMenu();
                    break;
                case 2:
                    showDietDetails();
                    break;
                case 3:
                    modifyDietMenu();
                    break;
                case 4:
                    deleteDiet();
                    break;
            }
        }while(option != 5);

    }

    private void deleteDiet() {
        System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
        System.out.println("Eliminar una dieta");
        System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
        //TODO COMPROBAR QUE LA DIETA NO ESTÉ ASIGNADA A NINGÚN PACIENTE
        String selected = selectDiet();
        if(selected.equals(null)){
            return;
        }else{
            dietList.remove(selected);
        }
    }

    private void modifyDietMenu() {
        if(dietList.isEmpty()){
            System.out.println("Debes crear una dieta en primer lugar");
        }else{
            System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@");
            System.out.println("Modificar dieta");
            System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@");
            String selectedDietKey = selectDiet();

            //*Si nos devuelve null significa que el usuario desea volver
            if(selectedDietKey.equals(null)){
                System.out.println("Operación cancelada");
                return;
            }else{
                System.out.println("\nSelecciona una opción de la lista:");
                int option;

                do{
                    System.out.println("1- Modificar calorías máximas");
                    System.out.println("2- Modificar carbohidratos máximos");
                    System.out.println("3- Modificar grasas máximas");
                    System.out.println("4- Modificar proteínas máximas");
                    System.out.println("5- Gestión de Alimentos");
                    System.out.println("6- Salir");
                    option = Kb.getOption(1, 6);

                    switch(option){
                        case 1:
                            System.out.println("Indica el número de calorías máximas:");
                            int newMaxCalories = Kb.forceNextInt();
                            dietList.get(selectedDietKey).setMaxCalories(newMaxCalories);
                            break;
                        case 2:
                            System.out.println("Indica el número de carbohidratos máximo:");
                            int newMaxCarbs = Kb.forceNextInt();
                            dietList.get(selectedDietKey).setMaxCarbs(newMaxCarbs);
                            break;
                        case 3:
                            System.out.println("Indica el número de grasas máximas:");
                            int newMaxFats = Kb.forceNextInt();
                            dietList.get(selectedDietKey).setMaxFats(newMaxFats);
                            break;
                        case 4:
                            System.out.println("Indica el número de proteínas máximas");
                            int newMaxProteins = Kb.forceNextInt();
                            dietList.get(selectedDietKey).setMaxProteins(newMaxProteins);
                            break;
                        case 5:
                            System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@");
                            System.out.println("Gestión de alimentos");
                            System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@");
                            System.out.println("Selecciona una opción");
                            System.out.println("1- Añadir alimento a la dieta");
                            System.out.println("2- Eliminar un alimento de la dieta");
                            System.out.println("3- Salir");

                            int selectedOption = Kb.getOption(1, 3);

                            switch(selectedOption){
                                case 1:
                                    addFoodMenu(selectedDietKey);
                                    break;
                                case 2:
                                    deleteFood(selectedDietKey);
                                    break;
                            }
                    }
                }while(option != 6);
            }

        }

    }

    private void deleteFood(String selectedDietKey) {
        if(dietList.get(selectedDietKey).getIntakes() == null){
            System.out.println("Esta dieta no contiene ningún alimento");
        }else{
            System.out.println("Selecciona el alimento que deseas eliminar de la dieta:");

            int i = 1;
            for(Intake intake:dietList.get(selectedDietKey).getIntakes()){
                System.out.println(i + "- " + intake.getName());
                i++;
            }
            System.out.println(i + "- Salir");
            int option = Kb.getOption(1, i);
            if(option == i){
                return;
            }

            dietList.get(selectedDietKey).getIntakes().remove(option-1);
            System.out.println("El alimento ha sido eliminado");
        }
    }

    private void addFoodMenu(String selectedDietKey) {
        if(dietList.isEmpty()){
            System.out.println("Para agregar alimentos hace falta iniciar una dieta");
            return;
        }
        System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
        System.out.println("Agregar Alimentos a la dieta");
        System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
        System.out.println("Escriba una opción:");
        System.out.println("===================================");
        System.out.println("1-Agregar un nuevo alimento");
        System.out.println("2-Agregar un alimento ya existente");
        System.out.println("3- Volver");

        Integer option = Kb.getOption(1,3);
        switch (option){
            case 1:
                System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
                System.out.println("Datos de nuevo alimento");
                System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
                System.out.println("Nombre del alimento:");
                String name = Kb.nextLine();
                System.out.println("Carbohidratos:");
                Integer carbs = Kb.forceNextInt();
                System.out.println("Grasas:");
                Integer fats = Kb.forceNextInt();
                System.out.println("Proteínas:");
                Integer proteins = Kb.forceNextInt();
                System.out.println("Gramos:");
                Integer grams = Kb.forceNextInt();
                Food newFood = new Food(name,carbs,fats,proteins);
                validateAndAddFoodToDiet(newFood,grams, selectedDietKey);
                foodList.add(newFood);
                break;
            case 2:
                if(foodList.size()==0){
                    System.out.println("Para agregar un alimento existente, tienen que existir alimentos previos");
                    return;
                }
                System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
                System.out.println("Escoja un alimento");
                System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
                Integer i = 1;
                for(Food food:foodList){
                    System.out.println(i+"- "+food.getName());
                    i++;
                }
                System.out.println(i+"- Cancelar");
                Integer element = Kb.getOption(1,i);
                if(element==i){
                    System.out.println("Cancelando alimento");
                    return;
                }
                Food storedFood = foodList.get(element-1);
                System.out.println("indique el número de gramos de "+storedFood.getName());
                Integer foodGrams = Kb.forceNextInt();
                validateAndAddFoodToDiet(storedFood,foodGrams, selectedDietKey);
                break;
        }
    }

    private void validateAndAddFoodToDiet(Food food, Integer grams, String selectedDietKey){
        try{
            dietList.get(selectedDietKey).addFood(food, grams);
        }catch (MaxCaloriesReachedException ecal){
            System.out.println("Se ha alcanzado el máximo valor de calorías permitido");
        }catch (MaxCarbsReachedException ecar){
            System.out.println("Se ha alcanzado el máximo valor de carbohidratos permitido");
        }catch (MaxFatsReachedException efat){
            System.out.println("Se ha alcanzado el máximo valor de grasas permitido");
        }catch (MaxProteinsReachedException epro){
            System.out.println("Se ha alcanzado el máximo valor de proteínas permitido");
        }catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void createMenu() {
        System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
        System.out.println("Crear Dieta");
        System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
        System.out.println("Selecciona una opción:");
        System.out.println("===================================");
        System.out.println("1-Dieta sin límite");
        System.out.println("2-Dieta por calorías");
        System.out.println("3-Dieta por macronutrientes");
        System.out.println("4-Dieta por datos personales");
        Integer option = Kb.getOption(1,4);
        System.out.println("¿Qué nombre deseas ponerle a esta dieta?");
        String name = Kb.nextLine();
        switch (option){
            case 1:
                dietList.put(name, new Diet());
                System.out.println("Se ha creado una dieta sin límites");
                break;
            case 2:
                System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
                System.out.println("Escriba número de calorias");
                System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
                Integer calories = Kb.forceNextInt();
                dietList.put(name, new Diet(calories));
                System.out.println("Se ha creado una dieta con "+calories+" calorías máximas");
                break;
            case 3:
                System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
                System.out.println("Escriba los macronutrientes");
                System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
                System.out.println("Carbohidratos:");
                Integer carbs = Kb.forceNextInt();
                System.out.println("Grasas:");
                Integer fats = Kb.forceNextInt();
                System.out.println("Proteínas:");
                Integer proteins = Kb.forceNextInt();
                dietList.put(name, new Diet(carbs, fats, proteins));
                System.out.println("Se ha creado una dieta con Carbohidratos:"+carbs+", Grasas:"+fats+" ,Proteínas:"+proteins);
                break;
            case 4:
                //TODO CREAR PRIMERO CLASE COSTUMER Y LUEGO MODIFICAR ESTE MÉTODO
                /*System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
                System.out.println("Escriba los datos personales del paciente");
                System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
                System.out.println("Peso:");
                Integer weight = Kb.forceNextInt();
                System.out.println("Altura:");
                Integer height = Kb.forceNextInt();
                System.out.println("Edad:");
                Integer age = Kb.forceNextInt();
                System.out.println("Mujer u Hombre(m/h):");
                String sexCharacter = Kb.nextLine();
                this.diet = new Diet("m".equalsIgnoreCase(sexCharacter),age,height,weight);
                System.out.println("Se ha creado una dieta de "+this.diet.getMaxCalories()+" calorías máximas");
                break;*/
        }
    }
    
    private void showDietDetails(){
        if(!dietList.isEmpty()){
            System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
            System.out.println("Detalles de la dieta");
            System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
            String selected = selectDiet();
            //*Comprobamos que el usuario no haya seleccionado la opción de salir
            if(selected.equals(null)){
                return;
            }else{
                System.out.println("\nDieta seleccionada: "  + selected);
                if(dietList.get(selected).getMaxCalories() != null){
                    System.out.println("\nEl número máximo de calorías es:"+ dietList.get(selected).getMaxCalories());
                }
                if(dietList.get(selected).getMaxCarbs() != null || dietList.get(selected).getMaxFats() != null || dietList.get(selected).getMaxProteins() != null){
                    System.out.println("Los valores máximos de macronutrientes son: Carbohidratos:"+dietList.get(selected).getMaxCarbs()+" , Grasas:"+dietList.get(selected).getMaxFats()+" , Proteinas:"+dietList.get(selected).getMaxProteins());
                }
                System.out.println("Número de alimentos de la dieta:"+ dietList.get(selected).getFoodNumber());
                System.out.println("Calorías:"+ dietList.get(selected).getTotalCalories());
                System.out.println("Carbohidratos:"+ dietList.get(selected).getTotalCarbs());
                System.out.println("Grasas:"+ dietList.get(selected).getTotalFats());
                System.out.println("Proteínas:"+ dietList.get(selected).getTotalProteins());
                System.out.println("Alimentos de la dieta: "+ dietList.get(selected).getDietIntakes());
            }
        }else{
            System.out.println("No hay ninguna dieta dada de alta\nPor favor, crea una dieta en primer lugar.");
        }
    }//showDietDetails()

    private String selectDiet() {
        Integer selected = null;

        System.out.println("Selecciona una dieta de la lista:");
        //*Creamos un ArrayList para guardar las claves del hashmap de dietas
        ArrayList<String> keyList = new ArrayList<>(dietList.keySet());
        int i=0;
        //*Recorremos el hashmap para mostrar al usuario la lista de nombres de las dietas
        //*y le pedimos que nos indique el número para poder seleccionar la posición en el arraylist de keys
        for(String key: dietList.keySet()){
            System.out.println((i+1) + ". " + key);
            i++;
        }
        System.out.println((i+1) + ". Salir");
        selected = Kb.getOption(1, i+1);

        if(selected == i+1){
            return null;
        }
        String selectedKey = keyList.get(selected-1);
        return selectedKey;
    }//selectDiet()
    
}
