package kr.co.lion.android01.miniproject1

import android.content.DialogInterface
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.core.view.isEmpty
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.textfield.TextInputEditText
import kr.co.lion.android01.miniproject1.databinding.ActivitySecondBinding
import kr.co.lion.androidproject1test.Util
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class SecondActivity : AppCompatActivity() {

    lateinit var activitySecondBinding: ActivitySecondBinding

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activitySecondBinding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(activitySecondBinding.root)

        initData()
        setToolBar()
        //showDiaLog()

    }

    fun initData(){


    }
    @RequiresApi(Build.VERSION_CODES.O)
    fun setToolBar(){
        activitySecondBinding.apply {
            secondToolbar.apply {
                //타이틀 설정
                title = "메모 작성"

                //아이콘 설정
                setNavigationIcon(R.drawable.arrow_back_24px)

                setNavigationOnClickListener {
                    finish()
                }
                //메뉴 형성
                inflateMenu(R.menu.second_menu)
                //메뉴를 클릭했을 때?
                setOnMenuItemClickListener {

                    var title = titleTextField.text.toString()
                    var content = contentTextField.text.toString()
                    var currentTime = LocalDate.now().toString()

                    if (title.isEmpty()){
                        showDiaLog("제목 입력 오류", "제목을 입력해주세요")


                    }else if (content.isEmpty()){
                        showDiaLog("내용 입력 오류", "내용을 입력해주세요")
                    }else {
                        var meno = MemoClass(title, content, currentTime)

                        var newIntent = Intent()
                        newIntent.putExtra("obj1", meno)
                        setResult(RESULT_OK, newIntent)
                        finish()
                    }

                        true

                }
                Util.showSoftInput(activitySecondBinding.titleTextField, this@SecondActivity)
            }
        }

    }



    fun showDiaLog(title:String, message:String){
        //DiaLog를 보여준다
        var viewDiaLog = MaterialAlertDialogBuilder(this@SecondActivity).apply {
            setTitle(title)
            setMessage(message)
            setPositiveButton("확인"){ dialogInterface: DialogInterface, i: Int ->


            }

        }
        viewDiaLog.show()

    }





















}