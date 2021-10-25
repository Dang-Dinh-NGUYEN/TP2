import java.util.Random;


public class Main {
    public static void main(String[] Ags) {
        Random rand = new Random();
        int[][] matrix = new int[4][4];
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                matrix[i][j] = 1;
            }
        }
        squareSubMatrice matrice = new squareSubMatrice(4, 0, 0, 3, 3, matrix);

        int[][] subMatrix = new int[4][4];
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                subMatrix[i][j] = rand.nextInt(10);
            }
        }
        squareSubMatrice subMatrice = new squareSubMatrice(4, 0, 0, 3, 3, subMatrix);

        System.out.println(matrice.toString());
        //System.out.println(matrice.equals(matrice.clone()));

        System.out.println(subMatrice.toString());
        //System.out.println(subMatrice.localisedClone(0,2,0,2).toString());


        //matrice.sum(subMatrice);
        //matrice.product(subMatrice);
        matrice.quickProduct(subMatrice);
        //matrice.power(2);
        //matrice.quickPower(3);
        System.out.println(matrice.toString());

            }
        }

