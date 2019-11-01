package zhihu.iptv.jiayin.customlibe.view;

import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.iptv.hualu.hualunew5_17.HuaLuSearchActivity;
import com.iptv.hualu.hualunew5_17.R;
import com.iptv.hualu.hualunew5_17.adapter.GridViewAdapter;
import com.iptv.hualu.hualunew5_17.bean.HuaLuSearchBean;
import com.iptv.hualu.hualunew5_17.bean.NewSearchBean;
import com.iptv.hualu.hualunew5_17.bean.RecommentBean;
import com.iptv.hualu.hualunew5_17.listener.ListListener;
import com.iptv.hualu.hualunew5_17.network.NetWorkgk;
import com.iptv.hualu.hualunew5_17.utils.ConstantUtils;
import com.iptv.hualu.hualunew5_17.utils.OkHttpUtils;

import java.util.List;


/**
 * Created by wh on 2018-09-16.
 */

public class SearchObj {

    AllMovieRecyclerView remen;
    HuaLuSearchActivity context;

    LinearLayout topLayout;
//    TextView qjp;

    public void init(HuaLuSearchActivity context) {

        this.context = context;
//        remen = context.findViewById(R.id.remen);
        topLayout = context.findViewById(R.id.search_layout);
//        qjp = context.findViewById(R.id.qjp);
//        createTuiJian();

//        initList();
        //   initListFocu();

    }
boolean isGone;
    private void initData(String key) {

        ListListener.OnGetRequest listener = new ListListener.OnGetRequest<NewSearchBean>() {
            @Override
            public void onRequst(NewSearchBean data) {
                    if (data.getData().getData().getList() != null && data.getData().getData().getList().size() != 0) {
                        Log.e("TAG", "我就瞅瞅: 搜索不是空的，" + data.getData().getData().getList().size() );
                        isGone = false;
                        context.noData(isGone);
                        context.recyclerViewSearchBaseAdapter.setDatas(data);
                        context.recyclerViewSearchBaseAdapter.notifyDataSetChanged();
                    }else {
                        Log.e("TAG", "我就瞅瞅: 搜索是空的，" + data.getData().getData().getList().size());
                        isGone = true;
                        context.noData(isGone);
                    }
            }


        };


        NetWorkgk.goSearch(context,key ,listener);




    }





//    GridView tuijianGrid;
//    GridViewAdapter tuijianAdapter;
//    View oldTuiJianView;



//    public void initList() {
//        LinearLayoutManager layoutManager = new LinearLayoutManager(context);
//        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
//        remen.setLayoutManager(layoutManager);
//
//        String[] arrStr = new String[]{"为你推荐", "热听排行", "最新上架", "北方戏曲", "南方戏曲"};
//        mRecyclerViewPresenter = new HuaRecyclerViewPresenter(context, arrStr, null, R.layout.huaquanbu_search_select_item, R.id.title_tv);
//        mGeneralAdapter = new GeneralAdapter(mRecyclerViewPresenter);
//        remen.setAdapter(mGeneralAdapter);
//    }

    GridView movieGrid;
    GridViewAdapter gridViewAdapter;

    View oldMovieView;


    boolean isError;
    public void crreateGrid() {
        movieGrid = (GridView) context.findViewById(R.id.search_right_grid);
      //  gridViewAdapter = new GridViewAdapter(context, R.layout.search_hualu_movie_item);
     ///  movieGrid.setAdapter(gridViewAdapter);

        movieGrid.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                if (oldMovieView != null) {
                    oldMovieView.animate().scaleX(1f).scaleY(1f).setDuration(150).start();
             //      oldMovieView.findViewById(R.id.tishen).setBackgroundResource(0);

                }
                if(isError) {
                    view.animate().scaleX(1.05f).scaleY(1.05f).setDuration(150).start();

            //        view.findViewById(R.id.tishen).setBackgroundResource(R.drawable.blue_shape);
//                    view.setBackgroundResource(R.drawable.blue_shape);
                }

                oldMovieView = view;
//                view.animate().scaleY(1.5f).setDuration(300).start();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        movieGrid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

//                MyContentLayout.SendItemDetail sendItem = (MyContentLayout.SendItemDetail) view.getTag();
//
//                Log.e("TAA", "看看获取的ab：" + sendItem.type + "_________" + sendItem.style + "_______________" + sendItem.uid + "_________" + sendItem.id);
//                Intent intent = null;
//                if (sendItem.type == 1 || sendItem.type == 3) {
//                    intent = new Intent(context, HuaLuDetailActivity.class);
//
//                } else {
//                    intent = new Intent(context, HuaLuYinPinDetailActivity.class);
//
//                }
//                intent.putExtra(MyContentLayout.SendItemDetail.KEY, sendItem);
//                context.startActivity(intent);
//                context.overridePendingTransition(R.anim.activity_in, R.anim.activity_over);
            }
        });


        movieGrid.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                Log.e("TAA", "干" + context.getResources().getDimension(R.dimen.x520));
                isError  = true;
                if (hasFocus) {

                    ((ScrollView)context.findViewById(R.id.scroll)).smoothScrollTo(0,0);

                    if (oldMovieView != null) {
                        oldMovieView.animate().scaleY(1.05f).scaleX(1.05f).setDuration(150).start();
                   //     oldMovieView .findViewById(R.id.tishen).setBackgroundResource(R.drawable.blue_shape);

                    } else {
                        movieGrid.getChildAt(0).animate().scaleY(1.05f).scaleX(1.05f).setDuration(150).start();
                   //     movieGrid.getChildAt(0) .findViewById(R.id.tishen).setBackgroundResource(R.drawable.blue_shape);
                        oldMovieView = movieGrid.getChildAt(0);
                    }
                } else {
                    if (oldMovieView != null) {
                        oldMovieView.animate().scaleY(1f).scaleX(1f).setDuration(150).start();
                    //    oldMovieView .findViewById(R.id.tishen).setBackgroundResource(0);

                    }

                }

                if (topLayout != null && !isScrollX && hasFocus) {
                    float mdimen = context.getResources().getDimension(R.dimen.x620);
                    topLayout.scrollBy((int) mdimen, 0);
                    ((ScrollView) ((HuaLuSearchActivity) context).findViewById(R.id.scroll)).scrollTo(0, 0);
                    isScrollX = true;
                }
//
            }
        });


        //切换回来


        ((HuaLuSearchActivity) context).searchGrid.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {

                if (hasFocus) {

                    if (oldMovieView != null) {
                        oldMovieView.animate().scaleY(1f).scaleX(1f).setDuration(150).start();
                     //   oldMovieView .findViewById(R.id.tishen).setBackgroundResource(0);
                    }


                    if (((HuaLuSearchActivity) context).mOldView != null && ((HuaLuSearchActivity) context).mOldView.findViewById(R.id.textView) != null) {
                        ((HuaLuSearchActivity) context).mOldView.setBackgroundResource(R.drawable.four_item_focuse);
                        ((HuaLuSearchActivity) context).mOldView.findViewById(R.id.textView).animate().scaleX(1.2f).scaleY(1.2f).setDuration(300).start();
                    }

                    if (isScrollX || ((HuaLuSearchActivity) context).findViewById(R.id.scroll).getTop() != 0) {
                        int mdimen = (int) context.getResources().getDimension(R.dimen.x620);

                        topLayout.scrollBy(-mdimen, 0);

                        ((HuaLuSearchActivity) context).findViewById(R.id.scroll).scrollTo(0, 0);
                        isScrollX = false;
                        ((GridView) context.findViewById(R.id.search_left_grid)).requestFocus();
                    }

                } else {
                    if (((HuaLuSearchActivity) context).mOldView != null && ((HuaLuSearchActivity) context).mOldView.findViewById(R.id.textView) != null) {
                        ((HuaLuSearchActivity) context).mOldView.setBackgroundResource(0);

                        ((HuaLuSearchActivity) context).mOldView.findViewById(R.id.textView).animate().scaleX(1.0f).scaleY(1.0f).setDuration(300).start();

                    }

                }

            }
        });

    }

    public void initMovieList(final List<HuaLuSearchBean.DataBean.ResBean> res) {
        //search_right_grid
//        movieGrid = (GridView) context.findViewById(R.id.search_right_grid);
//        gridViewAdapter = new GridViewAdapter(context, R.layout.search_hualu_movie_item);
        if (gridViewAdapter == null || movieGrid == null) {
            crreateGrid();
        }

        gridViewAdapter.setCount(res.size());
//        movieGrid.setAdapter(gridViewAdapter);
        movieGrid.setVerticalSpacing(50);
        movieGrid.setHorizontalSpacing(150);
        gridViewAdapter.setItemViewInit(new GridViewAdapter.ItemViewInit() {
            @Override
            public void initItemView(int position, View convertView, ViewGroup parent) {

                HuaLuSearchBean.DataBean.ResBean resBean = res.get(position);

//                MyContentLayout.SendItemDetail sendItem = new MyContentLayout.SendItemDetail();
//                sendItem.id = resBean.getDataid();
//                sendItem.style = resBean.getStyle();
//                sendItem.type = resBean.getPtype();
//
//                convertView.setTag(sendItem);
//
//                ImageView imageView = convertView.findViewById(R.id.img);
//                TextView title = convertView.findViewById(R.id.search_title_tv);
//                TextView search_detail = convertView.findViewById(R.id.search_detail);
//
//
//                title.setText(resBean.getName());
//                search_detail.setText(resBean.getJckd() == "" ? "暂无" : resBean.getJckd());
//
//
//                Glide.with(convertView.getContext())
//                        .load(resBean.getImg())
//                        .into(imageView);

            }

        });

        gridViewAdapter.notifyDataSetChanged();


    }

//    public void


    /**
     * true 代表已经切换位置
     * false 代表代表初始状态
     */
    private boolean isScrollX;

    public void initListFocu() {

        //切换出去
//        mRecyclerViewPresenter.setOnItemFocu(new HuaRecyclerViewPresenter.OnItemFocu() {
//            @Override
//            public void onItemFocuse(int position, View view, boolean focuse) {
//                ImageView imageView = view.findViewById(R.id.point_img);
//
//                if (focuse) {
//                    imageView.setImageResource(R.drawable.point_focu);
//                    view.setBackgroundResource(R.drawable.xian);
//                    if (topLayout != null && !isScrollX) {
//                        topLayout.scrollBy(650, 0);
//                        isScrollX = true;
//                    }
////                    view.animate().scaleX(1.5f).setDuration(150).start();
//                } else {
//                    imageView.setImageResource(R.drawable.point);
//                    view.setBackgroundResource(R.drawable.key_bg_rectangle);
////                    view.animate().scaleX(1f).setDuration(150).start();
//
//                }
//            }
//        });


        //切换回来
//        qjp.setOnFocusChangeListener(new View.OnFocusChangeListener() {
//            @Override
//            public void onFocusChange(View v, boolean hasFocus) {
//
//                if (hasFocus) {
//                    if (isScrollX) {
//                        topLayout.scrollBy(-650, 0);
//                        ((HuaLuSearchActivity) context).findViewById(R.id.scroll).scrollTo(0,0);
//
//                        isScrollX = false;
//                        ((GridView) context.findViewById(R.id.search_left_grid)).requestFocus();
//                    }
//
//
//                }
//            }
//        });
    }


    public void searchData(String key) {

        initData(key);

    }


}
