package com.example.neteasecloudmusicsecondversionapplication.widget

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup
import kotlin.math.max

class FlowLayoutWidget(context: Context, attrs: AttributeSet) : ViewGroup(context, attrs) {

    //子View的横向间隔、纵向间隔
    private val horizontalSpace = 30
    private val verticalSpace = 20

    //保存测量的子View， 每一个元素为一行的子View数组
    private val allLines: MutableList<List<View>> = mutableListOf()
//    private var line = mutableListOf<View>()

    //记录每一行的最大高度，用于布局
    private val heights: MutableList<Int> = mutableListOf()
    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        allLines.clear()
        heights.clear()
        val paddingLeft = paddingLeft
        val paddingRight = paddingRight
        val paddingTop = paddingTop
        val paddingBottom = paddingBottom
        var usedWidth = 0
        var height = 0

        //父布局对FlowLayout的约束宽高(还剩多少可以使用）
        val selfWidth = MeasureSpec.getSize(widthMeasureSpec) - paddingLeft - paddingRight
        val selfHeight = MeasureSpec.getSize(heightMeasureSpec) - paddingTop - paddingBottom

        //FlowLayout的测量宽高
        var needHeight = 0
        var needWidth = 0

        //每一行存储view
        @SuppressLint("DrawAllocation") var line: MutableList<View> = ArrayList()
//        line.clear()

        val count = childCount

        //遍历每一个view，并把进行测量
        for (i in 0 until count) {
            val child = getChildAt(i)
            val childWidthSpec = getChildMeasureSpec(
                widthMeasureSpec,
                paddingLeft + paddingRight, child.layoutParams.width
            )
            val childHeightSpec = getChildMeasureSpec(
                heightMeasureSpec,
                paddingTop + paddingBottom, child.layoutParams.height
            )
            //调用子view的measure方法进行测量（它还会在内部调用它的onMeasure方法
            child.measure(childWidthSpec, childHeightSpec)


            // 进行判断是否换行
            if (usedWidth + horizontalSpace + child.measuredWidth > selfWidth) {
                //当前行无法在放下下一个view，则保存当前行的Views集合以及当前行的最大高度，
                heights.add(height + verticalSpace)
                allLines.add(line)
                //所有行的最大宽度
                needWidth = max(needWidth, usedWidth)
                //所有行的高度之和
                needHeight += height + verticalSpace

                //重置下一行的使用宽度、高度、View集合，便于下一次遍历
                usedWidth = 0
                height = 0
                line = ArrayList()
            }
            //获取当前行的最大高度，作为当前行的高度
            height = max(height, child.measuredHeight)
            //记录已经使用的宽度（第一个元素不需要加横向间隔）
            usedWidth += child.measuredWidth + if (line.size == 0) 0 else horizontalSpace
            //保存已经测量及模拟布局的View
            line.add(child)

            //记录最后一行的数据，这是因为这里判断的是换行才记录,很多情况是不满足换行要求的
            if (i == count - 1) {
                heights.add(height + verticalSpace)
                allLines.add(line)
                needWidth = Math.max(needWidth, usedWidth)
                needHeight += height + verticalSpace
            }
        }

        // 这里要完成对自己的的测量
        val widthMode = MeasureSpec.getMode(widthMeasureSpec)
        val heightMode = MeasureSpec.getMode(heightMeasureSpec)
        // 看自己怎么设置，看这个是wrap还是定了高度
        //如果mode为MeasureSpec.EXACTLY， 则使用widthMeasureSpec中的size
        //不然使用测量得到的size， 宽高同理
        val realWidth = if (widthMode == MeasureSpec.EXACTLY) selfWidth else needWidth
        val realHeight = if (heightMode == MeasureSpec.EXACTLY) selfHeight else needHeight

        //保存测量的宽和高
        setMeasuredDimension(
            realWidth + paddingLeft + paddingRight,  //如果只有一行，不需要纵向间隔
            realHeight + paddingTop + paddingBottom - (if (allLines.size > 0) verticalSpace else 0)
        )
    }

    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {
        var left = paddingLeft
        var top = paddingTop
        for (i in allLines.indices) {
            val line = allLines[i]
            for (j in line.indices) {
                val child = line[j]
                child.layout(
                    left, top, left + child.measuredWidth,
                    top + child.measuredHeight
                )
                //一行中View布局后每次向后移动child的测量宽 + 横向间隔
                left += child.measuredWidth + horizontalSpace
            }
            //每一行布局从paddingLeft开始
            left = paddingLeft
            //布局完成一行，向下移动当前行的最大高度
            top += heights[i]
        }
    }
}