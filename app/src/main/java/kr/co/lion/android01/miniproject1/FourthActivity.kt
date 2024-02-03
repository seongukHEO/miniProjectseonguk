package kr.co.lion.android01.miniproject1

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import kr.co.lion.android01.miniproject1.databinding.ActivityFourthBinding
import java.time.LocalDate

class FourthActivity : AppCompatActivity() {

    lateinit var activityFourthBinding: ActivityFourthBinding

    //받을 런쳐
    lateinit var activityMainlauncher:ActivityResultLauncher<Intent>

    //ThirtActivity에서 온 객체를 받을 변수
    var seonguk = mutableListOf<MemoClass2>()

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activityFourthBinding = ActivityFourthBinding.inflate(layoutInflater)
        setContentView(activityFourthBinding.root)

        initData()
        setToolBar()
        initView()
    }

    fun initData(){
        var contract20 = ActivityResultContracts.StartActivityForResult()
        activityMainlauncher = registerForActivityResult(contract20){

        }

    }
    @RequiresApi(Build.VERSION_CODES.O)
    fun setToolBar(){
        activityFourthBinding.apply {

            fourthToolbar.apply {
                //타이틀 설정
                title = "메모 수정"
                //아이콘 설정
                setNavigationIcon(R.drawable.arrow_back_24px)
                //아이콘을 클릭했을 때
                setNavigationOnClickListener {
                    finish()
                }

                //메뉴 설정
                inflateMenu(R.menu.fourth_menu)
                setOnMenuItemClickListener {
                    when(it.itemId){
                        //분기한다
                        R.id.changememo_menu -> {
                            var newTitle = changeTitleTextField.text.toString()
                            var newContent = changeContentTextField.text.toString()

                            var meme = MemoClass2(newTitle, newContent)
                            var newIntent = Intent()
                            newIntent.putExtra("newplease", meme)

                            setResult(RESULT_OK, newIntent)
                            finish()
                        }
                    }

                    true
                }
            }
        }

    }
    fun initView(){
        activityFourthBinding.apply {
            //기본적으로 설정되어 있는 Text
            changeTitleTextField.apply {
                var gogo = intent?.getStringExtra("newTitle")
                setText(gogo)
                //Text를 클릭할 경우 내부의 내용이 사라짐
                setOnClickListener {
                    setText("")
                }
            }
            changeContentTextField.apply {
                var gogo1 = intent?.getStringExtra("newContect")
                setText(gogo1)
                setOnClickListener {
                    setText("")
                }
            }



        }

    }
}






















































