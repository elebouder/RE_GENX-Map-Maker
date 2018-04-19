package Model;
/*
* Enum to contain states for all possible road segments
* If a member has not been used it needs a graphic to be added in SegTemp, likewise any new
  new members added need a graphic added to SegTemp
 */
public enum RoadState {
    ERASE(0),
    NORMAL(1),
    FRICTION(2),
    START_LINE(3),
    FINISH_LINE(4),
    SPEED_BOOST(5),
    OBSTICAL(6),
    JUMP_PAD(7),
    TELEPORT_ENTRANCE(8),
    TELPPORT_EXIT(9);


    public final int stateVal;
    private RoadState(int val) {
        this.stateVal = val;
    }
}
