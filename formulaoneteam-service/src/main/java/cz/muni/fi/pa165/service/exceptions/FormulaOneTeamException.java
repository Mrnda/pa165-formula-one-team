package cz.muni.fi.pa165.service.exceptions;

public class FormulaOneTeamException  extends  RuntimeException {
    public FormulaOneTeamException(String message) {
        super(message);
    }

    public FormulaOneTeamException(String message, Throwable cause) {
        super(message, cause);
    }
}
