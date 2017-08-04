package co.tide.lab.labescape.transformer

import spock.lang.Specification
import spock.lang.Subject

class StringArrayTransformerTest extends Specification {

    @Subject
    StringArrayTransformer subject = new StringArrayTransformer()

    def "Transform a multiline string to a correctly sized char array"() {

        given:
        def labAsStr =
                """OOOOO
                  |O  OO
                  |OO  O""".stripMargin()

        when:
        def labAsArray = subject.transformToArray(labAsStr)

        then:
        labAsArray
        labAsArray == [
                ['O', 'O', 'O', 'O', 'O'],
                ['O', ' ', ' ', 'O', 'O'],
                ['O', 'O', ' ', ' ', 'O']
        ]
    }

    def "If try to transform a string with different lengths thrown illegal argument"() {

        given:
        def labAsStr =
                """OOO
                  |O  OO
                  |OO""".stripMargin()

        when:
        subject.transformToArray(labAsStr)

        then:
        IllegalArgumentException ex = thrown()
        ex.message == 'Strings have different lengths'
    }

    def "Transform a char array to a correct multiline string"() {

        given:
        def labAsArray = [
                ['O', 'O', 'O', 'O', 'O'],
                ['O', ' ', ' ', 'O', 'O'],
                ['O', 'O', ' ', ' ', 'O']
        ] as char[][]


        when:
        def labAsStr = subject.transformToString(labAsArray)

        then:
        labAsStr
        labAsStr ==
                """OOOOO
                  |O  OO
                  |OO  O""".stripMargin()
    }
}
