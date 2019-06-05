package net.ijiangtao.tech.demo.retryer.guava.exception;

/**
 * 当抛出这个异常的时候，表示需要重试
 */
public class NeedRetryException extends Exception {

    public NeedRetryException(String message) {
        super("NeedRetryException can retry."+message);
    }

}
