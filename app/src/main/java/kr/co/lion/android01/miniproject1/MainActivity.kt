package kr.co.lion.android01.miniproject1

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ViewGroup
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kr.co.lion.android01.miniproject1.databinding.ActivityMainBinding
import kr.co.lion.android01.miniproject1.databinding.RecyclerviewBinding

class MainActivity : AppCompatActivity() {

    lateinit var activityMainBinding: ActivityMainBinding

    //SecondActivity를 받기 위한 런쳐
    lateinit var secondActivitylauncher:ActivityResultLauncher<Intent>

    //FourthActivity를 받기 위한 런쳐
    lateinit var FourthActivitylauncher:ActivityResultLauncher<Intent>

    //TirthActivity를 받기 위한 객체
    lateinit var ThirtActivitylauncher:ActivityResultLauncher<Intent>

    //정보를 담을 객체
    var newList = mutableListOf<MemoClass>()

    //ThirdActivity에서 받아온 값을 담을 객체
    var pleaseList = mutableListOf<MemoClass>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)

        initData()
        setToolBar()
        initView()
        setState()

    }

    fun initData(){
        var contract = ActivityResultContracts.StartActivityForResult()
        secondActivitylauncher = registerForActivityResult(contract){
            if(it.resultCode == RESULT_OK){
                if (it.data != null){
                    //버전 관리
                    var info1 = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU){
                        it?.data!!.getParcelableExtra("obj1", MemoClass::class.java)
                    }else{
                        it?.data!!.getParcelableExtra("obj1")
                    }
                    newList.add(info1!!)
                    activityMainBinding.recyclerview.adapter?.notifyDataSetChanged()

                }
            }

        }

        var contract2 = ActivityResultContracts.StartActivityForResult()
        FourthActivitylauncher = registerForActivityResult(contract2){

        }

        var contract4 = ActivityResultContracts.StartActivityForResult()
        ThirtActivitylauncher = registerForActivityResult(contract4){
            if (it.resultCode == RESULT_OK){
                if (it.data != null){
                    //버전별 관리
                    var please = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU){
                        it?.data!!.getParcelableExtra("obj1", MemoClass::class.java)
                    }else{
                        it?.data!!.getParcelableExtra<MemoClass>("obj1")
                    }
                    newList.remove(please)
                    activityMainBinding.recyclerview.adapter?.notifyDataSetChanged()

                }
            }
        }


    }

    fun setToolBar(){
        activityMainBinding.apply {
            mainToolbar.apply {
                //타이틀 설정
                title = "메모 관리"
                //메뉴 아이콘
                inflateMenu(R.menu.main_menu)
                //메뉴를 클릭했을때
                setOnMenuItemClickListener {
                    when(it.itemId){
                        R.id.gosecond_menu -> {
                            var newIntent = Intent(this@MainActivity, SecondActivity::class.java)
                            secondActivitylauncher.launch(newIntent)
                        }
                    }

                    true
                }

            }
        }
    }

    fun initView(){
        activityMainBinding.apply {
            recyclerview.apply {
                //어댑터 객체 생성
                adapter = RecylcerViewAdapter()
                //레이아웃
                layoutManager = LinearLayoutManager(this@MainActivity)
            }
        }

    }
    fun setState(){

    }

    //adapter class 형성
    inner class RecylcerViewAdapter:RecyclerView.Adapter<RecylcerViewAdapter.ViewHolderClass>(){

        //viewHolderClass 생성
        inner class ViewHolderClass(recyclerviewBinding: RecyclerviewBinding):RecyclerView.ViewHolder(recyclerviewBinding.root){
            var recyclerviewBinding:RecyclerviewBinding

            init {
                this.recyclerviewBinding = recyclerviewBinding

                //가로 ,세로의 길이를 정의한다
                this.recyclerviewBinding.root.layoutParams = ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
                )

                //클릭했을 때 ThirdActivity로의 이동
                this.recyclerviewBinding.root.setOnClickListener {
                    var newIntent = Intent(this@MainActivity, ThirdActivity::class.java)
                    newIntent.putExtra("obj1", newList[adapterPosition])
                    ThirtActivitylauncher.apply {
                        newList.removeAt(adapterPosition)
                        launch(newIntent)
                    }
                }
            }
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderClass {
            //viewBinding
            var recyclerviewBinding = RecyclerviewBinding.inflate(layoutInflater)
            //viewHolder
            var viewHolderClass = ViewHolderClass(recyclerviewBinding)
            return viewHolderClass
        }

        override fun getItemCount(): Int {
            return newList.size
        }

        override fun onBindViewHolder(holder: ViewHolderClass, position: Int) {
            holder.recyclerviewBinding.titleTextView.text = "제목 : ${newList[position].title}"
            holder.recyclerviewBinding.timeTextView.text = "날짜 : ${newList[position].currentTime}"
        }
    }



































}