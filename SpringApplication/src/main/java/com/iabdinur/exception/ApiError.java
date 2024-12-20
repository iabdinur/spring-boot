package com.iabdinur.exception;

import java.time.ZonedDateTime;
import java.util.List;

public record ApiError(
        String path,
        String message,
        int StatusCode,
        ZonedDateTime zonedDateTime,
        List<String> errors
) {
}
