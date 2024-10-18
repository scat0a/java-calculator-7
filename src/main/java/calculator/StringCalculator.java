package calculator;

import camp.nextstep.edu.missionutils.Console;

public class StringCalculator {

    public void run() {
        System.out.println("덧셈할 문자열을 입력해 주세요.");
        String input = Console.readLine();

        UserInput userInput = new UserInput(input);
        String defaultDelimiter = "[,:]";
        DefaultDelimiter defaultDelimiters = new DefaultDelimiter(defaultDelimiter);
        DelimiterResult delimiterResult = new DelimiterResult(defaultDelimiters.getDefaultDelimiter());

        if (userInput.isCustom()) {
            int customDelimiterEndIndex = findCustomDelimiterEndIndex(userInput);
            String customDelimiter = userInput.getUserInput().substring(2, customDelimiterEndIndex);
            String updateDelimiter = delimiterResult.getDelimiterResult().replace("]", customDelimiter + "]");
            delimiterResult = new DelimiterResult(updateDelimiter);
            String combinedInput = userInput.getUserInput().substring(customDelimiterEndIndex + 2);
            input = combinedInput;
        }

        DelimitedNumbers delimitedNumbers = new DelimitedNumbers(input, delimiterResult);

        String[] separatedNumbers = delimitedNumbers.getStringNumbers().split(delimiterResult.getDelimiterResult());
        Numbers numbers = new Numbers(separatedNumbers);

        int sum = 0;
        for (String stringNumbers : numbers.getNumbers()) {
            Number number = new Number(stringNumbers);
            sum += Integer.parseInt(number.getNumber());
        }

        System.out.println("결과 : " + sum);

    }

    private static int findCustomDelimiterEndIndex(UserInput userInput) {
        int customDelimiterEndIndex = userInput.getUserInput().indexOf("\\n");
        return customDelimiterEndIndex;
    }

}
