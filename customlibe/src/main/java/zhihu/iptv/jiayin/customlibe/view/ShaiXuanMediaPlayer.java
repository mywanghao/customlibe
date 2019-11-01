package zhihu.iptv.jiayin.customlibe.view;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.iptv.hualu.hualunew5_17.HualuHistoryActivity;
import com.iptv.hualu.hualunew5_17.HualuShaiXuanActivity;
import com.iptv.hualu.hualunew5_17.HualuYInPinAudioActivity;
import com.iptv.hualu.hualunew5_17.R;
import com.iptv.hualu.hualunew5_17.bean.LikeBean;
import com.iptv.hualu.hualunew5_17.bean.ShaixuanMovieBean;
import com.iptv.hualu.hualunew5_17.listener.ListListener;
import com.iptv.hualu.hualunew5_17.network.NetWorkgk;
import com.iptv.hualu.hualunew5_17.utils.ClickUtils;
import com.iptv.hualu.hualunew5_17.utils.SPUtils;
import com.iptv.hualu.hualunew5_17.utils.StaticUtils;
import com.iptv.hualu.hualunew5_17.utils.ToastUtils;
import com.iptv.hualu.hualunew5_17.utils.YinpinObj;

import java.io.IOException;
import java.util.Formatter;
import java.util.List;
import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;

public class ShaiXuanMediaPlayer implements MediaPlayer.OnBufferingUpdateListener, MediaPlayer.OnCompletionListener, MediaPlayer.OnPreparedListener {

    TextView tv_kuaiJin;
    SeekBar seekbar_audio;
    StringBuilder mFormatBuilder;
    Formatter mFormatter;
    public MediaPlayer mediaPlayer;
    ButtonView iv_player_audio;
    HualuShaiXuanActivity Activity;
    List<ShaixuanMovieBean.DataBeanX.DataBean> data;
    ImageView leftImg;
    View saveChildAt;
    HualuYInPinAudioActivity hualuYInPinAudioActivity;

    YinpinObj yinpinObj;
    //初始化音频
    public void initAudio(final SeekBar seekBar, final ButtonView iv_player_audio, LongClickBytton up_music,
                          LongClickBytton down_music, final ButtonView2 qiehuan, ImageView leftImg, final ButtonView like, ButtonView qp, TextView centerText, final List<ShaixuanMovieBean.DataBeanX.DataBean> data , HualuShaiXuanActivity view) {
//        mediaPlayer = new MediaPlayer();
        mFormatBuilder = new StringBuilder();
        Activity = view;
        mFormatter = new Formatter(mFormatBuilder, Locale.getDefault());
        this.leftImg  = leftImg;
        //中间的
        this.iv_player_audio = iv_player_audio;
        this.data = data;
        tv_kuaiJin = centerText;
        seekbar_audio = seekBar;
        down_music.init(R.drawable.down_music, R.drawable.down_musicys);
        up_music.init(R.drawable.up_music, R.drawable.up_musiys);

        if (StaticUtils.xunhuan){
            qiehuan.setBackgroundResource(R.drawable.ms_dq);
            StaticUtils.xunhuan = true;
        }else {

            qiehuan.setBackgroundResource(R.drawable.ms_ls);
            StaticUtils.xunhuan = false;
        }
        down_music.setLongClickRepeatListener(new LongClickBytton.LongClickRepeatListener() {
            @Override
            public void repeatAction() {
                showKuaiJin();
            }
        });
        //快退
        up_music.setLongClickRepeatListener(new LongClickBytton.LongClickRepeatListener() {
            @Override
            public void repeatAction() {
                showKuaiTui();
            }
        });

        iv_player_audio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mediaPlayer != null) {
                    if (mediaPlayer.isPlaying()) {
                        mediaPlayer.pause();
                        iv_player_audio.setBackgroundResource(R.drawable.start_btn);
                    } else {
                        mediaPlayer.start();
                        iv_player_audio.setBackgroundResource(R.drawable.pause_btn);

                    }
                }
            }
        });


        qp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //全屏去播放
                ShaixuanMovieBean.DataBeanX.DataBean dataBean = data.get(position);
//                dataBean.getPlay_url();

                String id = dataBean.getId();
                String type = dataBean.getVideo_type();
                Intent intent = new Intent(v.getContext(), HualuYInPinAudioActivity.class);
                StaticUtils.shaixuanyinpin = true;
                Log.e("TAG", "onClick瞅瞅: "+id+"___:"+type );
                intent.putExtra("id",id);
                intent.putExtra("type","2");

                v.getContext().startActivity(intent);
                if (mediaPlayer != null) {
                    mediaPlayer.pause();
                }
            }
        });

        like.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("taa", "onClick:点击了11111 " );
                ShaixuanMovieBean.DataBeanX.DataBean dataBean = data.get(position);
                String id = dataBean.getId();
                String type = "2";
                like.setBackgroundResource(R.drawable.yishoucang);
                like.init(R.drawable.yishoucang, R.drawable.yishoucang);
                Activity.setLike1(type,id);
            }
        });
        qiehuan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (StaticUtils.xunhuan) {

                    qiehuan.setBackgroundResource(R.drawable.ms_lsys);
                    qiehuan.init(R.drawable.ms_ls, R.drawable.ms_lsys);
                    StaticUtils. xunhuan = false;
                    Log.e("TAG", "看看xunhuan是啥: 是全部然后设置成" + StaticUtils. xunhuan );

                } else {
                    qiehuan.setBackgroundResource(R.drawable.danqu);
                    qiehuan.init(R.drawable.ms_dq, R.drawable.danqu);

                    StaticUtils.xunhuan = true;
                    Log.e("TAG", "看看xunhuan是啥: 是单曲然后设置成"+StaticUtils.xunhuan );

                }
            }
        });
    }
    String isvip = SPUtils.getSharedStringData(Activity, "is_vip");

    int currentKuaiJinTime;

    private void showKuaiTui() {

        currentKuaiJinTime = currentKuaiJinTime - 10 * 1000;
        mHandler.removeCallbacksAndMessages(null);
        mediaPlayer.pause();
        tv_kuaiJin.setVisibility(View.VISIBLE);
        if (currentKuaiJinTime <= 0) {
            currentKuaiJinTime = 0;
            tv_kuaiJin.setText("已到开头");
            seekbar_audio.setProgress(currentKuaiJinTime);
            mHandler.sendEmptyMessageDelayed(1, 1000);
            return;
        }
        String str = stringForTime(currentKuaiJinTime);
        tv_kuaiJin.setText("快退至: " + str);
        seekbar_audio.setProgress(currentKuaiJinTime);
        //mediaPlayer.seekTo(currentTime);
        mHandler.sendEmptyMessageDelayed(1, 1000);
    }

    private void showKuaiJin() {
        mHandler.removeCallbacksAndMessages(null);
        mediaPlayer.pause();
        tv_kuaiJin.setVisibility(View.VISIBLE);
        currentKuaiJinTime = currentKuaiJinTime + 10 * 1000;
        if (currentKuaiJinTime >= mediaPlayer.getDuration()) {
            currentKuaiJinTime = mediaPlayer.getDuration();

//            tv_kuaiJin.setText("已到末尾");
//            seekbar_audio.setProgress(currentKuaiJinTime);
//            mHandler.sendEmptyMessageDelayed(2, 1000);
            nextVideo();

            return;
        }
        String str = stringForTime(currentKuaiJinTime);
        tv_kuaiJin.setText("快进至: " + str);
        seekbar_audio.setProgress(currentKuaiJinTime);
        //mediaPlayer.seekTo(currentTime);
        mHandler.sendEmptyMessageDelayed(1, 1000);
    }


    Handler mHandler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            if (msg.what == 1) {//快进
                tv_kuaiJin.setVisibility(View.GONE);
                mediaPlayer.seekTo(currentKuaiJinTime);
                mediaPlayer.start();
                seekbar_audio.setProgress(mediaPlayer.getCurrentPosition());
                //按钮-暂停
//                iv_playAudio.setBackgroundResource(R.drawable.play_audio_pause_button);
            }
            if (msg.what == 2) {
                tv_kuaiJin.setVisibility(View.GONE);
//                iv_playAudio.setBackgroundResource(R.drawable.play_audio_button);
                currentKuaiJinTime = 0;
                mediaPlayer.seekTo(currentKuaiJinTime);
            }
        }
    };

    public String stringForTime(int timeMs) {

        int totalSeconds = timeMs / 1000;
        int seconds = totalSeconds % 60;

        int minutes = (totalSeconds / 60) % 60;

        int hours = totalSeconds / 3600;

        mFormatBuilder.setLength(0);
        if (hours > 0) {
            return mFormatter.format("%d:%02d:%02d", hours, minutes, seconds)
                    .toString();
        } else {
            return mFormatter.format("%02d:%02d", minutes, seconds).toString();
        }
    }

    //当前播放在什么位置
    int position;

    View oldView;

    String url ;

    String Is_vip;

    public void playerMedia(String trim, int position, View v ,String isvip1 ,String id ,String type,String times) {
        Log.e("TAA", "执行bof1：playerMedia");
        this.position = position;
        this.oldView = v;
//        seekbar_audio.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
//            @Override
//            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
//                mediaPlayer.seekTo(seekBar.getProgress());
//            }
//
//            @Override
//            public void onStartTrackingTouch(SeekBar seekBar) {
//
//            }
//
//            @Override
//            public void onStopTrackingTouch(SeekBar seekBar) {
//                mediaPlayer.seekTo(seekBar.getProgress());
//            }
//        });
       Activity.setHistory( id , type , times );
        currentKuaiJinTime = 0;
        startTime();
        url = trim;
        Is_vip = isvip1;
        Log.e("TAG", "playerMedia: 视频" + trim +"___:" +isvip1 +"___:"+isvip);
        if ("1".equals(isvip1)) {
            Log.e("TAG", "playerMedia: 视频执行了");
            if ("1".equals(isvip) ) {
                initAudioPlayer(trim);
                Log.e("TAG", "playerMedia: 视频VIP，用户VIP可以观看" );
            } else {

                initAudioPlayer(trim);
                Log.e("TAG", "playerMedia: 视频VIP，用户不是VIP"+data.get(position).getPilots_time() );
//                hualuYInPinAudioActivity.setCurrentTime(data.get(position).getPilots_time());

                try {
                    yinpinObj.setCurrentTime(data.get(position).getPilots_time());
                } catch (Exception e) {
                    e.printStackTrace();
                }


            }
        } else {
            Log.e("TAG", "playerMedia: 视频不是VIP，用户不用管是否是VIP" );
            initAudioPlayer(trim);
        }
        Log.e("TAG", "playerMedia: " + url );
    }

    Timer timer;
    TimerTask task;

    public void startTime() {

        if (timer != null) {
            timer.cancel();
        }

        if (task != null) {
            task.cancel();
        }

        timer = new Timer(true);
        task = new TimerTask() {
            public void run() {
                ((Activity) seekbar_audio.getContext()).runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        //每次需要执行的代码放到这里面。
                        if (mediaPlayer != null && mediaPlayer.isPlaying()) {
                            seekbar_audio.setProgress(mediaPlayer.getCurrentPosition());
                        }
//                        String hms = TimeUtil.getHMS();
//                        tv_time.setText(hms);

//                        if (canShowTime) {//能否显示音频的播放时间
//
//                            double v = mediaPlayer.getCurrentPosition() * 1.0 / mediaPlayer.getDuration();
//
//                      //      tv_playTime.setText(utils2.stringForTime(mediaPlayer.getDuration() - mediaPlayer.getCurrentPosition()));
//
//                        }
                        if (mediaPlayer != null) {
                            if (mediaPlayer.isPlaying()) {
                                iv_player_audio.setBackgroundResource(R.drawable.pause_btn);
                            } else {
                                iv_player_audio.setBackgroundResource(R.drawable.start_btn);
                            }
                        }

                    }
                });

            }
        };
        timer.schedule(task, 1000, 1000);

    }

    public void initAudioPlayer(String audioUrl) {
        /*"http://www.jiayinkeji.xin/test/bjbj.mp3"*/
        if (mediaPlayer != null) {
            mediaPlayer.pause();
            mediaPlayer.stop();
            mediaPlayer.release();
            mediaPlayer = null;
        }
        mediaPlayer = new MediaPlayer();
        mediaPlayer.setOnBufferingUpdateListener(this);
        mediaPlayer.setOnCompletionListener(this);
        //设置监听：播放出错，播放完成，准备好
        mediaPlayer.setOnPreparedListener(this);
//            mediaPlayer.setOnCompletionListener(new MusicPlayerService.MyOnCompletionListener());
//            mediaPlayer.setOnErrorListener(new MusicPlayerService.MyOnErrorListener());*/
        try {
            mediaPlayer.setDataSource(audioUrl);
            mediaPlayer.prepare();
            mediaPlayer.start();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onBufferingUpdate(MediaPlayer mp, int percent) {
        //设置缓冲进度
        float position = (float) ((percent / 100.0) * mediaPlayer.getDuration());
        seekbar_audio.setSecondaryProgress(Math.round(position));
    }



    @Override
    public void onCompletion(MediaPlayer mp) {

        if (StaticUtils.xunhuan){
            Log.e("TAG", "onCompletion: "+url );

            if ("1".equals(Is_vip)) {
                if ("1".equals(isvip)) {
                    Log.e("TAG", "playerMedia: 视频VIP，用户VIP可以观看" );
                    initAudioPlayer(url);
                } else {

//                    ToastUtils.showToast(Activity,"您还不是会员，无法收听此音频");
                    Log.e("TAG", "playerMedia: 视频VIP，用户不是VIP" );
                    initAudioPlayer(url);
                }
            } else {
                Log.e("TAG", "playerMedia: 视频不是VIP，用户不用管是否是VIP" );
                initAudioPlayer(url);
            }

        }else {

            if ("1".equals(Is_vip)) {
                if ("1".equals(isvip)) {
                    Log.e("TAG", "playerMedia: 视频VIP，用户VIP可以观看" );
                    nextVideo();
                } else {

//                    ToastUtils.showToast(Activity,"您还不是会员，无法收听此音频");
                    Log.e("TAG", "playerMedia: 视频VIP，用户不是VIP" );
                    nextVideo();
                }
            } else {
                Log.e("TAG", "playerMedia: 视频不是VIP，用户不用管是否是VIP" );
                nextVideo();
            }

        }


    }

    private void nextVideo() {

        if (oldView != null) {

            oldView.findViewById(R.id.left_img).setTag("0");
            ((ImageView) oldView.findViewById(R.id.left_img)).setImageResource(R.drawable.hua_btn_play);
            oldView.findViewById(R.id.left_img).setVisibility(View.GONE);
            oldView.findViewById(R.id.left_txt).setVisibility(View.VISIBLE);
        }

        position += 1;
        ShaixuanMovieBean.DataBeanX.DataBean dataBean = data.get(position);
        playerMedia(dataBean.getPlay_url(), position, null , dataBean.getIs_vip() ,dataBean.getId(),"2",null);

        //以前的回复
        if (saveChildAt != null) {
            saveChildAt.findViewById(R.id.left_img).setTag("0");
            ((ImageView) saveChildAt.findViewById(R.id.left_img)).setImageResource(R.drawable.hua_btn_play);
            saveChildAt.findViewById(R.id.left_img).setVisibility(View.GONE);
            saveChildAt.findViewById(R.id.left_txt).setVisibility(View.VISIBLE);
        }

        //现在的状态改变
        View childAt = StaticUtils.audioRecyclerView.getChildAt(position);
        StaticUtils.saveStateView = childAt;
        ((ImageView) childAt.findViewById(R.id.left_img)).setTag("1");
        ((ImageView) childAt.findViewById(R.id.left_img)).setVisibility(View.VISIBLE);
        ((ImageView) childAt.findViewById(R.id.left_img)).setImageResource(R.drawable.bofangzhong);
        ((TextView) childAt.findViewById(R.id.left_txt)).setVisibility(View.INVISIBLE);
        saveChildAt = childAt;
    }

    @Override
    public void onPrepared(MediaPlayer mp) {
        seekbar_audio.setMax(mediaPlayer.getDuration());
        seekbar_audio.setProgress(0);
    }


}
