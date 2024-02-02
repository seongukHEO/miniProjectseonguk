package kr.co.lion.android01.miniproject1

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import kr.co.lion.android01.miniproject1.databinding.ActivityFourthBinding

class FourthActivity : AppCompatActivity() {

    lateinit var activityFourthBinding: ActivityFourthBinding

    //받을 런쳐
    lateinit var activityMainlauncher:ActivityResultLauncher<Intent>

    //ThirtActivity에서 온 객체를 받을 변수
    var seonguk = mutableListOf<MemoClass>()

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
    fun setToolBar(){
        activityFourthBinding.apply {

               changeTitleTextField.apply {
                    val gogo1 = intent.getStringExtra("title").toString()
                    setText(gogo1)
                    setOnClickListener {
                        //TextField를 클릭할 경우 입력된 값이 사라진다
                        setText("")
                    }
                }
                changeContentTextField.apply {
                    var gogo2 = intent.getStringExtra("contect").toString()
                    setText(gogo2)
                    setOnClickListener {
                        setText("")
                    }
                   // Log.e("test1234", "${title}")
                }


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
                        R.id.changememo_menu -> {
                            var title = changeTitleTextField.text.toString()
                            var content = changeContentTextField.text.toString()


                            var meme = MemoClass2(title, content)

                            var newIntent = Intent(this@FourthActivity, MainActivity::class.java)
                            newIntent.putExtra("newplease", meme)

                            setResult(RESULT_OK, newIntent)
                            startActivity(newIntent)
                        }
                    }

                    true
                }
            }
        }

    }
    fun initView(){
        activityFourthBinding.apply {


        }

    }
}






















































