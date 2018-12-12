package com.mciad.astralcompass;

import android.content.Context;
import android.support.v7.widget.AppCompatImageButton;
import android.util.AttributeSet;

/**
 * ImageButton Class Extension providing built in image toggle
 * functionality.
 */
public class ToggleImageButton extends AppCompatImageButton {

    private boolean toggledOn;
    private int imageOn;
    private int imageOff;

    public ToggleImageButton(Context ctx){
        super(ctx);
        toggledOn = false;
    }

    public ToggleImageButton(Context ctx, AttributeSet attrs){
        super(ctx, attrs);
        toggledOn = false;
    }

    public ToggleImageButton(Context ctx, AttributeSet attrs, int defStyle){
        super(ctx, attrs, defStyle);
        toggledOn = false;
    }

    /**
     * Create a new togglable ImageButton while also providing the IDs
     * of the 'On' and 'Off' state images.
     * @param ctx - the view context
     * @param imgOn - image ID of the 'On' image  (example: R.id.myTarget)
     * @param imgOff - image ID of the 'Off' image
     */
   /* public ToggleImageButton(Context ctx, int imgOn, int imgOff){
        super(ctx);
        setOnImage(imgOn);
        setOffImage(imgOff);
        toggleOff();
    }*/

    /**
     * Set the ID of the 'On' image for the button
     * to display while in 'On' state.
     * @param img - image ID of the 'On' image  (example: R.id.myTarget)
     */
    public void setOnImage(int img){
        imageOn = img;
    }

    /**
     * Set the ID of the 'On' image for the button
     * to display while in 'On' state.
     * @param img - image ID of the 'On' image  (example: R.id.myTarget)
     */
    public void setOffImage(int img){
        imageOff = img;
    }

    /**
     * Set the button state to 'On'
     */
    public void toggleOn(){
        toggledOn = true;
        setImageResource(imageOn);
    }

    /**
     * Set the button state to 'Off'
     */
    public void toggleOff(){
        toggledOn = false;
        setImageResource(imageOff);
    }

    /**
     * Switch to the alternate button state.
     * (i.e. switch between on or off)
     */
    public void toggle(){
        toggledOn = !toggledOn;
        if(toggledOn){
            setImageResource(imageOn);
        }else{
            setImageResource(imageOff);
        }
    }

    /**
     * Returns the button's on/off state as boolean
     * @return true if button is 'on' otherwise false
     */
    public boolean isOn(){
        return toggledOn;
    }
}
