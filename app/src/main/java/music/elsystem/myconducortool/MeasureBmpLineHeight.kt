package music.elsystem.myconducortool

import android.graphics.Bitmap
import android.util.Log

//ビットマップデータより各拍および裏拍の最上点および最下点を取得する関数。
//結果はログ出力し、列挙型データに手で登録する。
class MeasureBmpLineHeight() {
    fun measureBmpLineHeight(bmpBeat: Bitmap?, rhythm: Int) {
        var intArgb = 0
        var strRgb = ""
        //現在6拍子をMAXと想定している。
        //この関数では、一拍表、裏といった数え方はせず、２拍子なら0,1,2,3と数える。
        //この関数では、一拍目表へのラインを一拍目表と呼ぶ。
        var startY = IntArray(12){it * 0} //0で初期化
        var endY = IntArray(12){it * 0} //0で初期化
        val rgbY = arrayOf(
            "ff0000",           //一拍目
            "ff7f00",
            "ff00",             //二拍目
            "ff7f",
            "ff",               //三拍目
            "7f00ff",
            "ffff00",           //四拍目
            "ffff7f",
            "ffff",             //五拍目
            "7fffff",
            "ff00ff",           //六拍目
            "ff7fff"
        )
        bmpBeat?.let {
            Log.i(tagMsg,"bmpBeat.width: ${bmpBeat.width}")
            Log.i(tagMsg,"bmpBeat.height: ${bmpBeat.height}")
            for (i in 0 until rhythm * 2) {
                for (y in 0 until bmpBeat.height) {
                    for (x in 0 until bmpBeat.width) {
                        intArgb = it.getPixel(x, y)
                        strRgb = (intArgb and 0xffffff).toString(16)
                        if (strRgb == rgbY[i]) {
                            if (startY[i] == 0) {
                                startY[i] = y
                            }
                            endY[i] = y
                        }
                    }
                }
//                Log.i(tagMsg, "${(i + 2) / 2f}拍目最上点は ${startY[i]} 最下点は　${endY[i]}")
            }
        } ?: Log.i(tagMsg, "ビットマップデータがnullです！！")
        //以下に書き出される内容を本体モジュールにコピペする。
        //最後の","は手で取ること。
        Log.i("@@@" ,"highestPosition = listOf(${startY[0]},${startY[1]},${startY[2]}," +
                "${startY[3]},${startY[4]},${startY[5]},${startY[6]},${startY[7]},${startY[8]}," +
                "${startY[9]},${startY[10]},${startY[11]})")
        Log.i("@@@" ,"lowestPosition = listOf(${endY[0]},${endY[1]},${endY[2]}," +
                "${endY[3]},${endY[4]},${endY[5]},${endY[6]},${endY[7]},${endY[8]}," +
                "${endY[9]},${endY[10]},${endY[11]})")
    }

    companion object {
        private const val tagMsg = "My_mkLogicalPosition : "
    }
}