package com.study.appproject0120;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;

public class MyCustomButton extends AppCompatButton {
    //순수 자바 코드에서 생성될 때, 호출되는 생성자
  public MyCustomButton(Context context){
      super(context);
      this.setText("버튼3");
  }

  //XML에서 이 버튼을 사용하고자 할때, xml에 지정된 버튼 속성이 AttributeSet
  public MyCustomButton(@NonNull Context context, @Nullable AttributeSet attrs) {
    super(context, attrs);
  }
}
