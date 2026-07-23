package DTO;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
public class MusicBrainzArtistSearchResponse {
    private int count;
    private List<MusicBrainzResponse> artists;
}
