package jeonseguard.backend.global.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record OpenApiResponse<T>(
        Response<T> response
) {
    public T getItem() {
        return getItems().isEmpty() ? null : getItems().get(0);
    }

    public List<T> getItems() {
        return response.body.items.item;
    }

    public record Response<T>(
            Body<T> body
    ) {}

    public record Body<T>(
            Items<T> items
    ) {}

    public record Items<T>(
            List<T> item
    ) {}
}
