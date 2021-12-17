package dyve.aoc2021.day.day17;

import dyve.aoc2021.day.Part;
import dyve.aoc2021.input.InputReader;

public class Part2 extends Part {

    public static void main(String[] args) throws Exception {
        new Part2().subMain(16);
    }

    @Override
    public Object execute(InputReader inputReader) {
        double txmin = 277d;
        double txmax = 318d;
        double tymax = -53d;
        double tymin = -92d;
//        double txmin = 20d;
//        double txmax = 30d;
//        double tymax = -5d;
//        double tymin = -10d;

        int Dxmin = (int) Math.ceil((-1d + Math.sqrt(8d*txmin))/2d);
//        System.out.println("Dxmin = " + Dxmin);
//        System.out.println("Dxmax = " + txmax);

        int Dymin = (int) tymin;
//        System.out.println("Dymin = " + Dymin);
        int Dymax = (int) (-tymin);
//        System.out.println("Dymax = " + Dymax);

        int nb = 0;

        for(int Dx0 = Dxmin; Dx0 <= txmax; Dx0++) {
            int nmin, nmax;
            double deltaMax = Math.pow(2d * Dx0 + 1d, 2d) - 8d * txmax;
            double deltaMin = Math.pow(2d * Dx0 + 1d, 2d) - 8d * txmin;
            nmin = (int) Math.ceil(((2d * Dx0 + 1d) - Math.sqrt(deltaMin)) / 2d);
            if(deltaMax < 0){
                nmax = Integer.MAX_VALUE;
            }else{
                nmax = (int) Math.floor(((2d * Dx0 + 1d) - Math.sqrt(deltaMax)) / 2d);
            }
//            System.out.println("nmin = " + nmin);
//            System.out.println("nmax = " + nmax);
//            System.out.println();

            loop:for(int Dy0 = Dymin; Dy0 <= Dymax; Dy0++){
                int n = 0;
                int Dy = Dy0;
                int Dx = Dx0;
                int y = 0;
                int x = 0;
                while(y > tymin){
                    n++;
                    y += Dy;
                    Dy--;
                    x += Dx;
                    Dx = Dx > 0 ? Dx - 1 : 0;
                    if(y >= tymin && y <= tymax && x <= txmax && x >= txmin){
                        nb++;
                        System.out.println("Dx0 = " + Dx0);
                        System.out.println("Dy0 = " + Dy0);
                        System.out.println("n = " + n);
                        System.out.println("x = " + x);
                        System.out.println("y = " + y);
                        System.out.println();
                        continue loop;
                    }
                }
            }
            System.out.println("--------------------------");
        }

        return nb;
    }

}