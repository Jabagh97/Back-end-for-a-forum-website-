package com.example.demo.service;

import com.example.demo.model.Tag;
import com.example.demo.repository.TagRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TagService {

    private final TagRepository tagRepository;

    public Tag getOrCreateTag(String title) {
        Optional<Tag> optionalTag = tagRepository.findByTitle(title);
        if (optionalTag.isPresent()) {
            return optionalTag.get();
        }

        Tag tag = new Tag(title);
        return tagRepository.save(tag);
    }
}
