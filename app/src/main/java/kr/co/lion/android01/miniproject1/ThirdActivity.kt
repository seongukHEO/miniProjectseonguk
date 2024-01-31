package kr.co.lion.android01.miniproject1

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import kr.co.lion.android01.miniproject1.databinding.ActivityThirdBinding

class ThirdActivity : AppCompatActivity() {

    lateinit var activityThirdBinding: ActivityThirdBinding

    //FourthActivity를 위한 런쳐
    lateinit var activityFourthlauncher:ActivityResultLauncher<Intent>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityThirdBinding = ActivityThirdBinding.inflate(layoutInflater)
        setContentView(activityThirdBinding.root)

        initData()
        setToolBar()
        initView()
    }

    fun initData(){
        var contract3 = ActivityResultContracts.StartActivityForResult()
        activityFourthlauncher = registerForActivityResult(contract3){

        }

    }
    fun setToolBar(){
        activityThirdBinding.apply {
            thirdToolbar.apply {
                //타이틀 설정
                title = "메모 보기"

                //아이콘 설정
                setNavigationIcon(R.drawable.arrow_back_24px)
                //아이콘을 클릭했을 때
                setNavigationOnClickListener {
                    finish()
                }
                //메뉴 설정
                inflateMenu(R.menu.thirth_menu)
                setOnMenuItemClickListener {
                    when(it.itemId){
                        R.id.modify_menu -> {
                            var intent = Intent(this@ThirdActivity, FourthActivity::class.java)
                            activityFourthlauncher.launch(intent)
                        }
                        R.id.delect_menu -> {
                            var newIntent = Intent()
                            finish()
                        }
                    }


                    true
                }
            }
        }

    }
    fun initView(){
        activityThirdBinding.apply {
            var str1 = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU){
                intent.getParcelableExtra("obj1", MemoClass::class.java)
            }else{
                intent.getParcelableExtra("obj1")
            }
            noTitleTextfield.setText("${str1?.title}")
            noTimeTextField.setText("${str1?.currentTime}")
            noContentTextField.setText("${str1?.contect}")
        }

    }
}