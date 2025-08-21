package br.com.ricael.movieflix.moviefliex.controller.response;

import lombok.Builder;

@Builder
public record StreamingResponse(Long id, String name) {
}
