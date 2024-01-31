package kr.co.lion.android01.miniproject1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kr.co.lion.android01.miniproject1.databinding.ActivityFourthBinding

class FourthActivity : AppCompatActivity() {

    lateinit var activityFourthBinding: ActivityFourthBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activityFourthBinding = ActivityFourthBinding.inflate(layoutInflater)
        setContentView(activityFourthBinding.root)

        initData()
        setToolBar()
        initView()
    }

    fun initData(){

    }
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
                        R.id.changememo_menu -> {
                            var newIntent = Intent(this@FourthActivity, MainActivity::class.java)
                            startActivity(newIntent)
                        }
                    }

                    true
                }
            }
        }

    }
    fun initView(){

    }
}






















































