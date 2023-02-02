package com.example.animation_labtest;

import android.os.Bundle;
import android.app.Activity;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;

public class MainActivity extends Activity {

    private static final int SQUARE_SIZE = 100;
    private static final int SPEED = 150;
    private static final int UPDATE_RATE = 50;

    private int x_axis;
    private int y_axis;
    private int direction;
    private Paint paint;
    private boolean isMoving;
    private Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        x_axis = 0;
        y_axis = 0;
        direction = 1;
        paint = new Paint();
        paint.setColor(Color.RED);
        isMoving = true;
        handler = new Handler();

        setContentView(new View(this) {
            @Override
            protected void onDraw(Canvas canvas) {
                canvas.drawRect(x_axis, y_axis, x_axis + SQUARE_SIZE, y_axis + SQUARE_SIZE, paint);
                if (isMoving) {
                    y_axis += direction * SPEED * UPDATE_RATE / 1000;
                    if (y_axis + SQUARE_SIZE >= getHeight()) {
                        direction = -1;
                        x_axis += SQUARE_SIZE;
                        paint.setColor(randomColor());
                    } else if (y_axis <= 0) {
                        direction = 1;
                        x_axisa += SQUARE_SIZE;
                        paint.setColor(randomColor());
                    }
                    if (x_axis + SQUARE_SIZE >= getWidth()) {
                        x_axis = 0;
                    }
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            invalidate();
                        }
                    }, UPDATE_RATE);
                }
            }

            @Override
            public boolean onTouchEvent(MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    isMoving = !isMoving;
                }
                return true;
            }
        });
    }

    private int randomColor() {
        int red = (int) (Math.random() * 256);
        int green = (int) (Math.random() * 256);
        int blue = (int) (Math.random() * 256);
        return Color.rgb(red, green, blue);
    }
}