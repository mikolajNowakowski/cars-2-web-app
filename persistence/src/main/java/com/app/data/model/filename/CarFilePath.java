package com.app.data.model.filename;

import lombok.Data;
import lombok.RequiredArgsConstructor;



public record CarFilePath(String path) implements DataSource {
}
