package net.skhu.likelion12thteam03be.user.exception;

public class InvalidUserException extends RuntimeException{
    public InvalidUserException(final String message) {
        super(message);
    }

    public InvalidUserException() {
        this("잘못된 회원의 정보입니다.");
    }
}
