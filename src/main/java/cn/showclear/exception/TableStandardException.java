package cn.showclear.exception;

/**
 * 非标准表格错误
 * 即输入的表格不符合标准规范
 */
public class TableStandardException extends Exception {

    /**
     * Constructs a new exception with {@code null} as its detail message.
     * The cause is not initialized, and may subsequently be initialized by a
     * call to {@link #initCause}.
     */
    public TableStandardException() {
        super("输入的表格不符合规范");
    }


    /**
     * Constructs a new exception with the specified detail message.  The
     * cause is not initialized, and may subsequently be initialized by
     * a call to {@link #initCause}.
     *
     * @param message the detail message. The detail message is saved for
     *                later retrieval by the {@link #getMessage()} method.
     */
    public TableStandardException(String message) {
        super(message);
    }
}
