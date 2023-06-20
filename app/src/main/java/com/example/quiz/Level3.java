package com.example.quiz;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class Level3 extends AppCompatActivity {

    Dialog dialog;
    Dialog dialogEnd;
    public int numleft;//переменна для левой картинки
    public int numright;//для правой картинки
    Array array = new Array();
    Random random = new Random();//для генерации случайных чисел
    public int count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.universal);
        //создаем переменную text_levels
        TextView text_levels = findViewById(R.id.text_levels);
        text_levels.setText(R.string.level_3);

        final ImageView img_left = (ImageView) findViewById(R.id.img_left);
        //Код скругления углов для левой картинки
        img_left.setClipToOutline(true);

        final ImageView img_right = (ImageView) findViewById(R.id.img_right);
        //Код скругления углов для правой картинки
        img_right.setClipToOutline(true);

        //уть к левой testview
        final TextView text_left = findViewById(R.id.text_left);

        //уть к левой testview
        final TextView text_right = findViewById(R.id.text_right);

        //Развернуть игру на весь экран
        Window w = getWindow();
        w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);

        ImageView background = (ImageView)findViewById(R.id.background);
        background.setImageResource(R.drawable.level3);

        //Вызов диалогового окна в начале игры

        Dialog dd =  getDialog();//показать диалоговое окно
        dd.show();


        //кнопка закрывающая диалог.окно
        TextView btnclose = (TextView) dialog.findViewById(R.id.btnclose);
        btnclose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Level3.this, GameLevels.class);
                startActivity(i);
                finish();
                //dd.dismiss();//закрытие диалогового окна
            }
        });
        //конец

        //Кнопка продолжить
        Button btncontinue = (Button) dialog.findViewById(R.id.btncontinue);

        btncontinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dd.dismiss();
            }
        });


        //кнопка назад
        Button btnback = (Button) findViewById(R.id.button_back);
        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    Intent i= new Intent(Level3.this, GameLevels.class);
                    startActivity(i); finish();
                }catch (Exception e){}
            }
        });

        //ассив для прогресса игры
        final int[] progress = {
                R.id.point1,  R.id.point2, R.id.point3, R.id.point4, R.id.point5,
                R.id.point6, R.id.point7, R.id.point8, R.id.point9, R.id.point10
        };

        final Animation a = AnimationUtils.loadAnimation(Level3.this, R.anim.alpha);


        numleft = random.nextInt(10);// генерируем случайное чсло от 0 до9
        img_left.setImageResource(array.images3[numleft]);//достаем из массива картинку
        text_left.setText(array.texts3[numleft]);//Достаем из массива текст

        numright = random.nextInt(10);// генерируем случайное чсло от 0 до9

        //цикл с предусловием проверяющее равесновто чисел
        while (array.strong[numleft]==array.strong[numright]){
            numright = random.nextInt(10);
        }
        img_right.setImageResource(array.images3[numright]);
        text_right.setText(array.texts3[numright]);//Достаем из массива текст


        //обработка нажатия на левую кнопку
        img_left.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                //условие касания картинки начало
                if (event.getAction()==MotionEvent.ACTION_DOWN){
                    //касание картинки
                    img_right.setEnabled(false);
                    if (array.strong[numleft]>array.strong[numright]) {
                        img_left.setImageResource(R.drawable.img_true);
                    }else{
                        img_left.setImageResource(R.drawable.img_false);
                    }
                    //касание - конец
                }else if (event.getAction()==MotionEvent.ACTION_UP){
                    //отпускание пальца от экрана
                    if (array.strong[numleft]>array.strong[numright]){
                        if (count<10){
                            count = count+1;
                        }
                        //ЗАКРАШИВАЕМ ПРОГРЕСС СЕРЫМ ЦВТОМ
                        for (int i=0; i<10; i++){
                            TextView tv = findViewById(progress[i]);
                            tv.setBackgroundResource(R.drawable.style_points);
                        }
                        //оПРЕДЕЛЯЕМ ПРАВИЛЬНЫЙ ОТВЕТ И ЗАКРАШИВАЕМ ЗЕЛЕНЫМ
                        for (int i=0; i<count; i++){
                            TextView tv = findViewById(progress[i]);
                            tv.setBackgroundResource(R.drawable.style_points_green);
                        }
                    }else {
                        //ЕСЛИ МЕНЬШЕ
                        if (count > 0) {
                            if (count==1) {
                                count = 0;
                            }else{
                                count=count-2;
                            }

                        }
                        for (int i=0; i<9; i++){
                            TextView tv = findViewById(progress[i]);
                            tv.setBackgroundResource(R.drawable.style_points);
                        }
                        //оПРЕДЕЛЯЕМ ПРАВИЛЬНЫЙ ОТВЕТ И ЗАКРАШИВАЕМ ЗЕЛЕНЫМ
                        for (int i=0; i<count; i++){
                            TextView tv = findViewById(progress[i]);
                            tv.setBackgroundResource(R.drawable.style_points_green);
                        }
                    }
                    if (count==10){

                        dialogEnd = new Dialog( Level3.this);//Содаем новое диалоговое окно
                        dialogEnd.requestWindowFeature(Window.FEATURE_NO_TITLE);//Скрываем заголовок
                        dialogEnd.setContentView(R.layout.dialogend);//Путь к макету даилогового окна
                        dialogEnd.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));//прозрачный фон диалогового окна
                        dialogEnd.setCancelable(false);// окно нельзя закрыть системной кнопкой.
                        dialogEnd.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);


                        LinearLayout dialogfon = (LinearLayout) dialog.findViewById(R.id.dialogfon);
                        dialogfon.setBackgroundResource(R.drawable.dialoglevel3start);

                        TextView textdescription = (TextView) dialogEnd.findViewById(R.id.textdescriptionend);
                        textdescription.setText(R.string.level3end);

                        dialogEnd.show();//показать диалоговое окно
                        //кнопка закрывающая диалог.окно
                        TextView btnclose3 = (TextView) dialogEnd.findViewById(R.id.btnclose);
                        btnclose3.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                //Обработчик нажатия кнопки
                                try {
                                    //вернуться назад к выбору
                                    Intent i = new Intent(Level3.this, GameLevels.class);
                                    startActivity(i);
                                    finish();
                                } catch (Exception e) {
                                    //кода нет
                                }
                                dialogEnd.dismiss();//закрытие диалогового окна
                            }
                        });
                        //конец

                        //Кнопка продолжить
                        Button btncontinue3 = (Button) dialogEnd.findViewById(R.id.btn_continue);
                        btncontinue3.setText(R.string.level_3end);
                        btncontinue3.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                try {
                                    Intent intent = new Intent(Level3.this, MainActivity.class);
                                    startActivity(intent);
                                    finish();
                                } catch (Exception e) {}
                                dialogEnd.dismiss();
                            }
                        });
                        //___________________________
                    }else{
                        numleft = random.nextInt(10);// генерируем случайное чсло от 0 до9
                        img_left.setImageResource(array.images3[numleft]);//достаем из массива картинку
                        text_left.setText(array.texts3[numleft]);//Достаем из массива текст

                        numright = random.nextInt(10);// генерируем случайное чсло от 0 до9

                        //цикл с предусловием проверяющее равесновто чисел
                        while (array.strong[numleft]==array.strong[numright]){
                            numright = random.nextInt(10);
                        }
                        img_right.setImageResource(array.images3[numright]);
                        text_right.setText(array.texts3[numright]);//Достаем из массива текст
                        img_right.setEnabled(true);//включили правую картинку
                    }

                }
                //Конец
                return true;
            }
        });
        //обработка левой кнопки конец

        //обработка нажатия на правую кнопку
        img_right.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                //условие касания картинки начало
                if (event.getAction()==MotionEvent.ACTION_DOWN){
                    //касание картинки
                    img_left.setEnabled(false);
                    if (array.strong[numleft]<array.strong[numright]) {
                        img_right.setImageResource(R.drawable.img_true);
                    }else{
                        img_right.setImageResource(R.drawable.img_false);
                    }
                    //касание - конец
                }else if (event.getAction()==MotionEvent.ACTION_UP){
                    //отпускание пальца от экрана
                    if (array.strong[numleft]<array.strong[numright]){
                        if (count<10){
                            count = count+1;
                        }
                        //ЗАКРАШИВАЕМ ПРОГРЕСС СЕРЫМ ЦВТОМ
                        for (int i=0; i<10; i++){
                            TextView tv = findViewById(progress[i]);
                            tv.setBackgroundResource(R.drawable.style_points);
                        }
                        //оПРЕДЕЛЯЕМ ПРАВИЛЬНЫЙ ОТВЕТ И ЗАКРАШИВАЕМ ЗЕЛЕНЫМ
                        for (int i=0; i<count; i++){
                            TextView tv = findViewById(progress[i]);
                            tv.setBackgroundResource(R.drawable.style_points_green);
                        }
                    }else {
                        //ЕСЛИ МЕНЬШЕ
                        if (count > 0) {
                            if (count==1) {
                                count = 0;
                            }else{
                                count=count-2;
                            }

                        }
                        for (int i=0; i<9; i++){
                            TextView tv = findViewById(progress[i]);
                            tv.setBackgroundResource(R.drawable.style_points);
                        }
                        //оПРЕДЕЛЯЕМ ПРАВИЛЬНЫЙ ОТВЕТ И ЗАКРАШИВАЕМ ЗЕЛЕНЫМ
                        for (int i=0; i<count; i++){
                            TextView tv = findViewById(progress[i]);
                            tv.setBackgroundResource(R.drawable.style_points_green);
                        }
                    }
                    if (count==10){

                        dialogEnd = new Dialog( Level3.this);//Содаем новое диалоговое окно
                        dialogEnd.requestWindowFeature(Window.FEATURE_NO_TITLE);//Скрываем заголовок
                        dialogEnd.setContentView(R.layout.dialogend);//Путь к макету даилогового окна
                        dialogEnd.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));//прозрачный фон диалогового окна
                        dialogEnd.setCancelable(false);// окно нельзя закрыть системной кнопкой.
                        dialogEnd.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);

                        LinearLayout dialogfon = (LinearLayout) dialog.findViewById(R.id.dialogfon);
                        dialogfon.setBackgroundResource(R.drawable.dialoglevel3start);

                        TextView textdescription = (TextView) dialogEnd.findViewById(R.id.textdescriptionend);
                        textdescription.setText(R.string.level3end);
                        //Выход из уровня
                        dialogEnd.show();//показать диалоговое окно
                        //кнопка закрывающая диалог.окно
                        TextView btnclose2 = (TextView) dialogEnd.findViewById(R.id.btnclose);
                        btnclose2.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                //Обработчик нажатия кнопки
                                try {
                                    //вернуться назад к выбору
                                    Intent i = new Intent(Level3.this, GameLevels.class);
                                    startActivity(i);
                                    finish();
                                } catch (Exception e) {
                                    //кода нет
                                }
                                dialogEnd.dismiss();//закрытие диалогового окна
                            }
                        });
                        //конец

                        //Кнопка продолжить
                        Button btncontinue2 = (Button) dialogEnd.findViewById(R.id.btn_continue);
                        btncontinue2.setText(R.string.level_3end);
                        btncontinue2.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                try {
                                    Intent intent = new Intent(Level3.this, MainActivity.class);
                                    startActivity(intent);
                                    finish();
                                } catch (Exception e) {}

                                dialogEnd.dismiss();
                            }
                        });
                        //___________________________
                    }else{
                        numleft = random.nextInt(10);// генерируем случайное чсло от 0 до9
                        img_left.setImageResource(array.images3[numleft]);//достаем из массива картинку
                        text_left.setText(array.texts3[numleft]);//Достаем из массива текст

                        numright = random.nextInt(10);// генерируем случайное чсло от 0 до9

                        //цикл с предусловием проверяющее равесновто чисел
                        while (array.strong[numleft]==array.strong[numright]){
                            numright = random.nextInt(10);
                        }
                        img_right.setImageResource(array.images3[numright]);
                        text_right.setText(array.texts3[numright]);//Достаем из массива текст
                        img_left.setEnabled(true);//включили левую картинку
                    }
                }
                return true;
            }
        });
        //___________________________
        //Вызов диалогового окна в конце игры

    }

    private Dialog getDialog() {
        dialog = new Dialog( this);//Содаем новое диалоговое окно
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);//Скрываем заголовок
        dialog.setContentView(R.layout.previewdialog);//Путь к макету даилогового окна
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));//прозрачный фон диалогового окна
        dialog.setCancelable(false);// окно нельзя закрыть системной кнопкой.

        ImageView previewimg = (ImageView) dialog.findViewById(R.id.previewimg);
        previewimg.setImageResource(R.drawable.level3alert);

        TextView textdescription = (TextView) dialog.findViewById(R.id.textdescription);
        textdescription.setText(R.string.level3);

        LinearLayout dialogfon = (LinearLayout) dialog.findViewById(R.id.dialogfon);
        dialogfon.setBackgroundResource(R.drawable.dialoglevel3start);

        return dialog;
    }


    //Ситемная кнопка "Назад"
    @Override
    public void onBackPressed() {
        Intent i = new Intent(getApplicationContext(), GameLevels.class);
        startActivity(i);
        finish();
    }

}