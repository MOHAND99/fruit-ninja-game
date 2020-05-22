package fruitninja;

import fruitninja.objects.ObjectType;
import javafx.scene.image.Image;

public interface GameObject {
    /**
     * @return the type of game object
     */
    ObjectType getObjectType();

    /**
     * @return X location of game object
     */
    int getXlocation();

    /**
     * @return Y location of game object
     */
    int getYlocation();

    /**
     * @return max Y location that the object can reach on the screen
     */
    int getMaxHeight();

    /**
     * @return velocity at which game object is thrown
     */
    int getInitialVelocity();

    ///**
    // * @return failing velocity of game object
    // */
    //int getFallingVelocity();

    /**
     * @return whether the object is sliced or not
     */
    boolean isSliced();

    /**
     * @return whether the object is dropped off the screen or not
     */
    boolean hasMovedOffScreen();

    /**
     * it is used to slice the object
     */
    void slice();

    ///**
    // * it is used to move the object on the screen
    // *
    // * @param time: time elapsed since the object is thrown
    // *              it is used calculate the new position of
    // *              fruit object.
    // */
    //void move(double time);

    /**
     * @return at least two images of the object, one when it is
     * sliced and one when it is not.
     */
    Image[] getBufferedImages(); // Changed BufferedImage (swing) to Image (FX) fo two reasons:
    // 1. We're using javaFX, there's no need to load the image as BufferedImage then the caller has to convert it to Image.
    // 2. Loading as BufferedImage then converting to Image caused dynamic animated gif to be static.
}