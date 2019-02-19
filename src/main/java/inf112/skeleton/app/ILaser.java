package inf112.skeleton.app;

public interface ILaser extends ITile {
    /*
    For the time being the laser tile only contains information of where the laser is facing
    it's position should be kept by the board object, and the firing of the laser should
    be handled by the action class.
    */

    /**
     * @return the direction the laser is facing
     */
    Facing getLaser();
}
