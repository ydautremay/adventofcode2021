package dyve.aoc2021.day.day17;

import dyve.aoc2021.day.Part;
import dyve.aoc2021.input.InputReader;

import java.util.List;

public class Part1 extends Part {

    public static void main(String[] args) throws Exception {
        new Part1().subMain(17);
    }

    @Override
    public Object execute(InputReader inputReader) {
        double txmin = 277d;
        double txmax = 318d;
        double tymin = 53d;
        double tymax = 92d;
//        double txmin = 20d;
//        double txmax = 30d;
//        double tymin = 5d;
//        double tymax = 10d;


        int xmin = (int) Math.ceil((-1d + Math.sqrt(1d + 4d * 2d * txmin)) / 2);
        int xmax = (int) Math.floor((-1d + Math.sqrt(1d + 4d * 2d * txmax)) / 2);

        System.out.println(xmin);
        System.out.println(xmax);

        //24 it is !
        //Pour un Dy donné, on monte jusque Dy(Dy + 1)/2 pui on retourne à l'altitude 0 avec Dy = -DyDonné
        //Au step n après le retour à 0, la position y est -(Dy + n)(Dy + n + 1)/2 + Dy(Dy + 1)/2
        //On retourne à zéro après 2*Dy + 1 steps.

        double Dy = 11d;
        double nmin;
        double nmax;

        nmin = (-(2d*Dy - 1d) + Math.sqrt(Math.pow(2d*Dy - 1d, 2d) + 8d * tymin)) / 2d;
        nmax = (-(2d*Dy - 1d) + Math.sqrt(Math.pow(2d*Dy - 1d, 2d) + 8d * tymax)) / 2d;

        System.out.println(nmin);
        System.out.println(nmax);



        double fleche = tymax * (tymax - 1) / 2;

        return fleche;
    }

}