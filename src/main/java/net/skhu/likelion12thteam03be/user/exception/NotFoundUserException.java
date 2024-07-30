package net.skhu.likelion12thteam03be.user.exception;

public class NotFoundUserException extends RuntimeException{
    public NotFoundUserException(final String message) {
       super(message);
    }

    public NotFoundUserException() {
        this("회원을 찾을 수 없습니다.");
    }
}
