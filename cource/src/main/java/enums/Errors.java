package enums;

public enum Errors {

    WrongSizeParams("Wrong params! Row number should be equals to column number."),
    NullCellValue("This cell contains null value!"),
    WriteMessage("Can`t write message in log file.");

    private String errorMessage;

    Errors(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
