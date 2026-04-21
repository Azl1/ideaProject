package abdullaevaziz.programm;

import abdullaevaziz.model.LoaderService;



public class Main {
    /*public static void progressPercentage(long remain, long total) {
        if (remain > total) {
            throw new IllegalArgumentException();
        }
        int maxBareSize = 10; // 10unit for 100%
        int remainProcent = (int) ((100 * remain) / total) / maxBareSize;
        char defaultChar = '-';
        String icon = "*";
        String bare = new String(new char[maxBareSize]).replace('\0', defaultChar) + "]";
        String bareDone = "[" + icon.repeat(remainProcent);
        String bareRemain = bare.substring(remainProcent);
        System.out.print("\r" + bareDone + bareRemain + " " + remainProcent * 10 + "%");
        if (remain == total) {
            System.out.print("\n");
        }
    }*/

    public static void main(String[] arg) {
        try {
            LoaderService loaderService =
               new LoaderService("https://drive.google.com/uc?id=1P0_XKLUfOsGN59em8tFXc5GItil_Grw1&export=download", "Airlines.rar", "stat.txt");
            loaderService.load();

            System.out.println();
        } catch (Exception e) {

        }

    }


}