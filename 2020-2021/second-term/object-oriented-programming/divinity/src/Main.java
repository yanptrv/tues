import com.home.Kris.*;

public class Main {
    public static void main(String[] args) throws Exception{
        Archer archer = new Archer();
        Mage mage = new Mage();

        Note note = new Note("Love message", "I love you");
        Potion potion = new Potion("Sex potion", 10, 5);
        Scroll scroll = new Scroll("Lighting spell", 10);
        HuntingRifle rifle = new HuntingRifle("Kar98", 15);

        mage.pick(scroll);
        mage.pick(note);

        archer.pick(rifle);
        archer.pick(potion);

        System.out.println(archer.toString());
        mage.useAt(0,archer);
        System.out.println(archer.toString());
        archer.useAt(1,mage);
        System.out.println(archer.toString());
        note.readNote();
        System.out.println(mage.toString());
        archer.useAt(0,mage);
        System.out.println(mage.toString());
    }
}
