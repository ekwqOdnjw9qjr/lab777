package ru.edu.penzgtu.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import jakarta.validation.constraints.NotBlank;
import lombok.*;


import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name = "artists")
public class Artist {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    @NotBlank
    private String name;

    @Column(name = "country")
    @NotBlank
    private String country;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "artist_pictures",
            joinColumns = @JoinColumn(name = "picture_id",referencedColumnName = "id"),
    inverseJoinColumns = @JoinColumn(name = "artist_id",referencedColumnName = "id"))
    @JsonIgnoreProperties({"artist","id","critics","gallery"})


    private List<Picture> pictures;

}