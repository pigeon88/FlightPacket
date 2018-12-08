package com.hsy.flightpacket.activity.report

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.studio.view.widget.SimpleAdapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.hsy.flightpacket.R
import com.hsy.flightpacket.activity.BaseActivity
import com.hsy.flightpacket.views.TrainingProgressTextView
import kotlinx.android.synthetic.main.report_student_training_progress.*
import kotlinx.android.synthetic.main.report_student_training_progress_item.view.*
import java.io.Serializable
import java.util.concurrent.TimeUnit

data class StudentBean(
        var name: String, //姓名
        var trains: List<StudentTrain>
) : Serializable {
    fun getStudentTrain(studentBean: String): StudentTrain? {
        var studentTrain: StudentTrain? = null
        for (train in trains) {
            if (train.planDate == studentBean) {
                studentTrain = train
                break
            }
        }
        return studentTrain
    }
}

data class StudentTrain(
        var planDate: String, //计划时间
        var itemUp: String,
        var itemDown: String
) : Serializable {
    fun getItemUpStr(): String? {
        val builder = StringBuilder()
        if (itemUp != null) {
            val itemUpLong = itemUp.toLong()
            var hoursOne = TimeUnit.HOURS.toMinutes(1)
            if (itemUpLong >= hoursOne) {
                var hour = itemUp.toLong() / hoursOne
                var minutes = itemUp.toLong() % hoursOne
                builder.append(String.format("%02d:%02d", hour, minutes))
            } else {
                builder.append(String.format("00:%02d", itemUpLong))
            }
        }
        return builder.toString()
    }
}

class StudentTrainingProgressActivity : BaseActivity() {

    var lstStudentAdapter: LstStudentAdapter? = null
    var header: List<String>? = null
    var studentBeans: List<StudentBean>? = null

    companion object {
        @JvmStatic
        fun start(context: Context, header: List<String>, studentBeans: List<StudentBean>) {
            var intent = Intent(context, StudentTrainingProgressActivity::class.java)
            intent.putExtra("header", header as Serializable)
            intent.putExtra("studentBeans", studentBeans as Serializable)
            context.startActivity(intent)
        }
    }

    override fun getContentLayoutId(): Int {
        return R.layout.report_student_training_progress
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        title = "学员训练进度"

        header = intent.getSerializableExtra("header") as List<String>
        studentBeans = intent.getSerializableExtra("studentBeans") as List<StudentBean>

        /*if (header?.size == 0) {
            header = arrayListOf("2018-08-06", "2018-08-07"*//*, "2018-08-08", "2018-08-09", "2018-08-10"*//*)
            val studentBeans_ = mutableListOf<StudentBean>()
            studentBeans_.add(StudentBean("张三", arrayListOf(
                    StudentTrain("2018-08-06", "100", "3"),
                    StudentTrain("2018-08-07", "100", "3")
            )))
            studentBeans_.add(StudentBean("李四", arrayListOf(
                    StudentTrain("2018-08-06", "200", "4"),
                    StudentTrain("2018-08-07", "200", "4")
            )))
            studentBeans = studentBeans_
        }*/

        lstStudentAdapter = LstStudentAdapter(this, header)
        lst_student.adapter = lstStudentAdapter

        tv_header_date.removeAllViewsInLayout()
        header?.forEachIndexed { index, studentBean ->
            var textView = LayoutInflater.from(this).inflate(R.layout.report_student_training_progress_head_text, tv_header_date, false) as TextView
            textView.text = studentBean
            tv_header_date.addView(textView)
        }

        lstStudentAdapter?.setData(studentBeans)
    }

    class LstStudentAdapter(context: Context, val header: List<String>?) : SimpleAdapter<StudentBean>(context) {

        override fun newView(p0: Context?, p1: ViewGroup?, p2: Int): View {
            val mInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            return mInflater.inflate(R.layout.report_student_training_progress_item, p1, false)
        }

        override fun bindView(p0: Context?, p1: View?, p2: Int) {
            val item = getItem(p2)
            p1!!.tv_item1.text = item.name
            p1.ll_body_date.removeAllViewsInLayout()
            header?.forEachIndexed { index, studentBean ->
                var studentTrain: StudentTrain? = item.getStudentTrain(studentBean)
                //var textView = TrainingProgressTextView(context)
                var textView = LayoutInflater.from(context).inflate(R.layout.report_student_training_progress_item_text, p1.ll_body_date, false) as TrainingProgressTextView
                textView.setText(studentTrain?.getItemUpStr(), studentTrain?.itemDown)
                p1.ll_body_date.addView(textView)
            }
        }
    }
}
