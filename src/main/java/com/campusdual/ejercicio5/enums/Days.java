package com.campusdual.ejercicio5.enums;

public enum Days {

    L(1, "Lunes"),
    M(2, "Martes"),
    X(3, "Miércoles"),
    J(4, "Jueves"),
    V(5, "Viernes"),
    S(6, "Sábado"),
    D(7, "Domingo");

    private Integer position;
    private String name;

    Days(Integer position, String name){
        this.position = position;
        this.name = name;
    }

    public Integer getPosition() {
        return position;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static Days selectDay(String day){

        Days selectedDay = null;

        while(selectedDay == null){
            switch(day.toUpperCase()){

                case "LUNES":
                    selectedDay = Days.L;
                    break;
                case "MARTES":
                    selectedDay = Days.M;
                    break;
                case "MIERCOLES":
                    selectedDay = Days.X;
                    break;
                case "JUEVES":
                    selectedDay = Days.J;
                    break;
                case "VIERNES":
                    selectedDay = Days.V;
                    break;
                case "SABADO":
                    selectedDay = Days.S;
                    break;
                case "DOMINGO":
                    selectedDay = Days.D;
                    break;
                default:
                    System.out.println("El día introducido no es válido. Por favor, introduce uno correcto");
                    break;
            }
        }
        return selectedDay;
    }
}
