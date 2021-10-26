import java.util.Random;


public class Main {
    public static void main(String[] Ags) {
        Random rand = new Random();
        int[][] matrix = new int[2][2];
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                matrix[i][j] = rand.nextInt(10);
            }
        }
        squareSubMatrice matrice = new squareSubMatrice(2, 0, 0, 1, 1, matrix);

        int[][] subMatrix = new int[2][2];
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                subMatrix[i][j] = rand.nextInt(10);
            }
        }
        squareSubMatrice subMatrice = new squareSubMatrice(2, 0, 0, 1, 1, subMatrix);

        System.out.println(matrice.toString());
        //System.out.println(matrice.equals(matrice.clone()));

        System.out.println(subMatrice.toString());
        //System.out.println(subMatrice.localisedClone(0,2,0,2).toString());
        //matrice.sum(subMatrice);
        //matrice.product(subMatrice);
        //matrice.join(subMatrice,0,0);
        //matrice.quickProduct(subMatrice);
        //matrice.power(2);
        //matrice.quickPower(3);
        matrice.veryQuickPower(4);
        System.out.println(matrice.toString());

            }
        }

