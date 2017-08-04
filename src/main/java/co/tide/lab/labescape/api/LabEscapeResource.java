package co.tide.lab.labescape.api;

import co.tide.lab.labescape.solver.LabEscape;
import co.tide.lab.labescape.transformer.StringArrayTransformer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
public class LabEscapeResource {

    private final LabEscape labEscape;

    private final StringArrayTransformer transformer;

    @RequestMapping("/lab/escape")
    public String getPathForLabEscape(@RequestParam(value = "lab") String lab,
                                      @RequestParam(value = "startX") int startX,
                                      @RequestParam(value = "startY") int startY) {
        log.info("getPathForLabEscape(lab= {}, startX= {}, startY= {})", lab, startX, startY);

        char[][] labAsArray = transformer.transformToArray(lab);
        char[][] labWithPath = labEscape.drawPathForEscape(labAsArray, startX, startY);

        return transformer.transformToString(labWithPath);
    }

}
