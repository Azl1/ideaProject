package com.kirillkotov.program;

import java.util.Arrays;

public class Main {
    /**
     * Work option handler
     */
    private static void workOption(){

    }

    /**
     * Util option handler
     */
    private static void utilOption(){

    }

    /**
     * Generic option handler
     */
    private static void genericOption(){

    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(args));
        if(args.length != 0){
            //TODO handle arguments
            if(args[0].equals("-help")){
                System.out.print("You input help ");
                if(args.length > 1){
                    if(args[1].equals("-work")){
                        System.out.println("work");
                        //TODO invoke work option handler method
                    }
                    else if(args[1].equals("-util")){
                        System.out.println("util");
                        //TODO invoke util option handler method
                    }
                    else {
                        System.out.println("Unknown operation");
                    }
                }
            }
            else if(args[0].equals("-generic")){
                System.out.print("You input generic ");
                //TODO handle generic option
            }
            else{
                System.out.println("Unknown operation");
            }
        }
    }
}