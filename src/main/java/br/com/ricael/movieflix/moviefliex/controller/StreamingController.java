package br.com.ricael.movieflix.moviefliex.controller;

import br.com.ricael.movieflix.moviefliex.controller.request.StreamingRequest;
import br.com.ricael.movieflix.moviefliex.controller.response.StreamingResponse;
import br.com.ricael.movieflix.moviefliex.entity.Streaming;
import br.com.ricael.movieflix.moviefliex.mapper.StreamingMapper;
import br.com.ricael.movieflix.moviefliex.service.StreamingService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movieflix/streaming")
@RequiredArgsConstructor
public class StreamingController {
    private final StreamingService streamingService;

    @GetMapping()
    public ResponseEntity<List<StreamingResponse>> getAllStreaming() {
        return ResponseEntity.ok(streamingService.findAll()
                .stream()
                .map(StreamingMapper::toStreamingResponse)
                .toList());
    }

    @PostMapping()
    public ResponseEntity<StreamingResponse> saveStreaming(@RequestBody StreamingRequest request) {
        Streaming newStreaming = StreamingMapper.toStreaming(request);
        Streaming savedStreaming = streamingService.save(newStreaming);
        StreamingResponse retorno = StreamingMapper.toStreamingResponse(savedStreaming);
        return ResponseEntity.status(HttpStatus.CREATED).body(retorno);
    }

    @GetMapping("/{id}")
    public ResponseEntity<StreamingResponse> getStreamingById(@PathVariable Long id) {
        return streamingService.findById(id)
                .map(streaming -> ResponseEntity.ok(StreamingMapper.toStreamingResponse(streaming)))
                .orElse(ResponseEntity.notFound().build());

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStreamingById(@PathVariable Long id) {
        streamingService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
