import java.util.Random;

public class RandomFactory {
    private static Random random=new Random();

    public static Integer requestRandomInt(Integer max){
        return random.nextInt(max);
    }
    public static Position getMoveIntPair(Integer number){
        if(number==4){
            return new Position(-1,1);
        }
        return new Position(number/3-1,number%3-1);
    }
}
