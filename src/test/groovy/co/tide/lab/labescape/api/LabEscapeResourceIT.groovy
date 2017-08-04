package co.tide.lab.labescape.api

import co.tide.lab.labescape.LabEscapeApplication
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.HttpStatus
import org.springframework.web.client.RestTemplate
import spock.lang.Specification

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT

@SpringBootTest(classes = LabEscapeApplication, webEnvironment = RANDOM_PORT)
class LabEscapeResourceIT extends Specification {

    RestTemplate restTemplate = new RestTemplate();

    @Value('http://localhost:${local.server.port}/lab/escape?lab={lab}&startX={startX}&startY={startY}')
    def subjectEndpoint

    def "Test rest service works correctly"() {

        given:
        def labToSolve =
                """OOOOOOOOOO
                  |O    O   O
                  |O OO O O O
                  |O  O O O O
                  |O OO   O  
                  |O OOOOOOOO
                  |O        O
                  |OOOOOOOOOO""".stripMargin()
        def x = 3
        def y = 1

        when:
        def result = restTemplate.getForEntity(subjectEndpoint, String.class, [lab: labToSolve, startX: x, startY: y]);

        then:
        result.statusCode == HttpStatus.OK
        result.body ==
                """OOOOOOOOOO
                  |O••••O•••O
                  |O•OO•O•O•O
                  |O• O•O•O•O
                  |O OO•••O••
                  |O OOOOOOOO
                  |O        O
                  |OOOOOOOOOO""".stripMargin()
    }
}