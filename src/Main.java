public class Main {
    public static void main(String[] args){
        if (args.length<3){
            System.out.println("za mało argumentów");
            System.exit(1);
        }

        int[] intArgs = new int[args.length];

        for (int i=0;i<3;i++) {
            try {
                intArgs[i] = Integer.parseInt(args[i]);
            } catch (NumberFormatException e) {
                System.err.println("Failed trying to parse a non-numeric argument, " + args[i]);
            }
        }

        if(intArgs[2]==0) {
            System.err.println("Not enough arguments or 0 days were supplied");
        }
        else
        new WorldMap(intArgs[0],intArgs[1],intArgs[2]).init();
    }
}
