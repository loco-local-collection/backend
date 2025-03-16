package com.loco.platform.exception;

public class RoomException extends CustomException {

    public RoomException(ErrorCode errorCode) {
        super(errorCode);
    }
}
