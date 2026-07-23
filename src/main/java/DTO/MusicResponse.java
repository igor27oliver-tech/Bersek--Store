package DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MusicResponse {
    private long IdMusica;
    private String titulo;
    private String mp3Url;
    private long idProduto;
    private String nomeProduto;
    private String pictureProduto;
}
