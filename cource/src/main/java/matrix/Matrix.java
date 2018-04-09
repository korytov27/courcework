package matrix;

import enums.Errors;
import utils.CustomLogger;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Objects;
import java.util.Random;
import java.util.function.Function;


/**
 * @author Dmitry Korytov
 */
public class Matrix<T> implements Iterable<T> {

    private T [][] array;

    private Integer rowNumber;
    private Integer columnNumber;


    @SuppressWarnings("unchecked")
    public Matrix(Class<T> clazz, Integer rowNumber, Integer columnNumber) {
        if (!rowNumber.equals(columnNumber)){
            CustomLogger.error(String.format("Receive wrong size params rowNum = %s columnNum = %s .", rowNumber, columnNumber));
            throw new AssertionError(Errors.WrongSizeParams.getErrorMessage());
        }

        this.rowNumber = rowNumber;
        this.columnNumber = columnNumber;

        this.array = (T[][]) Array.newInstance(clazz, rowNumber, columnNumber);
    }



    /**
     * This method set values in our matrix by task №6
     *
     *  * - null value
     *  1 - our value
     *
     *     *    *    *    *    *
     *     1    *    *    *    *
     *     1    1    *    *    *
     *     1    1    1    *    *
     *     1    1    1    1    *
     *
     */
    public void setMatrix(Function<Random, T> creator){
        CustomLogger.info("Set matrix by values");
        Random random = new Random();
        random.setSeed(System.currentTimeMillis());

        for (int row = 0; row < rowNumber; row++) {
            for (int column = 0; column < columnNumber; column++) {

                if (row > column){
                    array[row][column] = creator.apply(random);
                }
            }
        }

        showMatrix();
    }



    /**
     * This method show matrix with formatting
     */
    public void showMatrix(){
        CustomLogger.info("Show matrix");

        for (Integer row = 0; row < rowNumber; row++) {
            for (Integer column = 0; column < columnNumber; column++) {
                System.out.printf("%5s", array[row][column]);
            }
            System.out.println();
        }
        System.out.println();
    }




    /**
     * This method need to set value by index
     * @param rowIndex number of row
     * @param columnIndex number of column
     * @param value number what u wanna to set
     */
    public void setValueByIndex(Integer rowIndex, Integer columnIndex, T value){

        CustomLogger.debug(String.format("Try set %s by rowIndex = %s columnIndex =%s .", value, rowIndex, columnIndex));

        if (array[rowIndex][columnIndex] == null){
            array[rowIndex][columnIndex] = value;
        } else {

            CustomLogger.error(String.format("Can`t set %s by rowIndex = %s columnIndex =%s .", value, rowIndex, columnIndex));
            throw new AssertionError(Errors.NullCellValue.getErrorMessage());
        }

        showMatrix();
    }



    /**
     * This method show value by rowIndex and columnIndex
     * @param rowIndex
     * @param columnIndex
     */
    public void getValueByIndex(Integer rowIndex, Integer columnIndex){

        CustomLogger.debug(String.format("Try get value by rowIndex = %s columnIndex =%s ", rowIndex, columnIndex));

        if (array[rowIndex][columnIndex] != null){

            CustomLogger.info(String.format("array[%s][%s] = %s", rowIndex, columnIndex, array[rowIndex][columnIndex]));
            System.out.println(String.format("array[%s][%s] = %s", rowIndex, columnIndex, array[rowIndex][columnIndex]));
        } else {

            CustomLogger.error(String.format("Can`t get value by rowIndex = %s columnIndex =%s ", rowIndex, columnIndex));
            throw new AssertionError(Errors.NullCellValue.getErrorMessage());
        }
    }



    public Integer getNumbersOfNullInRow(Integer rowIndex){
        return Math.toIntExact(Arrays.stream(array[rowIndex]).filter(Objects::nonNull).count());
    }


    public Integer getNumberOfNullInColumn(Integer columnIndex){
        return Math.toIntExact(Arrays.stream(array).map(ts -> ts[columnIndex]).filter(Objects::nonNull).count());
    }


    public Integer getNumberOfNotNullCells(){
        Integer countOfNotEmptyFields = 0;

        for (T t : this) {
            countOfNotEmptyFields++;
        }

        return countOfNotEmptyFields;
    }

    public String getSizeOfMatrix(){
        return String.format("Size of matrix %sx%s", rowNumber, columnNumber);
    }

    public Integer getNumberOfNullCells(){
        return rowNumber*columnNumber - getNumberOfNotNullCells();
    }


    @Override
    public Iterator<T> iterator() {
        return new CustomIterator();
    }



    class CustomIterator implements Iterator<T> {

        private Integer currentRow = 0;
        private Integer currentColumn = 0;

        @Override
        public boolean hasNext() {
            return currentRow < array.length - 1  && currentColumn < array[currentRow].length - 1;
        }

        @Override
        public T next() {

            if (!hasNext()) {
                return null;
            }

            while (currentRow < array.length){

                while (currentColumn < array[currentRow].length){

                    if (array[currentRow][currentColumn] != null){

                        T value = array[currentRow][currentColumn];
                        currentColumn++;
                        resetIndex();

                        return value;
                    } else {
                        currentColumn ++;
                        resetIndex();
                    }
                }

                currentRow++;
            }

            return null;
        }


        private void resetIndex(){
            if (currentColumn.equals(array[currentRow].length)){

                currentColumn = 0;
                currentRow++;
            }
        }


    }
}
