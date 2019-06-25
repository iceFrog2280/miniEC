package icefrog.com.latte;
import java.lang.reflect.Proxy;

public class Client {
    public static void main(String[] arg) {
        Subject man = new Man();
        SubjectProxy subjectProxy = new SubjectProxy(man);
        Subject subject = (Subject)Proxy
                .newProxyInstance(man.getClass().getClassLoader(),
                        man.getClass().getInterfaces(), subjectProxy);
        subject.shopping();
        System.out.println("Client: " + subject.getClass().getName());
    }
}
