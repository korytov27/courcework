package workflow;

import matrix.Matrix;

import java.io.IOException;
import java.util.Random;
import java.util.Scanner;
import java.util.function.Function;


/**
 * @author Dmitry Korytov
 */
public class WorkFlow<T> {

    private Class<T> clazz;
    private Scanner scanner = new Scanner(System.in);
    private Function<Random, T> creator;
    private Matrix<T> matrix = null;

    private Integer rowIndex;
    private Integer columnIndex;
    private Boolean isDone = false;


    public WorkFlow(Class<T> clazz, Function<Random, T> creator) {
        this.clazz = clazz;
        this.creator = creator;
    }

    public void perform(){
        setupMatrix();

        while (!isDone){
            showChoiceTable();
            Integer number = scanner.nextInt();
            choice(number);
        }
    }

    private void showChoiceTable(){
        System.out.println("1 - Show matrix.");
        System.out.println("2 - Set value by index.");
        System.out.println("3 - Get value by index.");
        System.out.println("4 - Get number of nulls in row.");
        System.out.println("5 - Get number of nulls in column.");
        System.out.println("6 - Get number of not empty cells.");
        System.out.println("7 - Get number of null cells.");
        System.out.println("8 - Get size of matrix.");
        System.out.println("9 - Clear console.");
        System.out.println("10 - Exits.");
    }


    private void choice(Integer numberOfChoice){

        switch (numberOfChoice){

            case 1 : {
                showMatrix();
                break;
            }

            case 2 : {
                setValue();
                break;
            }

            case 3 : {
                getValue();
                break;
            }

            case 4 : {
                getNumberOfNullsInRow();
                break;
            }

            case 5: {
                getNumberOfNullsInColumn();
                break;
            }

            case 6 : {
                getNumberNoneEmptyCells();
                break;
            }

            case 7 : {
                getNumberOfNullCells();
                break;
            }

            case 8 : {
                getSize();
                break;
            }

            case 9 : {
                clear();
                break;
            }

            case 10 : {
                exit();
            }

        }
    }


    private void setupMatrix(){
        System.out.println("Input row number: ");
        Integer rowNumber =  scanner.nextInt();

        System.out.println("Input column number: ");
        Integer columnNumber = scanner.nextInt();

        matrix = new Matrix<T>(clazz, rowNumber, columnNumber);
        matrix.setMatrix(creator);
    }


    // case 1
    private void showMatrix(){
        matrix.showMatrix();
    }


    // case 2
    @SuppressWarnings("unchecked")
    private void setValue(){
        System.out.println("Input row index: ");
        rowIndex = scanner.nextInt();

        System.out.println("Input column index: ");
        columnIndex = scanner.nextInt();

        System.out.println("Input value index: ");
        T value = (T) scanner.next();

        matrix.setValueByIndex(rowIndex,columnIndex, value);
    }


    // case 3
    private void getValue(){
        System.out.println("Input row index: ");
        rowIndex = scanner.nextInt();

        System.out.println("Input column index");
        columnIndex = scanner.nextInt();

        matrix.getValueByIndex(rowIndex,columnIndex);
    }


    // case 4
    private void getNumberOfNullsInRow(){
        System.out.println("Input row index: ");
        rowIndex = scanner.nextInt();

        System.out.println(matrix.getNumbersOfNullInRow(rowIndex));
    }


    // case 5
    private void getNumberOfNullsInColumn(){
        System.out.println("Input column index");
        columnIndex = scanner.nextInt();

        System.out.println(matrix.getNumberOfNullInColumn(columnIndex));
    }


    // case 6
    private void getNumberNoneEmptyCells(){
        System.out.println(matrix.getNumberOfNotNullCells());
    }



    // case 7
    private void getNumberOfNullCells(){
        System.out.println(matrix.getNumberOfNullCells());
    }


    // case 8
    private void getSize(){
        System.out.println(matrix.getSizeOfMatrix());
    }


    // case 9
    private void clear(){
        try {
            Runtime.getRuntime().exec("clr");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    // case 10
    private void exit(){
        isDone = true;
        System.exit(2);
    }

}
