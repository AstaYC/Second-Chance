package com.MBAREK0.web.service;

import com.MBAREK0.web.entity.Tag;
import com.MBAREK0.web.repository.TagRepository;
import com.MBAREK0.web.repository.implementation.TagRepositoryImpl;
import jakarta.persistence.EntityManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TagService {
    private TagRepository tagRepository;

    public TagService(EntityManager entityManager) {
        this.tagRepository = new TagRepositoryImpl(entityManager);
    }

    public Tag createTag(Tag tag){
        return tagRepository.createTag(tag);
    }

    public Optional<Tag> getTagById(long id){
        return tagRepository.getTagById(id);
    }

    public List<Tag> getTagsByIds(List<String> tagsList) {
        List<Tag> tags = new ArrayList<>();
        for (String tagId : tagsList) {
            Optional<Tag> tag = tagRepository.getTagById(Long.parseLong(tagId));
            tag.ifPresent(tags::add);
        }
        return tags;
    }

    public List<Tag> gatAllTags(){
        return tagRepository.gatAllTags();
    }

    public Tag updateTag(Tag tag){
        return tagRepository.updateTag(tag);
    }

    public Tag deleteTag(Tag tag){
        return tagRepository.deleteTag(tag);
    }

}
