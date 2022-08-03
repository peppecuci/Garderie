package be.digitalcity.giuseppe.demospringwithalexandre.model.dto;

import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Map;

@Data
@Builder
public class ErrorDTO {

    private LocalDateTime receivedAt;
    private HttpMethod method;
    private String path;
    private String message;
    private HttpStatus status;
    private Map<String, Object> infos;

    public ErrorDTO addInfo( String key, Object value ){
        infos.put(key, value);
        return this;
    }

}
