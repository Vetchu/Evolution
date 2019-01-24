
class MapVisualizer {
    /**
     * Convert selected region of the worldMap into a string. It is assumed that the
     * indices of the worldMap will have no more than two characters (including the
     * sign).
     *
     * @param worldMap
     *            The worldMap to convert.
     * @param lowerLeft
     *            The lower left corner of the region that is dumped.
     * @param upperRight
     *            The upper right corner of the region that is dumped.
     * @return String representation of the selected region of the worldMap.
     */
    String dump(WorldMap worldMap, Position lowerLeft, Position upperRight) {
        StringBuilder builder = new StringBuilder();
        for (int i = upperRight.y-1 ; i >= lowerLeft.y; i--) {
            if (i == upperRight.y + 1) {
                builder.append(" y\\x ");
                for (int j = lowerLeft.x; j < upperRight.x + 1; j++) {
                    builder.append(String.format("%2d", j));
                }
                builder.append(System.lineSeparator());
            }
            builder.append(String.format("%3d: ", i));
            for (int j = lowerLeft.x; j <= upperRight.x ; j++) {
                Position currentPosition = new Position(j, i);
                if (j < upperRight.x) {
                    if (i < lowerLeft.y || i > upperRight.y) {
                        builder.append("--");
                    } else {
                        builder.append("|");
                        if (worldMap.isOccupied(currentPosition)) {
                            builder.append("M");
                        }else if(worldMap.getPlants()[currentPosition.x][currentPosition.y]>20){
                            builder.append(";");
                        }else if(worldMap.getPlants()[currentPosition.x][currentPosition.y]>0){
                            builder.append(",");
                        }

                        else {
                            builder.append(" ");
                        }
                    }
                } else {
                    if (i < lowerLeft.y || i > upperRight.y) {
                        builder.append("-");
                    } else {
                        builder.append("|");
                    }
                }
            }
            builder.append(System.lineSeparator());
        }
        return builder.toString();
    }
}