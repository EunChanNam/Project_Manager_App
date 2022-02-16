package cteam.cteamproject.web.test2;

import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class TestForm {

    @Max(100)
    @NotNull
    private Integer price;

    @Max(100)
    private Integer quantity;
}
