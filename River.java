public class River extends TileGroup{
    Tile recent_tile;

    //Pair <TileGroup,Player> stolen_tiles;

    public River(){
        super();
    }

    public Tile getRecent(){
        return recent_tile;
    }

    @Override
    public void add(Tile tile){
        group.add(tile);
        recent_tile = tile;
    }
}
