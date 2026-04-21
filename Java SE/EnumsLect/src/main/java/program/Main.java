package program;

import util.AutoType;
import util.Methods;

public class Main {
    public static void main(String[] args) {
        Methods.method(AutoType.PASSENGER);
        Methods.method(AutoType.FREIGHT);
        Methods.method(AutoType.RACE);

        AutoType type = AutoType.FREIGHT;
        System.out.println(type);

        AutoType res = AutoType.valueOf("PASSENGER");
        System.out.println(res);
    }
}
