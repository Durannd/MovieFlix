package br.com.ricael.movieflix.moviefliex.mapper;


import br.com.ricael.movieflix.moviefliex.controller.request.StreamingRequest;
import br.com.ricael.movieflix.moviefliex.controller.response.StreamingResponse;
import br.com.ricael.movieflix.moviefliex.entity.Streaming;
import lombok.experimental.UtilityClass;

@UtilityClass
public class StreamingMapper {
    public static Streaming toStreaming(StreamingRequest streamingRequest) {
        return Streaming
                .builder()
                .name(streamingRequest.name())
                .build();

    }

    public static StreamingResponse toStreamingResponse(Streaming streaming) {
        return StreamingResponse.builder()
                .id(streaming.getId())
                .name(streaming.getName())
                .build();
    }
}
