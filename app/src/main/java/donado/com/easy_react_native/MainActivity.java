package donado.com.easy_react_native;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private final int OVERLAY_PERMISSION_REQ_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.btn_to_react_native).setOnClickListener(this);
        findViewById(R.id.btn_to_react_nativeB).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_to_react_native:
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    if (!Settings.canDrawOverlays(this)) {
                        Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION,
                                Uri.parse("package:" + getPackageName()));
                        startActivityForResult(intent, OVERLAY_PERMISSION_REQ_CODE);
                    } else {
                        startReactNativeActivityWithBundleName("AwesomeProject");
                    }
                }
                break;
            case R.id.btn_to_react_nativeB:
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    if (!Settings.canDrawOverlays(this)) {
                        Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION,
                                Uri.parse("package:" + getPackageName()));
                        startActivityForResult(intent, OVERLAY_PERMISSION_REQ_CODE);
                    } else {
                        startReactNativeActivityWithBundleName("issuesB");
                    }
                }
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == OVERLAY_PERMISSION_REQ_CODE) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (!Settings.canDrawOverlays(this)) {
                } else {
                    Intent intent = new Intent(MainActivity.this,  ReactNativeActivity.class);
                    startActivity(intent);
                }
            }
        }
    }

    private void startReactNativeActivityWithBundleName(String bundleName){
        Intent intent = new Intent(MainActivity.this,  ReactNativeActivity.class);
        intent.putExtra("bundleName",bundleName);
        startActivity(intent);
    }
}
