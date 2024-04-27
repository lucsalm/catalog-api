package com.lucsalmd.catalogapi.model.dto;

import jakarta.validation.constraints.NotNull;

public record CategoryRequestDTO(@NotNull String title, @NotNull String description) {
}
