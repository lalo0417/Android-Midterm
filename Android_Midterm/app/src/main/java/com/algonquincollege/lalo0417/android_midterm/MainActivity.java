package com.algonquincollege.lalo0417.android_midterm;

/**
 * Created by CalebLalonde on 2016-09-26.
 */

import android.app.DialogFragment;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Observable;
import java.util.Observer;

import HSVModel.HSVModel;

public class MainActivity extends AppCompatActivity implements Observer, SeekBar.OnSeekBarChangeListener {

    private static final String ABOUT_DIALOG_TAG;
    private AboutDialogFragment mAboutDialog;
    private TextView mColorSwatch;
    private HSVModel mModel;
    private SeekBar mHueSB;
    private SeekBar mValueSB;
    private SeekBar mSaturationSB;
    private TextView mHueTxt;
    private TextView mSatTxt;
    private TextView mValTxt;


    static {
        ABOUT_DIALOG_TAG = "About Dialog";
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mModel = new HSVModel(HSVModel.MIN_HUE, HSVModel.MIN_VALUE, HSVModel.MIN_SATURATION);
        mModel.addObserver(this);

        mColorSwatch = (TextView) findViewById(R.id.colorSwatch);
        mHueSB = (SeekBar) findViewById(R.id.hueSB);
        mValueSB = (SeekBar) findViewById(R.id.valueSB);
        mSaturationSB = (SeekBar) findViewById(R.id.saturationSB);

        mHueTxt = (TextView) findViewById(R.id.hueTxt);
        mSatTxt = (TextView) findViewById(R.id.saturationTxt);
        mValTxt = (TextView) findViewById(R.id.valueTxt);

//        mModel.setHue( HSVModel.MIN_HUE );
//        mModel.setValue( HSVModel.MIN_VALUE );
//        mModel.setSaturation( HSVModel.MIN_SATURATION );

        mHueSB.setMax((HSVModel.MAX_HUE).intValue());
        mValueSB.setMax((HSVModel.MAX_VALUE).intValue());
        mSaturationSB.setMax((HSVModel.MAX_SATURATION).intValue());

        mHueSB.setOnSeekBarChangeListener(this);
        mValueSB.setOnSeekBarChangeListener(this);
        mSaturationSB.setOnSeekBarChangeListener(this);


        this.updateView();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }


    public void onColorButtonSelected(View button) {
        // Handle presses on the action bar items
        switch (button.getId()) {

            case R.id.blackBtn:
                mModel.asBlack();
                break;

            case R.id.whiteBtn:
                mModel.asWhite();
                break;

            case R.id.redBtn:
                mModel.asRed();
                break;

            case R.id.limeBtn:
                mModel.asLime();

                break;

            case R.id.blueBtn:
                mModel.asBlue();
                break;

            case R.id.yellowBtn:
                mModel.asYellow();
                break;

            case R.id.cyanBtn:
                mModel.asCyan();
                break;

            case R.id.magentaBtn:
                mModel.asMagenta();
                break;

            case R.id.silverBtn:
                mModel.asSilver();
                break;

            case R.id.grayBtn:
                mModel.asGray();
                break;

            case R.id.maroonBtn:
                mModel.asMaroon();
                break;

            case R.id.oliveBtn:
                mModel.asOlive();
                break;

            case R.id.greenBtn:
                mModel.asGreen();
                break;

            case R.id.purpleBtn:
                mModel.asPurple();
                break;

            case R.id.tealBtn:
                mModel.asTeal();
                break;

            case R.id.navyBtn:
                mModel.asNavy();
                break;

            default:
                return;
        }
        Toast.makeText(this, "Set Hue to: " + mModel.getHue() + "\n Set Saturation to: " + mModel.getSaturation() + "\n Set Value to: " + mModel.getValue(), Toast.LENGTH_SHORT).show();
    }

    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

        if (!fromUser) {
            return;
        }

        switch (seekBar.getId()) {
            case R.id.hueSB:
                mModel.setHue((float) mHueSB.getProgress());
                break;

            case R.id.valueSB:
                mModel.setValue((float) mValueSB.getProgress()/100);
                break;

            case R.id.saturationSB:
                mModel.setSaturation((float) mSaturationSB.getProgress()/100);
                break;
        }
        //Log.i("TAG","Hue: "+mHueSB.getProgress()+" S: "+mValueSB.getProgress()+"  V: "+mSaturationSB.getProgress());
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }

    public void update(Observable observable, Object data) {
        this.updateView();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if (id == R.id.action_about) {
            DialogFragment newFragment = new AboutDialogFragment();
            newFragment.show(getFragmentManager(), ABOUT_DIALOG_TAG);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void updateColorSwatch() {
        mColorSwatch.setBackgroundColor(Color.HSVToColor(new float[]{mModel.getHue()
                , mModel.getValue()
                , mModel.getSaturation()}));
    }

    private void updateHueSB() {
        mHueSB.setProgress(mModel.getHue().intValue());
        mHueTxt.setText("Hue: " + mModel.getHue().intValue() + "\u00B0");
    }

    private void updateValueSB() {
        mValueSB.setProgress((int)(mModel.getValue()*100));
        mValTxt.setText("Value: " + mModel.getValue()*100 + "%");
    }

    private void updateSaturationSB() {
        mSaturationSB.setProgress((int)(mModel.getSaturation()*100));
        mSatTxt.setText("Saturation: " + mModel.getSaturation()*100 + "%");
    }

    public void updateView() {
        this.updateColorSwatch();
        this.updateHueSB();
        this.updateValueSB();
        this.updateSaturationSB();
    }
}
