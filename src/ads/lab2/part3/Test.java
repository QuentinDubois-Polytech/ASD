package ads.lab2.part3;

public class Test {

    public static void main(String[] args) {
        String s = "[ " + 1 + " ->";
        s = s.replace("[ ", "").replace(" ->", "");
        System.out.println(s);
    }
}
