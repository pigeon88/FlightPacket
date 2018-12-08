package com.hsy.flightpacket.activity.report

import android.app.Fragment
import android.content.Context
import android.graphics.BitmapFactory
import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import android.studio.view.widget.SimpleAdapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.hsy.flightpacket.R
import com.hsy.flightpacket.bean.dao.PilotBean
import com.hsy.flightpacket.bean.dao.PilotRecordTable
import kotlinx.android.synthetic.main.report_pilot_records_left.*
import kotlinx.android.synthetic.main.report_pilot_records_left_item.view.*
import kotlinx.android.synthetic.main.report_pilot_records_left_footer.*
import java.lang.Exception
import java.text.SimpleDateFormat
import java.util.*

/**
 * 领航记录
 */
class PilotRecordsLeftFragment : Fragment() {

    lateinit var recordsAdapter: LstRecordsAdapter
    var recordTables: List<PilotRecordTable>? = null
    var recordFooter: PilotBean? = null

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater?.inflate(R.layout.report_pilot_records_left, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recordsAdapter = LstRecordsAdapter(activity)
        lst_records.adapter = recordsAdapter

        val data = ArrayList<RecordsLeftBean>()
        recordTables?.forEachIndexed { index, pilotRecordTable ->
            data.add(RecordsLeftBean(
                    pilotRecordTable.title,
                    pilotRecordTable.didianDaihao,
                    pilotRecordTable.weidu,
                    pilotRecordTable.jingdu,
                    pilotRecordTable.cihangguiji,
                    pilotRecordTable.disu,
                    pilotRecordTable.gaodu,
                    pilotRecordTable.kongsu,
                    pilotRecordTable.juli?.toInt(),
                    getDateFormat(pilotRecordTable.shijiTime)))
        }
        recordsAdapter.setData(data)

        tx_jx_item3.text = recordFooter?.model
        /*tx_jx_item3.text = recordFooter.toString()
        tx_jx_item4.text = recordFooter.toString()
        tx_jx_item5.text = recordFooter.toString()
        tx_jx_item5.text = recordFooter.toString()
        tx_jx_item7.text = recordFooter.toString()
        tx_jx_item8.text = recordFooter.toString()
        tx_jx_item9.text = recordFooter.toString()
        tx_jx_item10.text = recordFooter.toString()*/


        tx_fxy_item2.text = recordFooter?.flyer
        /*tx_fxy_item3.text = recordFooter.toString()
        tx_fxy_item4.text = recordFooter.toString()
        tx_fxy_item5.text = recordFooter.toString()
        tx_fxy_item5.text = recordFooter.toString()
        tx_fxy_item7.text = recordFooter.toString()
        tx_fxy_item8.text = recordFooter.toString()*/

        tx_lhy_item3.text = recordFooter?.pilot
        /*tx_lhy_item3.text = recordFooter.toString()
        tx_lhy_item4.text = recordFooter.toString()
        tx_lhy_item5.text = recordFooter.toString()
        tx_lhy_item5.text = recordFooter.toString()
        tx_lhy_item7.text = recordFooter.toString()
        tx_lhy_item8.text = recordFooter.toString()
        tx_lhy_item9.text = recordFooter.toString()*/

        //tx_r3_item1.text = recordFooter?.goOutPic
        setBackgroundBitmap(tx_r3_item1, recordFooter?.goOutPic)
        //tx_r3_item2.text = recordFooter?.returnPic
        setBackgroundBitmap(tx_r3_item2, recordFooter?.returnPic)
        tx_r3_item3.text = recordFooter?.naviInfo
    }

    private fun setBackgroundBitmap(tx_r3_item1: View, goOutPic: String?) {
        if (goOutPic != null) {
            try {
                var decodeFile = BitmapFactory.decodeFile(goOutPic)
                tx_r3_item1.background = BitmapDrawable(decodeFile)
            } catch (e: Exception) {
            }
        }
    }

    class LstRecordsAdapter(context: Context) : SimpleAdapter<RecordsLeftBean>(context) {

        override fun newView(p0: Context?, p1: ViewGroup?, p2: Int): View {
            val mInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            return mInflater.inflate(R.layout.report_pilot_records_left_item, p1, false)
        }

        override fun bindView(p0: Context?, p1: View?, p2: Int) {
            val item = getItem(p2)
            p1!!.tv_item1.text = item.item1
            p1.tv_item2.text = item.item2
            p1.tv_item3.text = item.item3
            p1.tv_item4.text = item.item4
            p1.tv_item5.text = item.item5.toString()
            p1.tv_item6.text = item.item6.toString()
            p1.tv_item7.text = item.item7.toString()
            p1.tv_item8.text = item.item8.toString()
            p1.tv_item9.text = item.item9.toString()
            p1.tv_item10.text = item.item10
        }
    }
}
