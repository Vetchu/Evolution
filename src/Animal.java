public class Animal {
    private Position position;
    private WorldMap worldMap;
    private Gene gene;
    private Integer energy=10;
    private Integer children=0;
    private Integer ancestors=0;
    private Integer age=0;

    public Position getPosition(){
        return this.position;
    }

    void move() {
        Integer[] bias=gene.getDirectionBias();
        Integer sum=gene.getSumBias();
        Integer randomInt=RandomFactory.requestRandomInt(sum);
        Integer index=0;
        while (randomInt>0){
            randomInt-=bias[index];
            index++;
        }
        this.position=worldMap.moveAnimal(this,position.add(RandomFactory.getMoveIntPair(index-1)));
        worldMap.place(this);
    }

    Animal(WorldMap worldMap, Integer x, Integer y, Gene gene, Integer energy){
        this.position =new Position(x,y);
        this.worldMap = worldMap;
        this.gene=new Gene(gene);
        this.energy=energy;
    }

    Animal(WorldMap worldMap, Position position, Gene gene,Integer energy){
        this.position =position;
        this.worldMap = worldMap;
        this.gene=new Gene(gene);
        this.energy=energy;
    }

    public void calculateLife(Integer[][] Plants){
        Integer plusEnergy=Plants[this.getPosition().x][this.getPosition().y];
        this.energy+=plusEnergy;
        divide();
    }

    public boolean calculateDead(){
        age++;
        energy--;
        return energy < 0;
    }

    @Override
    public String toString() {
        return "Animal{" +
                "position=" + position +
                ", gene=" + gene +
                ", energy=" + energy +
                ", children= "+children+
                ", ancestors= "+ancestors+
                ", age= "+age+
                '}';
    }


    private void divide(){
        if(this.age>5 && this.energy>200) {
            Animal child = new Animal(this.worldMap, new Position(this.position), new Gene(this.gene),this.energy/2);
            child.ancestors=this.ancestors+1;
            child.gene.modifyGene();
            this.energy/=2;
            Position birthPosition;
            for(int i=0;i<8;i++) {
                birthPosition=worldMap.moveAnimal(child,child.getPosition().add(RandomFactory.getMoveIntPair(i)));
                if(birthPosition!=child.position){
                    child.position=birthPosition;
                    worldMap.place(child);
                    break;
                }
            }
            children++;
            worldMap.place(this);
        }
    }
}
