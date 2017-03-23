# CircularMenuApp
<!-- Baidu Button BEGIN -->

<div id="article_content" class="article_content">

<p><br>
</p>
<p><span style="color:rgb(51,51,51); font-family:Arial"><strong><span style="font-size:12px">前言：</span></strong></span><span style="color:rgb(51,51,51); font-family:Arial; font-size:14px"><span style="font-family:SimHei"><span style="color:rgb(102,102,102); font-family:&quot;Microsoft YaHei&quot;,Arial">公司需求，自己写的一个弧形菜单.</span></span></span><br>
</p>
<p><span style="color:rgb(51,51,51); font-family:Arial; font-size:14px"><span style="font-family:SimHei"><span style="color:rgb(102,102,102); font-family:&quot;Microsoft YaHei&quot;,Arial"><br>
</span></span></span></p>
<p><span style="font-family:Microsoft YaHei,Arial; font-size:12px; color:#666666"><strong>效果：</strong></span></p>
<p><span style="font-family:Microsoft YaHei,Arial; font-size:12px; color:#666666"><strong><br>
</strong></span></p>
<p style="text-align:left"><span style="font-family:Microsoft YaHei,Arial; font-size:12px; color:#666666"><strong>&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;&nbsp;<img src="http://img.blog.csdn.net/20170323154215014?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvemhoX2NzZG5fYXJk/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/Center" width="250" height="400" alt=""><br>
</strong></span></p>
<p><span style="font-family:Microsoft YaHei,Arial; font-size:12px; color:#666666"><strong><br>
</strong></span></p>
<p><span style="color:rgb(51,51,51); font-size:14px; font-family:&quot;Microsoft YaHei&quot;,Arial"><strong>开发环境：AndroidStudio2.2.1&#43;gradle-2.14.1</strong></span><br>
</p>
<p><span style="color:rgb(51,51,51); font-size:14px; font-family:&quot;Microsoft YaHei&quot;,Arial"><strong><br>
</strong></span></p>
<p><span style="color:rgb(51,51,51); font-size:14px; font-family:&quot;Microsoft YaHei&quot;,Arial"><strong><span style="color:rgb(51,51,51); font-size:14px; font-family:SimHei"><strong>涉及知识：</strong></span><span style="color:rgb(51,51,51); font-size:14px; font-family:SimHei">1.自定义控件，</span><span style="color:rgb(51,51,51); font-size:14px; font-family:SimHei">2.事件分发等</span><br>
</strong></span></p>
<p><span style="color:rgb(51,51,51); font-size:14px; font-family:&quot;Microsoft YaHei&quot;,Arial"><strong><span style="color:rgb(51,51,51); font-size:14px; font-family:SimHei"><br>
</span></strong></span></p>
<p><span style="color:rgb(51,51,51); font-size:14px; font-family:&quot;Microsoft YaHei&quot;,Arial"><strong><span style="color:rgb(51,51,51); font-size:14px; font-family:SimHei">部分代码：</span></strong></span></p>
<p><span style="color:rgb(51,51,51); font-size:14px; font-family:&quot;Microsoft YaHei&quot;,Arial"><strong><span style="color:rgb(51,51,51); font-size:14px; font-family:SimHei"><br>
</span></strong></span></p>
<p><span style="font-family:Microsoft YaHei,Arial"></span></p>
<pre name="code" class="java" style="font-size:12px; color: rgb(102, 102, 102);">public class HomePageMenuLayout extends ViewGroup {


    private Context context;
    // 菜单项的文本
    private String[] mItemTexts = null;

    private int StatusHeight;//状态栏高度

    public HomePageMenuLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        StatusHeight = ScreenUtils.getStatusHeight(context);
    }


    /**
     * 设置布局的宽高，并策略menu item宽高
     */
    int resWidth = 0;
    int resHeight = 0;
    int mRadius = 0;

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        //布局宽高尺寸设置为屏幕尺寸
        //设置该布局的大小
        setMeasuredDimension(widthMeasureSpec, heightMeasureSpec);

        /**
         * 根据传入的参数，分别获取测量模式和测量值
         */
        int width = MeasureSpec.getSize(widthMeasureSpec);
        resHeight = MeasureSpec.getSize(heightMeasureSpec);
        resWidth = MeasureSpec.getSize(widthMeasureSpec);

        // 获得半径
        mRadius = (int) (resHeight / 2 - 2 * StatusHeight);
        //设置item尺寸
        int childSize = (int) (mRadius * 1 / 2);
        // menu item测量模式--精确模式
        int childMode = MeasureSpec.EXACTLY;

        for (int i = 0; i &lt; getChildCount(); i++) {
            final View child = getChildAt(i);
            if (child.getVisibility() == GONE) {
                continue;
            }
            // 计算menu item的尺寸；以及和设置好的模式，去对item进行测量
            int makeMeasureSpec = -1;
            makeMeasureSpec = MeasureSpec.makeMeasureSpec(childSize, childMode);
            child.measure(makeMeasureSpec, makeMeasureSpec);
        }

    }

    /**
     * item布局的角度
     */
    private int[] widthall = null;

    /**
     * 设置Item的位置：第一个参数1：该参数指出当前ViewGroup的尺寸或者位置是否发生了改变
     * 2.当期绘图光标横坐标位置
     * 3.当前绘图光标纵坐标位置
     */
    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {

        int left, top;
        int cWidth = (int) (mRadius * 1 / 2);
        final int childCount = getChildCount();
        // 计算，中心点到menu item中心的距离
        float tmp = mRadius - cWidth / 2;
        // 遍历去设置menuitem的位置
        for (int i = 0; i &lt; childCount; i++) {
            final View child = getChildAt(i);
            if (child.getVisibility() == GONE) {
                continue;
            }
            left = (int) (mRadius * Math.cos(Math.toRadians(widthall[i]))) - 65;
            top = (int) (mRadius - (resHeight / 2 - 2 * StatusHeight) * Math.sin(Math.toRadians(widthall[i])) - StatusHeight);
            child.layout(left, top, left + cWidth, top + cWidth);

        }

    }

    public interface OnMenuItemClickListener {
        void itemClick(View view, int pos);
    }

    public void setOnMenuItemClickListener(
            OnMenuItemClickListener mOnMenuItemClickListener) {
        this.mOnMenuItemClickListener = mOnMenuItemClickListener;
    }


    // 菜单的个数
    private int mMenuItemCount;

    /**
     * 设置菜单条目的图标和文本
     */
    public void setMenuItemIconsAndTexts(String[] mItemTexts) {
        this.mItemTexts = mItemTexts;
        this.mMenuItemCount = mItemTexts.length;
        resultAngle();
        addMenuItems();
    }

    private void resultAngle() {

        switch (this.mMenuItemCount) {
            case 3:
                widthall = Constants.ITEM3;
                break;
            case 4:
                widthall = Constants.ITEM4;
                break;
            case 5:
                widthall = Constants.ITEM5;
                break;
            case 6:
                widthall = Constants.ITEM6;
                break;
            case 7:
                widthall = Constants.ITEM7;
                break;
            case 8:
                widthall = Constants.ITEM8;
                break;
            case 9:
                widthall = Constants.ITEM9;
                break;
            case 10:
                widthall = Constants.ITEM10;
                break;
            default:
                break;

        }

    }


    /**
     * 设置菜单条目的图标和文本
     */
    public void setMenuItemIconsAndTexts() {
        addMenuItems();
    }

    private int mMenuItemLayoutId = R.layout.homepage_item_layout;


    /**
     * MenuItem的点击事件接口
     */
    private OnMenuItemClickListener mOnMenuItemClickListener;

    private float yPosition = 0;
    /**
     * 添加菜单项
     */
    private void addMenuItems() {
        LayoutInflater mInflater = LayoutInflater.from(getContext());

        /**
         * 根据用户设置的参数，初始化view
         */
        for (int i = 0; i &lt; mMenuItemCount; i++) {
            final int j = i;
            View view = mInflater.inflate(mMenuItemLayoutId, this, false);

            final ImageView iv = (ImageView) view
                    .findViewById(R.id.homepage_pager1_item_img);
            final TextView tv = (TextView) view
                    .findViewById(R.id.homepage_pager1_item_tv);
            if (iv != null) {
                iv.setImageResource(R.mipmap.menu_ture);
            }
            if (tv != null) {
                tv.setText(mItemTexts[i]);
            }
            view.findViewById(R.id.homepage_item_layout).setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {}
            });
            view.findViewById(R.id.homepage_item_layout).setOnTouchListener(new OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    if (event.getAction() == MotionEvent.ACTION_DOWN) {
                        yPosition = event.getY();//获取按下的位置
                        iv.setImageResource(R.mipmap.menu);
                    } else if (event.getAction() == MotionEvent.ACTION_UP) {
                        iv.setImageResource(R.mipmap.menu_ture);
                        float displacement = Math.abs(yPosition - event.getY());
                        //精确按下的位置做出响应
                        if (mOnMenuItemClickListener != null&amp;&amp;displacement&lt;25) {
                            mOnMenuItemClickListener.itemClick(v,j);
                        }
                    } else if (event.getAction() == MotionEvent.ACTION_CANCEL
                    || event.getAction() == MotionEvent.ACTION_POINTER_UP) {
                        iv.setImageResource(R.mipmap.menu_ture);
                    }
                    return true;
                }
            });
            addView(view);
        }
    }
}<strong>
</strong></pre>
<p></p>
<p style="font-size:12px; color:rgb(102,102,102)"><span style="font-family:Microsoft YaHei,Arial; font-size:12px; color:#666666"><br>
</span></p>

<p><span style="font-family:Microsoft YaHei,Arial"><br>
</span></p>
<br>
<br>
<p><span style="font-family:Microsoft YaHei,Arial; font-size:12px; color:#666666"><strong><br>
</strong></span></p>
   
</div>


<!-- Baidu Button END -->
