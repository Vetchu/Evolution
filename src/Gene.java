import java.util.Arrays;

public class Gene {
    private Integer[] directionBias={2,2,2,2,2,2,2,2};
    private Integer sumBias=directionBias.length*2;

    void modifyGene(){
        Integer whichGene=RandomFactory.requestRandomInt(8);
        Integer changeAmount=RandomFactory.requestRandomInt(3)-1;
        directionBias[whichGene]+=changeAmount;
        if(directionBias[whichGene]<2) directionBias[whichGene]=1;
        sumBias=0;
        for (Integer integer: directionBias){
            sumBias+=integer;
        }
    }


    public Integer[] getDirectionBias(){
        return directionBias;
    }

    Gene(){

    }

    Gene(Gene gene){
        this.directionBias=gene.directionBias.clone();
        this.sumBias=gene.sumBias;
    }
    public Integer getSumBias() {
        return sumBias;
    }

    @Override
    public String toString() {
        return "{directionBias=" + Arrays.toString(directionBias)+'}';
    }
}
