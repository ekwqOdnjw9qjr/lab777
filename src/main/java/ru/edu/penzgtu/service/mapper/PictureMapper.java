package ru.edu.penzgtu.service.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.edu.penzgtu.dto.PictureDto;
import ru.edu.penzgtu.entity.Artist;
import ru.edu.penzgtu.entity.Picture;
import ru.edu.penzgtu.repo.ArtistRepository;
import ru.edu.penzgtu.repo.CriticRepository;
import ru.edu.penzgtu.repo.GalleryRepository;
import ru.edu.penzgtu.entity.Critic;


import java.util.Collections;
import java.util.List;
@RequiredArgsConstructor
@Service
public class PictureMapper {
    private final ArtistRepository artistRepository;
    private final GalleryRepository galleryRepository;
    private final CriticRepository criticRepository;

    public List<PictureDto> toListDto(List<Picture> pictures) {
        return pictures.stream().map(this::toDto).toList();
    }

    public PictureDto toDto(Picture picture) {
       return PictureDto.builder()
                .id(picture.getId())
                .name(picture.getName())
                .artistName(picture.getArtist().getName())
                .galleryName(picture.getGallery().getName())
                .critics(picture.getCritics().stream()
                         .map(Critic :: getName).toList())
                .build();
    }

    public Picture toEntity(PictureDto pictureDto) {
        Picture picture = new Picture();

        picture.setId(pictureDto.getId());
        picture.setName(pictureDto.getName());
        picture.setArtist(artistRepository.findByName(pictureDto.getArtistName()));
        picture.setGallery(galleryRepository.findByName(pictureDto.getGalleryName()));


        return picture;
    }
}
