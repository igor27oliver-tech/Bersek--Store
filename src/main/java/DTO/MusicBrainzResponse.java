package DTO;

import com.example.demo2.model.ProdutoEntity;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MusicBrainzResponse {
    private String name;
    private String country;
    private Integer score;
    @JsonProperty("life-span")
    private LifeSpan lifeSpan;
    private ProdutoEntity produtoEntity;
}
