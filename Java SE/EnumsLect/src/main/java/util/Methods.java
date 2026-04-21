package util;

public class Methods {
    public static void method(AutoType autoType){
        if(autoType == AutoType.PASSENGER){
            System.out.println("This is passenger car");
        }
        else if(autoType == AutoType.FREIGHT){
            System.out.println("This is freight car");
        }
        else if(autoType == AutoType.RACE){
            System.out.println("This is race car");
        }
        else{
            System.out.println("This is unknown car");
        }
    }
}
