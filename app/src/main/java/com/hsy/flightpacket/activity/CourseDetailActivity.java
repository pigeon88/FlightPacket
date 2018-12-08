package com.hsy.flightpacket.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.hsy.flightpacket.R;
import com.hsy.flightpacket.activity.UtilsActivity.PhotoViewActivity;
import com.hsy.flightpacket.adapter.ImageListAdapter;
import com.hsy.flightpacket.adapter.StudentListAdapter;
import com.hsy.flightpacket.baseAdapter.ListViewDataAdapter;
import com.hsy.flightpacket.baseAdapter.ViewHolderBase;
import com.hsy.flightpacket.baseAdapter.ViewHolderCreator;
import com.hsy.flightpacket.bean.ImagePic;
import com.hsy.flightpacket.bean.dao.PlanRecord;
import com.hsy.flightpacket.bean.dao.Student;
import com.hsy.flightpacket.db.DataBaseUtils;
import com.hsy.flightpacket.util.TimeFormat;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by xiongweimin on 2018/6/25.
 */

public class CourseDetailActivity extends BaseActivity {

    public static final String KEY_COURSE_PIC = "key_course_pic";
    @Bind(R.id.tv_1)
    TextView tv1;
    @Bind(R.id.iv_1)
    ImageView iv1;
    @Bind(R.id.tv_2)
    TextView tv2;
    @Bind(R.id.iv_2)
    ImageView iv2;
    @Bind(R.id.iv_3)
    ImageView iv3;
    @Bind(R.id.tv_3)
    TextView tv3;
    @Bind(R.id.sc_content)
    ScrollView scContent;
    @Bind(R.id.lst_image)
    ListView lstImage;

    private List<String> pics;

    ListViewDataAdapter<String> dataAdapter;

    public static void start(Context context, int type, ArrayList<String> pics) {
        Intent intent = new Intent(context, CourseDetailActivity.class);
        intent.putExtra("type", type);
        intent.putStringArrayListExtra(KEY_COURSE_PIC, pics);
        context.startActivity(intent);
    }

    public static void start(Context context, int type) {
        Intent intent = new Intent(context, CourseDetailActivity.class);
        intent.putExtra("type", type);
        context.startActivity(intent);
    }

    @Override
    public int getContentLayoutId() {
        return R.layout.activity_course_detail;
    }

    /**
     * 加载适配器
     */
    private void initAdapter() {
        lstImage.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                PhotoViewActivity.startPics(CourseDetailActivity.this, (ArrayList<String>) pics, i, 1);
            }
        });
        //配置适配器
        dataAdapter = new ListViewDataAdapter<>(new ViewHolderCreator<String>() {
            @Override
            public ViewHolderBase<String> createViewHolder() {
                return new ImageListAdapter();
            }
        });
        lstImage.setAdapter(dataAdapter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getBaseNavigationBar().setTitle("流体属性与流体力学");
        pics = getIntent().getExtras().getStringArrayList(KEY_COURSE_PIC);
        int a = new Random().nextInt(10);
        String top = null;
        String bottom = null;
        switch (a) {
            case 0:
                top = "物体与空气作相对运动时作用在物体上的力，简称气动力。它由两个分布力系组成：一是沿物体表面面元法线方向的法向分布力系，另一是在表面面元切平面上的切向分布力系。空气动力通常就是指这两个力系的合力。以飞行器(如飞机)为例，为便于对飞行器的运动规律进行分析，通常取一个原点位于飞行器重心的气流坐标系，将空气动力分解为三个方向上的分量。设坐标系的x轴平行于气流方向且正向与气流方向相反，y轴在飞行器对称面内与x轴垂直且正向指向飞行器上方，z轴垂直于xy平面，指向右翼，则合力在x、y、z三个轴上的分量分别称为阻力、举力和侧向力。若空气动力作用点与飞行器重心不重合，则飞行器还受到一个合力矩的作用，它在x、y、z三个轴上的分量分别称为滚转力矩、偏航力矩和俯仰力矩。飞行器所受的空气动力与它的飞行速度、高度和飞行姿态有关。空气动力的分布和大小是飞行器结构和强度设计的依据，而且关系到飞行器的飞行性能、操纵性能和稳定性。空气动力学的一个主要任务就是确定飞行器的空气动力。确定空气动力需要知道空气的性质和运动规律。相应于低速流动、亚声速流动、跨声速流动、超声速流动、高超声速流动、稀薄气体流动和高温气体流动等不同情况，空气动力的分析有不同的理论和实验方法";
                bottom = "若空气动力作用点与飞行器重心不重合，则飞行器还受到一个合力矩的作用，它在x、y、z三个轴上的分量分别称为滚转力矩、偏航力矩和俯仰力矩。";
                break;
            case 1:
                top = "机翼的主要功用是产生升力,以支持飞机在空中飞行;也起一定的稳定和操纵作用。在机翼上一般安装有副翼和襟翼。操纵副翼可使飞机滚转;放下襟翼能使机翼升力增大。另外,机翼上还可安装发动机、起落架和油箱等。机翼有各种形状,数目也有不同。历史上曾出现过双翼机,甚至还出现过多翼机。但现代飞机一般都是单翼机";
                bottom = "拉驾驶盘,升降舵上偏,机头上仰;前推驾驶盘,则升降舵下偏,机头下俯。向左压驾驶盘,左边副翼上偏,右边副翼下偏,飞机向左滚转;反之,向右压驾驶盘右副翼上偏,左副翼下偏,飞机向右滚转。向前蹬左脚蹬板(即蹬左舵),方向舵左偏,机头向偏转;反之,向前蹬右脚蹬板(即蹬右舵),方向舵右偏,机头向右偏转矩形机翼制造简单,但阻力较大,因此一般用于旧式飞机和现代的小型飞机。为了适应提高飞行速度的需要,解决阻力与飞行速度之间的矛盾,后来又制造出了梯形翼和椭圆翼。椭圆翼的阻力(诱导阻力)最小,但因制造复杂,未被广泛采用。梯形翼的阻力也较小,制造也简单,因而是目前活塞式发动机飞机用的最多的一种机翼。";
                break;
            case 2:
                top = "脚蹬，坐下后在脚底下。相当于方向盘，连接着飞机垂直尾翼上的方向舵。负责飞机在水平面方向的偏转，如蹬左脚收右脚飞机就向右偏转，反之亦然";
                bottom = "操纵杆，就是最显眼的那个秆子或是半个盘子的摸样。连接着机翼和水平尾翼的升降舵。向前推，机头下俯，飞机就下降，向后拉，机头就上仰，飞机就上升。向左推，飞机就向左横滚，向右腿，飞机就向右横滚。\n" +
                        "关键是几个操纵部件需要配合使用，比如右转弯，要收油门，向右蹬脚蹬，向右推操纵杆。关键是配合。转弯的时候不能只蹬脚蹬，还要向左或右推一点操纵杆让机身适当倾斜才好转弯。就象骑自行车转弯，不能光转车把，身体也要配合着倾斜一点才行。\n" +
                        "起飞注意加速到起飞速度后不要太猛拉杆，要保证机头抬起的时候机尾不能擦地。\n" +
                        "降落比较复杂，向前推操纵杆可以下降高度，但接地时总不能让机头先落地吧。所以降落在对正跑道后，让飞机下降高度，到一定时候要拉杆保证机头上仰，让后机轮先着地，还要注意角度，不能擦着机尾。后轮着地后，迅速推杆，让机头下落，关油门，刹车。";
                break;
            case 3:
                top = "一种特殊的飞行器，直升机的升力和推力均由螺旋桨的旋转获得，这就决定了其动力和操作系统与固定翼飞机的必然不同。";
                bottom = "直升机和固定翼飞机不太一样，它有两根操纵杆而不像固定翼飞机只有操纵杆节流阀和踏板，直升机主要的飞行控制装置,一般固定翼飞机的飞行原理从根本上说是对各部位机翼的状态进行调节，在机身周围制造气压差来完成各种飞行动作，而且其发动机只能提供向前的推力。但是直升机的主副螺旋桨可在水平或者垂直方向上对机身提供动力，这就使得直升机不需要向固定翼飞机那样需要巨大的机翼，二者的区别非常明显，今天我们来具体介绍一下直升机以及驾驶技术。";
                break;
            case 4:
                top = "A.总距操纵杆（Collective位于驾驶席的左侧，该手柄的控制对象为主螺旋桨下自动倾斜器的动环，动环通过对主螺旋桨的桨叶倾角进行调节来调整动力的大小，这个位于飞行员位置左侧的操纵杆控制直升机的飞行高度。推拉总距操纵杆可轻微改变桨叶的倾斜角度，这样可以改变直升机的迎角，从而使直升机爬升或下降。另外贝尔公司生产的系列直升机在总距杆上还集成有主发动机功率控制器，该控制器可以根据主螺旋桨桨叶的旋转倾角自动对主发动机的输出功率进行调整。";
                bottom = "B.踏板（pedals）\n" +
                        "在直升机驾驶席下方通常设有两块踏板，驾驶员可以通过他们对螺旋桨的输出功率和桨叶的倾角进行调整，左右踏板控制着尾旋翼的倾斜角度，这两项的调整可以对机头的水平方向进行调整，尾旋翼的作用是防止机身在主旋翼旋转产生的反作用力下向相反方向旋转。踏板的作用是让直升机在飞行时向左或向右改变方向。\n" +
                        "C.周期变距操纵杆（cyclic）\n" +
                        "位于驾驶席的中前方，该操作杆的控制对象为主螺旋桨下方自动倾斜器的不动环。不动换可以对主螺旋桨的旋转倾角进行调整，所以这个位于飞行员正前方的驾驶杆控制着旋翼的倾斜角度，从而让直升机向前或向后飞行，决定了机身的飞行方向。";
                break;
            case 5:
                top = "直升机的飞行操作\n" +
                        "1.升降\n" +
                        "一部分人会认为，直升机的在垂直方向的升降是通过改变主螺旋桨的旋转速度来实现的，实际上改变主螺旋桨的转速也不失为实现直升机机体升降的方法之一，但是直升机设计师们在早期就发现，提升主螺旋桨的输出功率会导致机身整体的复合加大，因此目前比较通用的方式是在保持主螺旋桨转速一定的情况下依靠改变主螺旋桨桨叶的倾角来调整机身升力的大小，驾驶员可以通过总局操纵杆来完成此操作，当你把总距操纵杆向上拉的时候，主螺旋桨的桨叶倾斜角度增大，这时候直升机便会上升，同理反向操作飞机便会下降，需要保持当前高度总距操纵杆需要置于中间位置。";
                bottom = "2.平移\n直升机最大的飞行优势就是可以在不改变机首方向的情况下，随时向任何方向平移，这种平移是改变主螺旋桨旋转倾斜角来实现的。当驾驶员向各个方向扳动周期变距杆时，主螺旋桨的主轴也会发生相应的倾斜，这时主螺旋桨的推力分解为垂直与水平方向的分力，垂直方向的分力依旧保持飞行高度，水平方向的分力可以使飞机在这个方向上平移。\n" +
                        "需要了解的是，以上分析是对主螺旋桨看作一个整体得出的，如果我们把关注点放在每一片桨叶的受力分析，情况将会更加复杂，而直升机桨叶的横截面和普通固定翼飞机机翼的横截面类似，均为头粗尾尖的纺锤形或者半纺锤形。当桨叶划过空气时候的切入角度发生变化时候，桨叶所产生的升力也会发生变化，而在直升机的主螺旋桨的旋转面偏离水平面的情况下，单片桨叶划过空气的切入角度将随着螺旋桨的转动发发生周期性变化。同样的道理，该片桨叶所产生的升力也会变现为周期性的变化，这也是驾驶舱里驾驶员控制主螺旋桨主轴角度的操作杆被称为周期变距杆的由来。以四桨叶结构的螺旋桨为例，当期旋转面发生倾斜时，相对两片桨叶所产生的升力差同样会对机身的飞行姿态和移动方向造成影响。实际上设计师正是利用这种升力差来使直升机在空中灵活自由的飞行。";
                break;
            case 6:
                top = "直升机最大的飞行优势就是可以在不改变机首方向的情况下，随时向任何方向平移，这种平移是改变主螺旋桨旋转倾斜角来实现的。当驾驶员向各个方向扳动周期变距杆时，主螺旋桨的主轴也会发生相应的倾斜，这时主螺旋桨的推力分解为垂直与水平方向的分力，垂直方向的分力依旧保持飞行高度，水平方向的分力可以使飞机在这个方向上平移。\n" +
                        "需要了解的是，以上分析是对主螺旋桨看作一个整体得出的，如果我们把关注点放在每一片桨叶的受力分析，情况将会更加复杂，而直升机桨叶的横截面和普通固定翼飞机机翼的横截面类似，均为头粗尾尖的纺锤形或者半纺锤形。当桨叶划过空气时候的切入角度发生变化时候，桨叶所产生的升力也会发生变化，而在直升机的主螺旋桨的旋转面偏离水平面的情况下，单片桨叶划过空气的切入角度将随着螺旋桨的转动发发生周期性变化。同样的道理，该片桨叶所产生的升力也会变现为周期性的变化，这也是驾驶舱里驾驶员控制主螺旋桨主轴角度的操作杆被称为周期变距杆的由来。以四桨叶结构的螺旋桨为例，当期旋转面发生倾斜时，相对两片桨叶所产生的升力差同样会对机身的飞行姿态和移动方向造成影响。实际上设计师正是利用这种升力差来使直升机在空中灵活自由的飞行。";
                bottom = "实际上直升机的每一个动作都是这三个控制系统控制的结果，以起飞为例，直升机启动通常要启动主螺旋桨，使飞机垂直升至1-1.5米的高度，然后驾驶员会一面加大主螺旋桨的倾角来加快爬升，另一面还会让主螺旋桨向前倾斜来加快飞行速度。随着主螺旋桨功率的增加，驾驶员还需要不断调整尾螺旋桨的功率使机身保持直线飞行，可以说飞机飞行的每时每刻都贯穿着数个力的平衡与失衡，所以直升机操作系统看似简单，真的要熟练掌握并非简单的事情。";
                break;
            case 7:
                top = "打开空中电瓶，检查电压不低于28.5v，接通单向36v，保险电门按需要接通，\n" +
                        "检查油量，依次接通左外泵，消耗泵，防火开关，灭火系统，参数记录仪，\n" +
                        "按警铃给信号，启动AN-9，记时，\n" +
                        "给信号，接通115v,启动左发，记时，\n" +
                        "给信号，启动右发，计时，";
                bottom = "断开AN-9，\n" +
                        "自检后接通交流发电机，整流装置，左右防砂，上下信标灯，航向系统，中波罗盘，右外泵，地平仪，左右电调，极限状态开关，尾桨限动，无线电高度表，记时，\n" +
                        "检查自驾，尾桨限动，纪录参数，\n" +
                        "向塔台报告请求起飞。";
                break;
            case 8:
                top = "加油门，起飞前螺旋桨需要加速，此时加不加油门都无所谓，当右边的仪表指针指向绿色区域时表示螺旋桨速度足够可以起飞，此时按下空格可以立即起飞，飞行姿态用鼠标控制，前进时机头向下压，后退则反之，至于转向，水平转向后面介绍，至于飞行过程中转向，可先侧拖鼠标使机身倾斜再向后拉鼠标以达到转向的目的，飞行过程中要注意保持平衡，机身倾角过大容易倒栽。";
                bottom = "飞机起飞后，油门会自动控制在一个中等水平，无需一直按空格，当需要拉起时按空格加油或下降时减油。水平转向：水平转向需要直升机悬停，按G(按一下就行，不需要按住)可以使直升机自动悬停，此时油门会变为黄色，当中间仪表里的圆点位于仪表中央时，说明直升机处于悬停状态，此时左右滑动鼠标可以水平转向而不会侧倾，注意：只有油门为黄色时才能水平转向，白色状态下会侧倾，而且在黄色状态下无法前进或后退，再按一下G可以解除悬停(黄色)状态。观察：机舱内正对前玻璃和视角有限，当降落时会有不便，按住Alt键并滑动鼠标可以转动视角，以观察下方或者侧面，也可观察机舱内部，松开恢复。双击Alt也可以达到同样效果，再次双击恢复。注意：观察时无法用鼠标控制飞机";
                break;
            case 9:
                top = "输直升机的核心意义在于运输兵力到敌人后方以达到出其不意的效果，因此降落很重要。机降的一般步骤是先观察预定的机降点有没有妨碍降落的东西，如树木、石头等，不能损坏主旋翼或尾翼，然后靠近，打开悬停模式，降低高度然后触地，带着一定速度着陆可能会导致机身滑行撞到树木。注意：由于直升机舱门敞开致使乘客易受到地面轻武器袭击，降落时直升机速度较慢容易被RPG威胁，所以我建议机降要速战速决，不要拖拉。超过一定速度落地会会被判定为坠机，导致全机人死亡。";
                bottom = "直升机主要有这些重要部位：主旋翼、发动机(位于旋翼下方)尾部旋翼，当这些部位受到损坏时会在直升机图上标示出来，黄色为不完全损坏，红色为完全损坏，彻底失去功能。损坏后果：主旋翼和发动机损坏时会产生动力不足的问题，导致爬升困难，完全损坏会使直升机失去动力，坠落或无法起飞。尾翼受损会使机身不稳定、摇晃，完全损坏会使机身旋转、失控而导致坠毁。导致损坏的原因有：受到武器攻击、与地面物体发生碰撞导致损坏，要提醒大家的是：尤其在降落和低空飞行时，主旋翼碰到树木时会折断，导致坠毁";
                break;

        }
        tv1.setText(top);
        tv2.setText(bottom);
        tv3.setText(top);
        //隐藏文字
        if (getIntent().getExtras().getInt("type") == 1) {
            scContent.setVisibility(View.GONE);
            tv1.setVisibility(View.GONE);
            tv2.setVisibility(View.GONE);
            tv3.setVisibility(View.GONE);
            //添加多张图片在底部
            iv1.setVisibility(View.GONE);
            iv2.setVisibility(View.GONE);
            iv3.setVisibility(View.GONE);
           /* for (int i = 0; i < pics.size(); i++) {
                final ImageView imageView = new ImageView(this);
                imageView.setImageBitmap(BitmapFactory.decodeFile(pics.get(i)));
                imageView.setTag(i);
                imageView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                    }
                });
                lltImg.addView(imageView);
            }*/
            //初始化适配器
            initAdapter();
        }

    }

    @Override
    protected void onResume() {
        super.onResume();
        if (dataAdapter!=null){
            dataAdapter.getDataList().clear();
            dataAdapter.getDataList().addAll(pics);
            dataAdapter.notifyDataSetChanged();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @OnClick({R.id.iv_1, R.id.iv_2, R.id.iv_3})
    public void onViewClicked(View view) {
        ArrayList<Integer> integers = new ArrayList<>();
        integers.add(R.mipmap.detail_1);
        integers.add(R.mipmap.detail_2);
        integers.add(R.mipmap.detail_3);
        switch (view.getId()) {
            case R.id.iv_1:
                PhotoViewActivity.start(this, integers, 0, 0);
                break;
            case R.id.iv_2:
                PhotoViewActivity.start(this, integers, 1, 0);
                break;
            case R.id.iv_3:
                PhotoViewActivity.start(this, integers, 2, 0);
                break;
        }
    }
}
