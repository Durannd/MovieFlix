package br.com.ricael.movieflix.moviefliex.service;

import br.com.ricael.movieflix.moviefliex.entity.Streaming;
import br.com.ricael.movieflix.moviefliex.repository.StreamingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StreamingService {
    private final StreamingRepository streamingRepository;

    public List<Streaming> findAll(){
        return streamingRepository.findAll();
    }

    public Streaming save( Streaming streaming){
        streamingRepository.save(streaming);
        return streaming;
    }

    public Optional<Streaming> findById (Long id){

        return streamingRepository.findById(id);
    }

    public void deleteById(Long id){
        streamingRepository.deleteById(id);
    }
}
