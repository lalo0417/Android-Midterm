package HSVModel;

import android.graphics.Color;

import java.util.Observable;

/**
 * Created by CalebLalonde on 2016-10-21.
 */

public class HSVModel extends Observable {

    public static final Float MAX_HUE = 360.0f;
    public static final Float MIN_HUE   = 0.0f;
    public static final Float MAX_VALUE = 100.0f;
    public static final Float MIN_VALUE   = 0.0f;
    public static final Float MAX_SATURATION = 100.0f;
    public static final Float MIN_SATURATION = 0.0f;

    private Float hue;
    private Float value;
    private Float saturation;

    public HSVModel( Float hue, Float value, Float saturation) {
        super();

        this.hue = hue;
        this.value  = value;
        this.saturation = saturation;
    }

    public void asBlack()   { this.setHSV( 0.0f, 0.0f, 0.0f ); }
    public void asWhite()   { this.setHSV( 0.0f, 0.0f, 1.0f ); }
    public void asRed()   { this.setHSV( 0.0f, 1.0f, 1.0f ); }
    public void asLime()   { this.setHSV( 120.f, 1.0f, 1.0f ); }
    public void asBlue()   { this.setHSV( 240.0f, 1.0f, 1.0f ); }
    public void asYellow()   { this.setHSV( 60.0f, 1.0f, 1.0f ); }
    public void asCyan()   { this.setHSV( 180.0f, 1.0f, 1.0f ); }
    public void asMagenta()   { this.setHSV( 300.0f, 1.0f, 1.0f ); }
    public void asSilver()   { this.setHSV( 0.0f, 0.0f, 0.75f ); }
    public void asGray()   { this.setHSV( 0.0f, 0.0f, 0.5f ); }
    public void asMaroon()   { this.setHSV( 0.0f, 1.0f, 0.5f ); }
    public void asOlive()   { this.setHSV( 60.0f, 1.0f, 0.5f ); }
    public void asGreen()   { this.setHSV( 120.0f, 1.0f, 0.5f ); }
    public void asPurple()   { this.setHSV( 300.0f, 1.0f, 0.5f ); }
    public void asTeal()   { this.setHSV( 180.0f, 1.0f, 0.5f ); }
    public void asNavy()   { this.setHSV( 240.0f, 1.0f, 0.5f ); }

    public Float getHue() { return hue; }
    public Float getValue()  { return value; }
    public Float getSaturation() { return saturation; }

    public void setHue( Float hue ) {
        this.hue = hue;

        this.updateObservers();
    }

    public void setValue(Float value) {
        this.value = value;

        this.updateObservers();
    }

    public void setSaturation(Float saturation) {
        this.saturation = saturation;

        this.updateObservers();
    }


    public void setHSV( Float hue, Float value, Float saturation ) {
        this.hue   = hue;
        this.value = value;
        this.saturation  = saturation;

        this.updateObservers();
    }

    private void updateObservers() {
        this.setChanged();
        this.notifyObservers();
    }


}
