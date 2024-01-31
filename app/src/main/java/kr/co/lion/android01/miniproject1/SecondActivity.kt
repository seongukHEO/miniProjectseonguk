package kr.co.lion.android01.miniproject1

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.annotation.RequiresApi
import kr.co.lion.android01.miniproject1.databinding.ActivitySecondBinding
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
        initView()

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

                    var meno = MemoClass(title, content, currentTime)

                    var newIntent = Intent()
                    newIntent.putExtra("obj1", meno)
                    setResult(RESULT_OK, newIntent)
                    finish()

                    true
                }
            }
        }

    }



    fun initView(){

    }





















}