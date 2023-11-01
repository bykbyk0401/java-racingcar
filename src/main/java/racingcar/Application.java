package racingcar;

import camp.nextstep.edu.missionutils.Console;
import camp.nextstep.edu.missionutils.Randoms;
import racingcar.domain.CarInformation;
import racingcar.domain.GameManagement;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class Application {
    public static void main(String[] args) {
        List<String> carNames = Arrays.asList(getCarNames());
        int roundCount = getRoundCount();

        GameManagement game = new GameManagement(carNames);

        for (int i = 0; i < roundCount; i++) {
            game.playRound();
            printRoundResult(game);
        }

        List<String> winners = getWinners(game);
        printWinners(winners);
    }

    private static String[] getCarNames() {
        System.out.println("경주할 자동차 이름을 입력하세요.(이름은 쉼표(,) 기준으로 구분)");
        String input = Console.readLine();
        String[] carNames = input.split(",");
        return carNames;
    }

    private static int getRoundCount() {
        System.out.println("시도할 회수는 몇회인가요?");
        String input = Console.readLine();
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("올바른 숫자를 입력하세요.");
        }
    }

    private static void printRoundResult(GameManagement game) {
        for (CarInformation car : game.getWinners()) {
            System.out.print(car.getName() + " : ");
            for (int i = 0; i < car.getPosition(); i++) {
                System.out.print("-");
            }
            System.out.println();
        }
    }

    private static List<String> getWinners(GameManagement game) {
        List<CarInformation> winnerCars = game.getWinners();
        List<String> winners = new ArrayList<>();
        for (CarInformation car : winnerCars) {
            winners.add(car.getName());
        }
        return winners;
    }

    private static void printWinners(List<String> winners) {
        System.out.print("최종 우승자 : ");
        for (int i = 0; i < winners.size(); i++) {
            System.out.print(winners.get(i));
            if (i < winners.size() - 1) {
                System.out.print(", ");
            }
        }
    }
}
