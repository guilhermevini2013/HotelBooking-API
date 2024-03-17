package org.guilhermedev.hotelbooking.controllers.exception;

import java.time.Instant;
import java.util.List;

public record ErrorModel(Instant timestamp, Integer status, List<String> errors, String path) {
}
