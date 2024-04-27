package com.lucsalmd.catalogapi.model.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record ProductRequestDTO(@NotNull String title, @Positive Integer price, String categoryId, @NotNull String description) {
}
