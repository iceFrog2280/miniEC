package icefrog.com.latte;

public class Man implements Subject{
    @Override
    public void shopping() {
        System.out.println(""+this.getClass().getSimpleName()+"  is shopping()");
    }
}
