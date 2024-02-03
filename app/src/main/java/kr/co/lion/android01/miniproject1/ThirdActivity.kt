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
            if (it.resultCode == RESULT_OK){
                if (it.data != null){
                    var str1 = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU){
                        it?.data!!.getParcelableExtra("newplease", MemoClass2::class.java)
                    }else{
                        it?.data!!.getParcelableExtra<MemoClass2>("newplease")
                    }
                    activityThirdBinding.apply {
                        noTitleTextfield.setText("${str1?.newTitle}")
                        noContentTextField.setText("${str1?.newContent}")
                    }

                }
            }

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

                activityThirdBinding.apply {
                   setOnMenuItemClickListener {
                    var title = noTitleTextfield.text.toString()
                    var contect = noContentTextField.text.toString()


                        when (it.itemId) {
                        R.id.modify_menu -> {

                            var newintent = Intent(this@ThirdActivity, FourthActivity::class.java)
                            newintent.putExtra("newTitle", title)
                            newintent.putExtra("newContect", contect)
                            setResult(RESULT_OK, newintent)

                            activityFourthlauncher.launch(newintent)

                        }

                        R.id.delect_menu -> {
                            var newIntent = Intent()
                            newIntent.putExtra("obj1", MemoClass::class.java)
                            setResult(RESULT_OK, newIntent)
                            finish()
                        }
                    }


                    true
                }
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