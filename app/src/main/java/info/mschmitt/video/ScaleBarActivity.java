package info.mschmitt.video;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.TypedValue;
import com.google.android.exoplayer2.ui.TimeBar;
import com.google.android.exoplayer2.util.Util;
import info.mschmitt.video.databinding.ScaleBarActivityBinding;

import java.util.Formatter;
import java.util.Locale;

public class ScaleBarActivity extends AppCompatActivity {
    static final int DURATION = 305700;
    private ScaleBarActivityBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StringBuilder formatBuilder = new StringBuilder();
        Formatter formatter = new Formatter(formatBuilder, Locale.getDefault());
        binding = DataBindingUtil.setContentView(this, R.layout.scale_bar_activity);
        binding.scaleBar.setDragTimeIncrement(500, TypedValue.COMPLEX_UNIT_DIP, 100);
        binding.scaleBar.setPosition(0);
        binding.scaleBar.setDuration(DURATION);
        binding.sbPositionView.setText(Util.getStringForTime(formatBuilder, formatter, 0));
        binding.sbDurationView.setText(Util.getStringForTime(formatBuilder, formatter, DURATION));
        binding.timeBar.setPosition(0);
        binding.timeBar.setDuration(DURATION);
        binding.tbPositionView.setText(Util.getStringForTime(formatBuilder, formatter, 0));
        binding.tbDurationView.setText(Util.getStringForTime(formatBuilder, formatter, DURATION));
        TimeBar.OnScrubListener onScrubListener = new TimeBar.OnScrubListener() {
            @Override
            public void onScrubStart(TimeBar timeBar, long position) {
                binding.startTextView.setText(String.valueOf(position));
                binding.moveTextView.setText("");
                binding.stopTextView.setText("");
            }

            @Override
            public void onScrubMove(TimeBar timeBar, long position) {
                binding.moveTextView.setText(String.valueOf(position));
            }

            @Override
            public void onScrubStop(TimeBar timeBar, long position, boolean canceled) {
                binding.stopTextView.setText(String.valueOf(position) + " " + canceled);
            }
        };
        binding.timeBar.addListener(onScrubListener);
        binding.scaleBar.addListener(onScrubListener);
    }
}
