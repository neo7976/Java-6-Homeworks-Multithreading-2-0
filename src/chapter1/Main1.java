package chapter1;

import java.util.ArrayList;
import java.util.List;

public class Main1 {
    public static void main(String[] args) {
        //непотокобезопасный список ArrayList<>
        List<String> names = new ArrayList<>();
//        // список будет пустым, требуется синхронизация
//        new Thread(()-> {
//            for (int i = 0; i < 10; i++) {
//                names.add("Имя номер: " + i);
//                try {
//                    Thread.sleep(300);
//                } catch (InterruptedException e) {
//                    return;
//                }
//            }
//        }).start();
//
//
//        new Thread(()->{
//            for (int i = 0; i < 10; i++) {
//                if (!names.isEmpty()) {
//                    System.out.println("Обслужили покупателя " + names.remove(0));
//                }
//                else
//                    System.out.println("Пусто!");
//            }
//        }).start();

//        // добавляем синхронизацию, иногда успеваем добавить, но все ещё не идеально
//        new Thread(() -> {
//            for (int i = 0; i < 10; i++) {
//                synchronized (names) {
//                    names.add("Имя номер: " + i);
//                }
//                try {
//                    Thread.sleep(300);
//                } catch (InterruptedException e) {
//                    return;
//                }
//            }
//        }).start();
//
//
//        new Thread(() -> {
//            for (int i = 0; i < 10; i++) {
//                synchronized (names) {
//                    if (!names.isEmpty()) {
//                        System.out.println("Обслужили покупателя " + names.remove(0));
//                    } else
//                        System.out.println("Пусто!");
//                }
//            }
//            try {
//                Thread.sleep(200);
//            } catch (InterruptedException e) {
//                return;
//            }
//        }).start();


        //  ....
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                synchronized (names) {
                    names.add("Имя номер: " + i);
                    names.notify(); //отправляем сигнал, кто ждёт этот объект
                }
                try {
                    Thread.sleep(300);
                } catch (InterruptedException e) {
                    return;
                }
            }
        }).start();


        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                synchronized (names) {
                    if (names.isEmpty()) {
                        try {
                            names.wait(); //ожидаем
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    System.out.println("Обслужили покупателя " + names.remove(0));
                }
            }
        }).start();


    }
}
