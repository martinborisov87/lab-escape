package co.tide.lab.labescape.solver

import spock.lang.Specification
import spock.lang.Subject

import static co.tide.lab.labescape.solver.LabEscape.*

class LabEscapeTest extends Specification {

    @Subject
    LabEscape subject = new LabEscape()

    def "We can move if the point is not visited and it is free"() {

        given:
        char[][] lab = [[isFree ? FREE : WALL]]
        boolean[][] visited = [[isVisited]]

        expect:
        canMove == subject.canMove(0, 0, lab, visited)

        where:
        isFree | isVisited || canMove
        true   | false     || true
        false  | false     || false
        true   | true      || false
        false  | true      || false
    }

    def "It has escaped if we are on a border"() {

        given:
        char[][] labyrinth = [
                [FREE, FREE, FREE],
                [FREE, FREE, FREE],
                [FREE, FREE, FREE]
        ]

        expect:
        hasEscaped == subject.hasEscaped(x, y, labyrinth)

        where:
        x | y || hasEscaped
        0 | 1 || true
        1 | 0 || true
        2 | 1 || true
        1 | 2 || true
        1 | 1 || false
    }

    def "We step to a point marking it as visit and with path"() {

        given:
        char[][] lab = [[FREE]]
        boolean[][] visited = [[false]]

        when:
        subject.placeIn(0, 0, lab, visited)

        then:
        lab[0][0] == PATH
        visited[0][0] == true
    }

    def "We erase a path by marking it as free again"() {

        given:
        char[][] lab = [[PATH]]

        when:
        subject.erase(0, 0, lab)

        then:
        lab[0][0] == FREE
    }

    def "Draw escape path if such exists"() {

        given:
        char[][] labyrinthToSolve = [
                [WALL, WALL, WALL, WALL, WALL, WALL, WALL, WALL, WALL, WALL],
                [WALL, FREE, FREE, FREE, FREE, WALL, FREE, FREE, FREE, WALL],
                [WALL, FREE, WALL, WALL, FREE, WALL, FREE, WALL, FREE, WALL],
                [WALL, FREE, FREE, WALL, FREE, WALL, FREE, WALL, FREE, WALL],
                [WALL, FREE, WALL, WALL, FREE, FREE, FREE, WALL, FREE, FREE],
                [WALL, FREE, WALL, WALL, WALL, WALL, WALL, WALL, WALL, WALL],
                [WALL, FREE, FREE, FREE, FREE, FREE, FREE, FREE, FREE, WALL],
                [WALL, WALL, WALL, WALL, WALL, WALL, WALL, WALL, WALL, WALL]
        ]

        def x = 3
        def y = 1

        when:
        def escape = subject.drawPathForEscape(labyrinthToSolve, x, y)

        then:
        escape
        escape == [
                [WALL, WALL, WALL, WALL, WALL, WALL, WALL, WALL, WALL, WALL],
                [WALL, PATH, PATH, PATH, PATH, WALL, PATH, PATH, PATH, WALL],
                [WALL, PATH, WALL, WALL, PATH, WALL, PATH, WALL, PATH, WALL],
                [WALL, PATH, FREE, WALL, PATH, WALL, PATH, WALL, PATH, WALL],
                [WALL, FREE, WALL, WALL, PATH, PATH, PATH, WALL, PATH, PATH],
                [WALL, FREE, WALL, WALL, WALL, WALL, WALL, WALL, WALL, WALL],
                [WALL, FREE, FREE, FREE, FREE, FREE, FREE, FREE, FREE, WALL],
                [WALL, WALL, WALL, WALL, WALL, WALL, WALL, WALL, WALL, WALL]
        ]
    }
}
