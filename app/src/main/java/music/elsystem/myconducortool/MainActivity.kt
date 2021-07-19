package music.elsystem.myconducortool


import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

//ビットマップ画像の各ラインの最高点・最下点を操作しログに表示する。
//値の反映と同期して画像の移動も忘れないこと！！！
class MainActivity : AppCompatActivity() {
    var bmpBeat: Bitmap? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //ビットマップの生成にはActivityの継承が必要みたいなので、
        //ビットマップに対する処理はメインアクティビティに配置する。
        val mH = MeasureBmpLineHeight()
        val options = BitmapFactory.Options()
        options.inScaled = false
        bmpBeat = BitmapFactory.decodeResource(resources, R.drawable.twobeat, options)
        mH.measureBmpLineHeight(bmpBeat,2)
    }
    companion object {
        private const val tagMsg = "My_MainActivity"
    }

}