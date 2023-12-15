package com.reclaite.service;

import lombok.AllArgsConstructor;
import lombok.Getter;
import com.reclaite.model.VideoCard;
import com.reclaite.repository.VideoCardRepository;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

@Service
@Getter
@AllArgsConstructor
public class VideoCardService {
    
    private final VideoCardRepository videoCardRepository;
    
    public VideoCard createVideoCard(VideoCard cpu) {
        return videoCardRepository.save(cpu);
    }
    
    public VideoCard updateVideoCard(Long id, VideoCard videoCardDetails) {
        VideoCard videoCard = videoCardRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("VideoCard not found with id " + id));
        
        videoCard.setTitle(videoCardDetails.getTitle());
        videoCard.setPrice(videoCardDetails.getPrice());
        videoCard.setFrequency(videoCardDetails.getFrequency());
        videoCard.setMemorySize(videoCardDetails.getMemorySize());
        videoCard.setMemoryType(videoCardDetails.getMemoryType());
        
        return videoCardRepository.save(videoCard);
    }
    
    public VideoCard deleteVideoCard(Long id) {
        VideoCard videoCard = videoCardRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("VideoCard not found with id " + id));
        videoCardRepository.delete(videoCard);
        return videoCard;
    }
    
}
