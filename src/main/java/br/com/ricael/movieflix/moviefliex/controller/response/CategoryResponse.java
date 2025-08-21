package br.com.ricael.movieflix.moviefliex.controller.response;

import lombok.Builder;

@Builder
public record CategoryResponse(Long id, String name) {
}
