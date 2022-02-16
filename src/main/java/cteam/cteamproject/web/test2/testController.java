package cteam.cteamproject.web.test2;

import cteam.cteamproject.web.Success;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class testController {

    @PostMapping("/test")
    public Success test(@Validated @ModelAttribute TestForm testForm, BindingResult bindingResult) throws BindException {

        int result = testForm.getPrice() + testForm.getQuantity();
        if (result < 200){
            bindingResult.reject("bad", "bad");
            throw new BindException(bindingResult);
        }
        log.info("result = {}", result);
        return new Success(true);
    }
}
