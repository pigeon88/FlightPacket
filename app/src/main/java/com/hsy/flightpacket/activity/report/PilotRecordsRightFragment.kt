package com.hsy.flightpacket.activity.report

import android.app.Fragment
import android.content.Context
import android.os.Bundle
import android.studio.view.widget.SimpleAdapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.hsy.flightpacket.R
import com.hsy.flightpacket.bean.dao.PilotBean
import com.hsy.flightpacket.bean.dao.PilotRecordTable
import kotlinx.android.synthetic.main.report_pilot_records_right.*
import kotlinx.android.synthetic.main.report_pilot_records_right_item.view.*
import java.text.SimpleDateFormat
import java.util.*

/**
 * 领航记录
 */
class PilotRecordsRightFragment : Fragment() {

    lateinit var recordsAdapter: LstRecordsAdapter
    var recordTables: List<PilotRecordTable>? = null
    var recordFooter: PilotBean? = null

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater?.inflate(R.layout.report_pilot_records_right, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recordsAdapter = LstRecordsAdapter(activity)
        lst_records.adapter = recordsAdapter

        val data = ArrayList<RecordsBean>()
        recordTables?.forEachIndexed { index, pilotRecordTable ->
            data.add(RecordsBean(
                    pilotRecordTable.title,
                    pilotRecordTable.yuqiTime,
                    pilotRecordTable.shijiTime,
                    pilotRecordTable.cihangxiang,
                    pilotRecordTable.pianliu,
                    pilotRecordTable.cihangguiji,
                    pilotRecordTable.disu,
                    pilotRecordTable.kongsu,
                    pilotRecordTable.gaodu,
                    pilotRecordTable.fuji))
        }
        recordsAdapter.setData(data)


        if (recordFooter?.dateTime != null) {
            var cal = Calendar.getInstance()
            cal.time = recordFooter?.dateTime

            tv_report_right_year.text = cal.get(Calendar.YEAR).toString()
            tv_report_right_month.text = cal.get(Calendar.MONTH).toString()
            tv_report_right_day.text = cal.get(Calendar.DATE).toString()
        }

        tv_landing_date.text = "着陆时刻" + getDateFormat(recordFooter?.landing)
        tv_renewal_date.text = "续航时刻" + getDateFormat(recordFooter?.renewal)
    }

    class LstRecordsAdapter(context: Context) : SimpleAdapter<RecordsBean>(context) {

        override fun newView(p0: Context?, p1: ViewGroup?, p2: Int): View {
            val mInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            return mInflater.inflate(R.layout.report_pilot_records_right_item, p1, false)
        }

        override fun bindView(p0: Context?, p1: View?, p2: Int) {
            val item = getItem(p2)
            p1!!.tv_item1.text = item.item1
            p1.tv_item2.text = item.getItem2Str()
            p1.tv_item3.text = item.getItem3Str()
            p1.tv_item4.text = item.item4?.toString()
            p1.tv_item5.text = item.item5?.toString()
            p1.tv_item6.text = item.item6?.toString()
            p1.tv_item7.text = item.item7?.toString()
            p1.tv_item8.text = item.item8?.toString()
            p1.tv_item9.text = item.item9?.toString()
            p1.tv_item10.text = item.item10
        }
    }
}
