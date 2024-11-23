package src.t1_Calculator;


public class Main {
    public static void main(String[] args) {
        Calculator calc = Calculator.instance.get();
        int a = calc.plus.apply(1, 2);
        int b = calc.minus.apply(1, 1);
        try {
            int c = calc.divide.apply(a, b);
            calc.println.accept(c);
        } catch (ArithmeticException e) {
            calc.println.accept(e.getMessage());
        }

        /*Ошибка возникала из-за деления на ноль. Чтобы избежать ошибки, я добавила выбрасывание исключения.
         Также можно было сделать это через условный оператор:
            if (calc.isZero.test(b)) {
                calc.println.accept("Делить на ноль нельзя!");
            } else {
                int c = calc.divide.apply(a, b);
                calc.println.accept(c);
            }
        Кроме того, я поменяла integer на object в строчке  Consumer<Object> println = System.out::println,
        чтобы была возможность выводить строчки */

    }
}
