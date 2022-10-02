package chapter1;

public class A {

//  аналогично записи  public synchronized void foo()
    public void foo() {
        synchronized (this) {
// код
        }
    }

//  аналогично записи public synchronized static void bar()
    public static void bar() {
        synchronized (A.class) {
// код
        }
    }
}