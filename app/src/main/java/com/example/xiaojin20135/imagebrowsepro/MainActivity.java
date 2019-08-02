package com.example.xiaojin20135.imagebrowsepro;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private Button show_btn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        show_btn = (Button)findViewById(R.id.show_btn);
        show_btn.setOnClickListener(new ShowImageListener());
    }


    class ShowImageListener implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            ArrayList<String> photos = new ArrayList<>();
            photos.add("https://desk-fd.zol-img.com.cn/t_s2560x1440c5/g2/M00/05/09/ChMlWl1BAz-IcV0oADKEXBJ0ncgAAMP0gAAAAAAMoR0279.jpg");
            photos.add("https://desk-fd.zol-img.com.cn/t_s2560x1440c5/g2/M00/05/09/ChMlWV1BA0uIJD2cACgyyOBAl4YAAMP0gOPNF0AKDLg887.jpg");
            photos.add("https://article-fd.zol-img.com.cn/g2/M00/0E/00/ChMlWVyJwQeIRQrvAA_BjB8NhecAAIyDANWGdgAD8Gk692.jpg");
            photos.add("https://b.zol-img.com.cn/desk/bizhi/image/8/4096x2160/1563934008198.png");
            photos.add("https://desk-fd.zol-img.com.cn/t_s4096x2160c5/g2/M00/02/06/ChMlWV03v-eIOEWoAC0lpucbl_sAAMC8AFTL9QALSW-183.jpg");
            photos.add("https://desk-fd.zol-img.com.cn/t_s4096x2160c5/g2/M00/02/06/ChMlWl03wq6IbWwqAA-IxrPijHEAAMDAwJ0cR8AD4je242.jpg");
            photos.add("https://desk-fd.zol-img.com.cn/t_s4096x2160c5/g2/M00/02/06/ChMlWl03v_aISd7vABOqKe2IAXEAAMC8QJgIh4AE6pB2971212.jpg");
            Bundle bundle = new Bundle ();
            bundle.putStringArrayList ("imageList", photos);
            bundle.putBoolean("fromNet",true);
            bundle.putInt("index",2);
            Intent intent = new Intent(MainActivity.this,ImageBrowserActivity.class);
            intent.putExtras(bundle);
            startActivity(intent);
        }
    }


}
