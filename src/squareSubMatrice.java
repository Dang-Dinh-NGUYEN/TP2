import java.util.Arrays;
import java.util.Objects;
import java.math.*;

public class squareSubMatrice {
    private int dimension;
    private int[][] matrix;
    private int firstLine;
    private int firstColumn;
    private int lastLine;
    private int lastColumn;

    public squareSubMatrice(int dimension, int firstLine, int firstColumn, int lastLine,int lastColumn, int[][] matrix) {
        this.dimension = dimension;
        this.firstLine = firstLine;
        this.firstColumn = firstColumn;
        this.lastLine = lastLine;
        this.lastColumn = lastColumn;
        this.matrix = matrix;
    }

    public int getDimension() {
        return this.dimension;
    }

    public int getSubDimension() {
        return (this.getLastLine() + 1 - this.getFirstLine());
    }

    public int getFirstLine() {
        return firstLine;
    }

    public int getFirstColumn() {
        return firstColumn;
    }

    public int getLastLine() {
        return lastLine;
    }

    public int getLastColumn() {
        return lastColumn;
    }

    public squareSubMatrice clone() {
        int[][] nMatrix = new int[dimension][dimension];
        for (int i = 0; i < dimension; i++) {
            for(int j = 0; j < dimension; j++) {
                nMatrix[i][j] = this.matrix[i][j];
            }
        }
        return new squareSubMatrice(dimension, firstLine, firstColumn, lastLine, lastColumn, nMatrix);
    }

    public squareSubMatrice localisedClone(int firstLine,int lastLine,int firstColumn,int lastColumn) {
        squareSubMatrice cl = this.clone();
        int subDimension = lastLine - firstLine + 1;
        int[][] nMatrix = new int[subDimension][subDimension];
        for (int i = 0; i < subDimension; i++) {
            for (int j = 0; j < subDimension; j++) {
                nMatrix[i][j] = cl.get(i + firstLine,j + firstColumn);
            }
        }
        return new squareSubMatrice(subDimension,0,0,subDimension-1,subDimension-1,nMatrix);
    }

    public int get(int row, int col) {
        return this.matrix[firstLine + row][firstColumn + col];
    }

    public void set(int row, int col, int value) {
        matrix[firstLine + row][firstColumn + col] = value;
    }


    public String toString() {
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < this.getDimension(); i++) {
            str.append('\n');
            for (int j = 0; j < this.getDimension(); j++) {
                str.append(this.get(i, j)).append(" ");
            }
        }
        return str.toString();
    }


    public void sum(squareSubMatrice subMatrice) {
        for (int i = 0; i < subMatrice.getDimension(); i++) {
            for (int j = 0; j < subMatrice.getDimension(); j++) {
                int value = this.get(i, j) + subMatrice.get(i, j);
                this.set(i, j, value);
            }
        }
    }

    public void product(squareSubMatrice subMatrice) {
        squareSubMatrice cl = this.clone();
        int value;
        for (int i = 0; i < subMatrice.getDimension(); i++) {
            for (int j = 0; j < subMatrice.getDimension(); j++) {
                int sum = 0;
                for (int k = 0; k < subMatrice.getDimension(); k++) {
                    sum += cl.get(i, k) * subMatrice.get(k, j);
                }
                value = sum;
                this.set(i, j, value);
            }
        }
    }

    public void power(int n) {
        if (n == 0 || n == 1) {
            return ;
        }
        /**
         * en utilisant itération:
        squareSubMatrice cl = this.clone();
        while(n > 1){
        this.product(cl);
        n--;
        }
         **/
        squareSubMatrice cl = this.clone();
        cl.power(n-1);
        this.product(cl);
    }

    public void quickPower(int n){
        if (n == 0 || n == 1) {
            return;
        }
        if(n > 1 && n % 2 == 0){
            this.product(this.clone());
            this.power(n/2);
        }
        else{
            squareSubMatrice cl = this.clone();
            this.product(this.clone());
            this.power((n-1)/2);
            this.product(cl);
        }
    }

    public void quickProduct(squareSubMatrice subMatrice){
        if((this.getDimension() == subMatrice.getDimension()) && (this.getDimension() % 2 == 1)){
            this.product(subMatrice);
        }
        else {
            int newDimension = this.dimension / 2;
            int newFirstLine = this.firstLine ;
            int newLastLine =  this.lastLine ;
            int newFirstColumn = this.firstColumn;
            int newLastColumn = this.lastColumn ;

            //divise la matrice initiale aux 4 sous-matrices de dimension n/2 (où n est la dimension de la matrice initiale)

            squareSubMatrice A11 = this.localisedClone(newFirstLine, newDimension-1, newFirstColumn, newDimension-1);
            squareSubMatrice B11 = subMatrice.localisedClone(newFirstLine, newDimension-1, newFirstColumn, newDimension-1);
            squareSubMatrice A12 = this.localisedClone(newFirstLine, newDimension-1, newDimension, newLastColumn);
            squareSubMatrice B12 = subMatrice.localisedClone(newFirstLine, newDimension-1, newDimension, newLastColumn);
            squareSubMatrice A21 = this.localisedClone(newDimension, newLastLine, newFirstColumn, newDimension - 1);
            squareSubMatrice B21 = subMatrice.localisedClone(newDimension, newLastLine, newFirstColumn, newDimension - 1);
            squareSubMatrice A22 = this.localisedClone(newDimension, newLastLine, newDimension, newLastColumn);
            squareSubMatrice B22 = subMatrice.localisedClone(newDimension, newLastLine, newDimension, newLastColumn);

            //ajoute les copies des sous-matrices
            squareSubMatrice A11c = A11.clone();
            squareSubMatrice B11c = B11.clone();
            squareSubMatrice A12c = A12.clone();
            squareSubMatrice B12c = B12.clone();
            squareSubMatrice A21c = A21.clone();
            squareSubMatrice B21c = B21.clone();
            squareSubMatrice A22c = A22.clone();
            squareSubMatrice B22c = B22.clone();

            //les calculs sur les sous-matrices
            //C11 = quickProduit(A11,B11) + quickProduit(A12,B21)
            A11c.quickProduct(B11c);
            A12c.quickProduct(B21c);
            A11c.sum(A12c);
            this.set(newFirstLine,newFirstColumn,A11c.get(newFirstLine,newFirstColumn));


            //C12 = quickProduit(A11,B12) + quickProduit(A12,B22)
            A11.quickProduct(B12);
            A12.quickProduct(B22);
            A11.sum(A12);
            //this.set(newFirstLine,newDimension,A11.get(newFirstLine,newFirstColumn));

            //C21 = quickProduit(A21,B11) + quickProduit(A22,B21)
            A21c.quickProduct(B11c);
            A22c.quickProduct(B21c);
            A21c.sum(A22c);
            //this.set(newDimension,newFirstColumn,A21c.get(newFirstLine,newFirstColumn));

            //C22 = quickProduit(A21,B12) + quickProduit(A22,B22)
            A21.quickProduct(B12c);
            A22.quickProduct(B22c);
            A21.sum(A22);
            //this.set(newDimension,newDimension,A21.get(newFirstColumn,newFirstColumn));

        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        squareSubMatrice that = (squareSubMatrice) o;
        return dimension == that.dimension && firstLine == that.firstLine && firstColumn == that.firstColumn && lastLine == that.lastLine && lastColumn == that.lastColumn && Arrays.equals(matrix, that.matrix);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(dimension, firstLine, firstColumn, lastLine, lastColumn);
        result = 31 * result + Arrays.hashCode(matrix);
        return result;
    }
}


