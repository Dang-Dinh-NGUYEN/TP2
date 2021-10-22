import java.util.Arrays;
import java.util.Objects;
import java.lang.Math;
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
        return (lastLine + 1 - firstLine);
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
        int subDimension = lastLine - firstLine + 1;
        //int subDimensionD2 = lastColumn - firstColumn + 1;
        //int subDimension = Math.max(subDimensionD1,subDimensionD2);
        int[][] nMatrix = new int[subDimension][subDimension];
        for (int i = 0; i < subDimension; i++) {
            for (int j = 0; j < subDimension; j++) {
                nMatrix[i][j] = this.get(i + firstLine, j + firstColumn);
            }
        }
        return new squareSubMatrice(subDimension,firstLine,firstColumn,lastLine,lastColumn,nMatrix);
    }

    public int get(int row, int col) {
        return this.matrix[firstLine + row][firstColumn + col];
    }

    public void set(int row, int col, int value) {
        matrix[firstLine + row][firstColumn + col] = value;
    }

    public String toString() {
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < this.getSubDimension(); i++) {
            str.append('\n');
            for (int j = 0; j < this.getSubDimension(); j++) {
                str.append(this.get(i,j) + " ");
            }
        }
        return str.toString();
    }

    public void sum(squareSubMatrice subMatrice) {
        for (int i = 0; i < this.getDimension(); i++) {
            for (int j = 0; j < this.getDimension(); j++) {
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
        while(n > 1){
        this.product(this.clone());
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
            squareSubMatrice clA = this.clone();
            squareSubMatrice clB = subMatrice.clone();
            int newDimension = clA.dimension / 2;
            int newFirstLine = clA.firstLine % 2;
            int newLastLine =  clA.lastLine % 2;
            int newFirstColumn = clA.firstColumn % 2;
            int newLastColumn = clA.lastColumn % 2;

            squareSubMatrice A11 = clA.localisedClone(newFirstLine, newDimension-1, newFirstColumn, newDimension-1);
            System.out.println(A11.toString());
            squareSubMatrice B11 = clB.localisedClone(newFirstLine, newDimension-1, newFirstColumn, newDimension-1);
            System.out.println(B11.toString());

            squareSubMatrice A12 = clA.localisedClone(newFirstLine, newDimension-1, newDimension, newLastColumn);
            System.out.println(A12.toString());
            squareSubMatrice B12 = clB.localisedClone(newFirstLine, newDimension-1, newDimension, newLastColumn);
            System.out.println(B12.toString());

            squareSubMatrice A21 = clA.localisedClone(newDimension, newFirstLine, newFirstColumn, newDimension);
            System.out.println(A21.toString());
            squareSubMatrice B21 = clB.localisedClone(newDimension, newFirstLine, newFirstColumn, newDimension);
            System.out.println(B21.toString());
            squareSubMatrice A22 = clA.localisedClone(newDimension, newFirstLine, newDimension, newLastColumn);
            System.out.println(A22.toString());
            squareSubMatrice B22 = clB.localisedClone(newDimension, newFirstLine, newDimension, newLastColumn);
            System.out.println(B22.toString());

            //A11.quickProduct(B11.clone());
            //A12.quickProduct(B12.clone());

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


