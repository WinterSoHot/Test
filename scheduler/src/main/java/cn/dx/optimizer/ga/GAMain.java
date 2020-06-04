package cn.dx.optimizer.ga;

public class GAMain {

    public static void main(String[] args) {
        GA ga = new GA(10, 300, 1, 0.015);
        // -(x^2+2x+1)
//        ga.init(1,-2,2, A -> -(Math.pow(A[0], 2) + 3 * A[0] + 1));
        ga.init(2,-2,2, A -> (Math.cos(A[0]) + Math.sin(A[1])));
        ga.start();
        for (double [] item : ga.oldPopulation){
            for (int i = 0; i < item.length; i++) {
                System.out.print(item[i]+" ");
            }
            System.out.println();
        }
    }
}
