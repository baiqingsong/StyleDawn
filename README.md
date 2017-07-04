# Android 样式开发

* [样式简介](#样式简介)
    * [shape](#shape)
        * [rectangle](#rectangle)
        * [oval](#oval)
        * [line](#line)
        * [ring](#ring)
    * [selector](#selector)
    * [layer-list](#layer-list)
* [参照网址](#参照网址)


## 样式简介
    一个应用，应该保持一套统一的样式，包括Button、EditText、ProgressBar、
    Toast、Checkbox等各种控件的样式，还包括控件间隔、文字大小和颜色、阴影等等。
    web的样式用css来定义，而android的样式主要则是通过shape、selector、layer-list、
    level-list、style、theme等组合实现。


### shape
一般用shape定义的xml文件存放在drawable目录下，若项目没有该目录则新建一个，而不要将它放到drawable-hdpi等目录中。
使用shape可以自定义形状，可以定义下面四种类型的形状，通过android:shape属性指定：
* rectangle: 矩形，默认的形状，可以画出直角矩形、圆角矩形、弧形等
* oval: 椭圆形，用得比较多的是画正圆
* line: 线形，可以画实线和虚线
* ring: 环形，可以画环形进度条

实现效果要用到以下特性：
* solid : 设置形状填充的颜色，只有android:color一个属性
    * android:color 填充的颜色
* padding : 设置内容与形状边界的内间距，可分别设置左右上下的距离
    * android:top 上内间距
    * android:right 右内间距
    * android:bottom 下内间距
    * android:left 左内间距
* gradient : 设置形状的渐变颜色，可以是线性渐变、辐射渐变、扫描性渐变
    * android:type 渐变的类型
        * linear 线性渐变，默认的渐变类型
        * radial 放射渐变，设置该项时，android:gradientRadius也必须设置
        * sweep 扫描性渐变
    * android:startColor 渐变开始的颜色
    * android:endColor 渐变结束的颜色
    * android:centerColor 渐变中间的颜色
    * android:angle 渐变的角度，线性渐变时才有效，必须是45的倍数，0表示从左到右，90表示从下到上
    * android:centerX 渐变中心的相对X坐标，放射渐变时才有效，在0.0到1.0之间，默认为0.5，表示在正中间
    * android:centerY 渐变中心的相对X坐标，放射渐变时才有效，在0.0到1.0之间，默认为0.5，表示在正中间
    * android:gradientRadius 渐变的半径，只有渐变类型为radial时才使用
    * android:useLevel 如果为true，则可在LevelListDrawable中使用
* corners : 设置圆角，只适用于rectangle类型，可分别设置四个角不同半径的圆角，当设置的圆角半径很大时，比如200dp，就可变成弧形边了
    * android:radius 圆角半径，会被下面每个特定的圆角属性重写
    * android:topLeftRadius 左上角的半径
    * android:topRightRadius 右上角的半径
    * android:bottomLeftRadius 左下角的半径
    * android:bottomRightRadius 右下角的半径
* stroke : 设置描边，可描成实线或虚线。
    * android:color 描边的颜色
    * android:width 描边的宽度
    * android:dashWidth 设置虚线时的横线长度
    * android:dashGap 设置虚线时的横线之间的距离
* size : 设置形状默认的大小，可设置宽度和高度
    * android:width 宽度
    * android:height 高度

#### rectangle
rectangle是默认的形状,一些文字背景、按钮背景、控件或布局背景等
例如圆角带内边距和颜色的矩形：
```
<?xml version="1.0" encoding="utf-8"?>
<shape xmlns:android="http://schemas.android.com/apk/res/android"
    android:shape="rectangle">
    <!-- solid指定形状的填充色，只有android:color一个属性 -->
    <solid android:color="#00aaff"/>
    <!-- padding设置内容区域离边界的间距 -->
    <padding
        android:top="10dp"
        android:right="10dp"
        android:bottom="10dp"
        android:left="10dp"/>
    <!-- corners设置圆角，只适用于rectangle -->
    <corners
        android:bottomLeftRadius="50dp"
        android:bottomRightRadius="50dp"
        android:topLeftRadius="50dp"
        android:topRightRadius="50dp"/>
</shape>
```
xml中调用：
```
<TextView
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    android:text="带内边距和颜色的矩形和圆角"
    android:layout_margin="10dp"
    android:background="@drawable/bg_rectangle_with_corners"/>
```


#### oval
oval用来画椭圆，而在实际应用中，更多是画正圆，比如消息提示，圆形按钮等
oval除了solid，padding，gradient,stroke属性外，还有个size属性

例如带颜色和内边距的圆：
```
<?xml version="1.0" encoding="utf-8"?>
<shape xmlns:android="http://schemas.android.com/apk/res/android"
    android:shape="oval">
    <!-- solid指定形状的填充色，只有android:color一个属性 -->
    <solid android:color="#00aaff"/>
    <!-- padding设置内容区域离边界的间距 -->
    <padding
        android:top="10dp"
        android:right="10dp"
        android:bottom="10dp"
        android:left="10dp"/>
    <!-- size 设置形状默认的大小，可设置宽度和高度 -->
    <size
        android:width="50dp"
        android:height="50dp"/>
</shape>
```


#### line
line主要用于画分割线，是通过stroke和size特性组合来实现的
例如带颜色的实线：
```
<?xml version="1.0" encoding="utf-8"?>
<shape xmlns:android="http://schemas.android.com/apk/res/android"
    android:shape="line">
    <!-- stroke设置描边 -->
    <stroke
        android:color="#fff000"
        android:width="10dp"/>
    <!-- size 设置形状默认的大小，可设置宽度和高度 -->
    <size
        android:height="10dp"/>
</shape>
```


#### ring
shape根元素有些属性只适用于ring类型

* android:innerRadius 内环的半径
* android:innerRadiusRatio 浮点型，以环的宽度比率来表示内环的半径，默认为3，表示内环半径为环的宽度除以3，该值会被android:innerRadius覆盖
* android:thickness 环的厚度
* android:thicknessRatio 浮点型，以环的宽度比率来表示环的厚度，默认为9，表示环的厚度为环的宽度除以9，该值会被android:thickness覆盖
* android:useLevel 一般为false，否则可能环形无法显示，只有作为LevelListDrawable使用时才设为true

需要注意的是设置渐变的时候需要设置渐变类型是扫描型渐变（sweep）

例如带颜色渐变的圆环：
```
<?xml version="1.0" encoding="utf-8"?>
<shape xmlns:android="http://schemas.android.com/apk/res/android"
    android:shape="ring"
    android:innerRadius="30dp"
    android:thickness="5dp"
    android:useLevel="false">
    <!-- solid指定形状的填充色，只有android:color一个属性 -->
    <solid android:color="#00aaff"/>
    <!-- gradient设置渐变 -->
    <gradient
        android:startColor="#ffffff"
        android:endColor="#00ffaa"
        android:type="sweep"/>
</shape>
```


### selector
用来处理不同状态显示不同样式，可以添加一个或者多个item子标签，而相应的状态在item中定义。
状态设置有：
* android:state_enabled: 设置触摸或点击事件是否可用状态，一般只在false时设置该属性，表示不可用状态
* android:state_pressed: 设置是否按压状态，一般在true时设置该属性，表示已按压状态，默认为false
* android:state_selected: 设置是否选中状态，true表示已选中，false表示未选中
* android:state_checked: 设置是否勾选状态，主要用于CheckBox和RadioButton，true表示已被勾选，false表示未被勾选
* android:state_checkable: 设置勾选是否可用状态，类似state_enabled，只是state_enabled会影响触摸或点击事件，而state_checkable影响勾选事件
* android:state_focused: 设置是否获得焦点状态，true表示获得焦点，默认为false，表示未获得焦点
* android:state_window_focused: 设置当前窗口是否获得焦点状态，true表示获得焦点，false表示未获得焦点，例如拉下通知栏或弹出对话框时，当前界面就会失去焦点；另外，ListView的ListItem获得焦点时也会触发true状态，可以理解为当前窗口就是ListItem本身
* android:state_activated: 设置是否被激活状态，true表示被激活，false表示未激活，API Level 11及以上才支持，可通过代码调用控件的setActivated(boolean)方法设置是否激活该控件
* android:state_hovered: 设置是否鼠标在上面滑动的状态，true表示鼠标在上面滑动，默认为false，API Level 14及以上才支持

selector本身有两个属性
添加了下面两个属性之后，则会在状态改变时出现淡入淡出效果:
* android:enterFadeDuration 状态改变时，新状态展示时的淡入时间，以毫秒为单位
* android:exitFadeDuration 状态改变时，旧状态消失时的淡出时间，以毫秒为单位

还有几点需要注意：
1. selector作为drawable资源时，item指定android:drawable属性，并放于drawable目录下；
2. selector作为color资源时，item指定android:color属性，并放于color目录下；
3. color资源也可以放于drawable目录，引用时则用@drawable来引用，但不推荐这么做，drawable资源和color资源最好还是分开；
4. android:drawable属性除了引用@drawable资源，也可以引用@color颜色值；但android:color只能引用@color；
5. item是从上往下匹配的，如果匹配到一个item那它就将采用这个item，而不是采用最佳匹配的规则；所以设置默认的状态，一定要写在最后，如果写在前面，则后面所有的item都不会起作用了。


最后，关于ListView的ListItem样式，有两种设置方式，一种是在ListView标签里设置android:listSelector  
属性，另一种是在ListItem的布局layout里设置android:background。但是，这两种设置的结果却有着不  
同。同时，使用ListView时也有些其他需要注意的地方，总结如下：
1. android:listSelector设置的ListItem默认背景是透明的，不管你在selector里怎么设置都无法改变它的背景。所以，如果想改ListItem的默认背景，只能通过第二种方式，在ListItem的布局layout里设置android:background。
2. 当触摸点击ListItem时，第一种设置方式下，state_pressed、state_focused和state_window_focused设为true时都会触发，而第二种设置方式下，只有state_pressed会触发。
3. 当ListItem里有Button或CheckBox之类的控件时，会抢占ListItem本身的焦点，导致ListItem本身的触摸点击事件会无效。那么，要解决此问题，有三种解决方案：
    * 将Button或CheckBox换成TextView或ImageView之类的控件
    * 设置Button或CheckBox之类的控件设置focusable属性为false
    * 设置ListItem的根布局属性android:descendantFocusability="blocksDescendants"
    
第三种是最方便，也是推荐的方式，它会将ListItem根布局下的所有子控件都设置为不能获取焦点。android:descendantFocusability属性的值有三种，其中，ViewGroup是指设置该属性的View，本例中就是ListItem的根布局：
* beforeDescendants：ViewGroup会优先其子类控件而获取到焦点
* afterDescendants：ViewGroup只有当其子类控件不需要获取焦点时才获取焦点
* blocksDescendants：ViewGroup会覆盖子类控件而直接获得焦点

例如按钮点击效果代码：
```
<?xml version="1.0" encoding="utf-8"?>
<shape xmlns:android="http://schemas.android.com/apk/res/android"
    android:shape="rectangle">
    <solid android:color="#00ffaa"/>
    <corners
        android:radius="50dp"/>
</shape>
```
```
<?xml version="1.0" encoding="utf-8"?>
<shape xmlns:android="http://schemas.android.com/apk/res/android"
    android:shape="rectangle">
    <solid android:color="#00aaff"/>
    <corners
        android:radius="50dp"/>
</shape>
```
```
<?xml version="1.0" encoding="utf-8"?>
<selector xmlns:android="http://schemas.android.com/apk/res/android"
    android:enterFadeDuration="500" android:exitFadeDuration="500">
    <!--按压状态-->
    <item android:drawable="@drawable/item_btn_pressed" android:state_pressed="true"/>
    <!--默认状态-->
    <item android:drawable="@drawable/item_btn_normal"/>
</selector>
```
需要注意的是selector里面要先写状态的item，然后再写默认的item。不然没有点击状态效果


### layer-list
使用layer-list可以将多个drawable按照顺序层叠在一起显示，例如阴影效果
item里面可以设置属性：
* android:top 顶部的偏移量
* android:bottom 底部的偏移量
* android:left 左边的偏移量
* android:right 右边的偏移量

这个偏移量是相对的
代码如下：
```
<?xml version="1.0" encoding="utf-8"?>
<layer-list xmlns:android="http://schemas.android.com/apk/res/android">
    <item
        android:left="2dp"
        android:top="4dp">
        <shape>
            <solid android:color="@android:color/darker_gray"/>
            <corners android:radius="50dp"/>
        </shape>
    </item>
    <item
        android:right="2dp"
        android:bottom="4dp">
        <shape>
            <solid android:color="#ffffff"/>
            <corners android:radius="50dp"/>
        </shape>
    </item>
</layer-list>
```



## 参照网址
[http://keeganlee.me/post/android/20150830](http://keeganlee.me/post/android/20150830 "参考网址")

[https://github.com/keeganlee/kstyle](https://github.com/keeganlee/kstyle "参考地址")