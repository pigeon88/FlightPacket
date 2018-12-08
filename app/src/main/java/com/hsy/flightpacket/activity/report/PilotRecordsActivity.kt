package com.hsy.flightpacket.activity.report

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.hsy.flightpacket.R
import com.hsy.flightpacket.activity.BaseActivity
import com.hsy.flightpacket.bean.dao.PilotBean
import com.hsy.flightpacket.bean.dao.PilotRecordTable
import kotlinx.android.synthetic.main.report_pilot_records.*
import java.io.Serializable
import java.util.*

/**
 * 领航记录
 */
class PilotRecordsActivity : BaseActivity() {

    private lateinit var recordTablesLeft: List<PilotRecordTable>
    private lateinit var recordTablesFooterBean: PilotBean
    private lateinit var recordTablesRight: List<PilotRecordTable>

    companion object {
        @JvmStatic
        fun start(context: Context,
                  recordTablesLeft: List<PilotRecordTable>,
                  recordTablesLeftFooter: PilotBean,
                  recordTablesRight: List<PilotRecordTable>) {
            val intent = Intent(context, PilotRecordsActivity::class.java)
            intent.putExtra("recordTablesLeft", recordTablesLeft as Serializable)
            intent.putExtra("recordTablesLeftFooter", recordTablesLeftFooter)
            intent.putExtra("recordTablesRight", recordTablesRight as Serializable)
            context.startActivity(intent)
        }
    }

    override fun getContentLayoutId(): Int {
        return R.layout.report_pilot_records
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        title = "领航记录表"
        recordTablesLeft = intent.getSerializableExtra("recordTablesLeft") as List<PilotRecordTable>
        recordTablesFooterBean = intent.getSerializableExtra("recordTablesLeftFooter") as PilotBean
        recordTablesRight = intent.getSerializableExtra("recordTablesRight") as List<PilotRecordTable>


        if (recordTablesFooterBean.dateTime != null) {
            var cal = Calendar.getInstance()
            cal.time = recordTablesFooterBean.dateTime

            tv_report_year.text = cal.get(Calendar.YEAR).toString()
            tv_report_month.text = cal.get(Calendar.MONTH).toString()
            tv_report_day.text = cal.get(Calendar.DATE).toString()
        }

        var pilotRecordsLeftFragment = PilotRecordsLeftFragment()
        pilotRecordsLeftFragment.recordTables = recordTablesLeft
        pilotRecordsLeftFragment.recordFooter = recordTablesFooterBean
        fragmentManager.beginTransaction()
                .replace(R.id.left_layout, pilotRecordsLeftFragment)
                .commitAllowingStateLoss()

        var pilotRecordsRightFragment = PilotRecordsRightFragment()
        pilotRecordsRightFragment.recordTables = recordTablesRight
        pilotRecordsRightFragment.recordFooter = recordTablesFooterBean
        fragmentManager.beginTransaction()
                .replace(R.id.right_layout, pilotRecordsRightFragment)
                .commitAllowingStateLoss()
    }
}
