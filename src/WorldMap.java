import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static java.lang.Math.abs;

public class WorldMap {
    private Map<Position, Animal> animals = new ConcurrentHashMap<>();
    private Position lowerLeft = new Position(0, 0);
    private Position upperRight = new Position(10, 10);
    private Integer[][] Plants;
    private Integer daysPassed = 0;
    private Integer simulationDays=0;


    WorldMap(int width, int height,int days) {
        this.upperRight = new Position(width, height);
        this.lowerLeft = new Position(0, 0);
        this.simulationDays=days;
        Plants = new Integer[width][height];
    }

    private void passADay() {
        genPlants();
        moveAllAnimals();
        feedAllAnimals();
        removeDeadAnimals();
        daysPassed++;
    }

    private void moveAllAnimals() {
        for (Animal animal : animals.values()) {
                animal.move();
        }
    }

    private void feedAllAnimals() {
        for (Animal animal : animals.values()) {
                animal.calculateLife(Plants);
                Plants[animal.getPosition().x][animal.getPosition().y] = 0;
        }
    }

    private void removeDeadAnimals() {
        for (Animal animal : animals.values()) {
                if (animal.calculateDead()) {
                    animals.remove(animal.getPosition());
                }
            }
        }


    boolean place(Animal animal) {
        if(isOccupied(animal.getPosition())) return false;
        animals.put(animal.getPosition(),animal);
        return true;
    }


    boolean isOccupied(Position position) {
        return animals.get(position) != null;
    }

    public String toString() {
        return new MapVisualizer().dump(this, lowerLeft, upperRight);
    }


    private void genPlants() {
        for (Integer i = 0; i <= Math.log10(daysPassed*upperRight.x); i++) {
            Integer newPlant = RandomFactory.requestRandomInt(this.upperRight.x * this.upperRight.y-1);
            //steppe
            Plants[newPlant / this.upperRight.x][newPlant % this.upperRight.y] += 5;
            //jungle
            Plants[newPlant / this.upperRight.x/3][newPlant % this.upperRight.y/3] += 10;
        }
    }

    Position moveAnimal(Animal animal, Position position) {
        if(position.x>=upperRight.x){
            position=new Position(abs(position.x - upperRight.x), position.y);
        }else if(position.x<lowerLeft.x){
            position=new Position(abs(position.x + upperRight.x), position.y);
        }

        if(position.y>=upperRight.y){
            position=new Position(position.x,abs(position.y - upperRight.y));
        }else if(position.y<lowerLeft.y){
            position=new Position(position.x,abs(position.y + upperRight.y));
        }

        if(isOccupied(position)) return animal.getPosition();

        animals.remove(animal.getPosition());
        return position;
    }

    private void setupPlants(){
        for (int i = 0; i < this.upperRight.x; i++) {
            for (int j = 0; j < this.upperRight.y; j++) {
                Plants[i][j] = 0;
            }
        }
        this.genPlants();
    }

    void init() {
        setupPlants();
        this.place(new Animal(this, new Position(2, 2), new Gene(), 200));
        this.place(new Animal(this, 3, 4, new Gene(), 200));

        for (int i = 0; i < this.simulationDays; i++) {
            passADay();
        }

        System.out.println(dumpAnimalData());
        System.out.println(new MapVisualizer().dump(this, this.lowerLeft, this.upperRight));
    }

    private String dumpAnimalData(){
        StringBuilder bob=new StringBuilder();
        for (Animal animal:animals.values()) {
            bob.append(animal.toString()).append("\n");
        }
        return bob.toString();
    }

    Integer[][] getPlants(){
        return Plants;
    }
}

