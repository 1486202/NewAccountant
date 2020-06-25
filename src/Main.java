import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {


        int option = inputOption();
        calculate(option);
    }

    private static int inputOption() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int result;
        while (true) {
            System.out.println("Укажите вид дохода (введите число от 1 до 4):\nЗаработная плата : 1\nОблагаемая материальная помощь : 2\nМатериальная помощь при рождении : 3\nМатериальная помощь при смерти: 4");
            try {
                result = Integer.parseInt(reader.readLine());
                if (checkChoice(result)) {
                    return result;
                }

                System.out.println("Ошибка! Неверный выбор");
            } catch (IOException e) {
                System.out.println("Ошибка ввода: " + e.getMessage());
            } catch (NumberFormatException e) {
                System.out.println("Введите число! Ошибка: " + e.getMessage());
            }

        }
    }

    private static boolean checkChoice(int n) {
        return n >= 1 && n <= 4;
    }


    private static void calculate(int n) throws IOException {
        switch (n) {
            case 1:
                System.out.println("Заработная плата");
                System.out.println("Начисленная сумма: " + salary());
                break;
            case 2:
                System.out.println("Облагаемая материальная помощь");
                System.out.println("Начисленная сумма: " + materialTax());
                break;
            case 3:
                System.out.println("Материальная помощь при рождении");
                System.out.println("Начисленная сумма: " + materialBirth());
                break;
            case 4:
                System.out.println("Материальная помощь при смерти");
                System.out.println("Начисленная сумма: " + materialDeath());
                break;
            default:
                throw new IllegalArgumentException("Неверный выбор!");
        }
    }

    private static double salary() throws IOException {
        double income = inputIncome("Введите сумму \"на руки\": ");
        return calculateN(income);
    }

    private static double calculateN(double income) {
        income += (double) ((int) Math.round(income * 100.0D / 87.0D * 0.13D));
        return income;
    }

    private static double materialBirth() throws IOException {
        double income = inputIncome("Введите сумму \"на руки\": ");
        return calculateNDFL(income);
    }

    private static double calculateNDFL(double income) {
        if (income > 50000.0D) {
            income += (double) ((int) Math.round((income - 50000.0D) * 100.0D / 87.0D * 0.13D));
        }

        return income;
    }

    private static double materialDeath() throws IOException {
        return inputIncome("Введите сумму \"на руки\": ");
    }

    private static double materialTax() throws IOException {
        double mathelp = inputIncome("Введите сумму материальной помощи, которая была начислена в текущем календарном году. Если не начислялась, введите ноль.");
        double income = inputIncome("Введите сумму \"на руки\": ");
        return calculateMathelp(income, mathelp);
    }

    private static double calculateMathelp(double income, double mathelp) {
        if (mathelp > 4000.0D) {
            mathelp = 4000.0D;
        }

        if (income >= 4000.0D - mathelp) {
            income = income - (double) ((int) Math.round(4000.0D - mathelp)) * 0.13D + (double) ((int) Math.round((income - (double) ((int) Math.round(4000.0D - mathelp)) * 0.13D) * 100.0D / 87.0D * 0.13D));
        }

        return income;
    }


    private static double inputIncome(String text) throws IOException {
        while (true) {
            System.out.println(text);
            try{
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            double income = Double.parseDouble(reader.readLine());
            if (validateSum(income)) {
                return income;
            }
        } catch (IOException e) {
            System.out.println("Ошибка ввода: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Введите число! Ошибка: " + e.getMessage());
        }

            System.out.println("Сумма некорректна!");
        }
    }

    private static boolean validateSum(double income) {
        return income >= 0.0D && income <= 500000.0D;
    }
}
