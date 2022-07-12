package com.ask.productdirectory.ui.customview

import android.content.Context
import android.graphics.*
import android.os.Parcel
import android.os.Parcelable
import android.util.AttributeSet
import android.view.View
import com.ask.productdirectory.R
import com.ask.productdirectory.domain.entity.Component
import com.ask.productdirectory.domain.entity.Ingredient
import com.ask.productdirectory.domain.entity.NormComponent
import com.ask.productdirectory.domain.entity.NormIngredient

class Graph @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    //размеры графа
    private val contentWidth = resources.getDimensionPixelSize(R.dimen.graph_width)
    private val contentHeight = resources.getDimensionPixelSize(R.dimen.graph_height)

    private var testZ = 0

    //длина одного столбца (таски)
    private val periodWidth = resources.getDimensionPixelSize(R.dimen.period_width)

    // высота столбца
    private val taskHeight = resources.getDimensionPixelSize(R.dimen.task_height)

    // Радиус скругления углов одного графа
    private val taskCornerRadius = resources.getDimension(R.dimen.task_corner_radius)

    // Вертикальный отступ таски внутри строки
    private val taskVerticalMargin = resources.getDimension(R.dimen.task_vertical_margin)

    // Отступ таски вконце строки
    private val taskVerticalEndMargin = resources.getDimension(R.dimen.task_vertical_end_margin)

    // Горизонтальный отступ текста таски внутри ее фигуры
    private val taskTextHorizontalMargin = resources.getDimension(R.dimen.task_text_horizontal_margin)




    private val rowPaint = Paint().apply {
        style = Paint.Style.STROKE
        color = Color.BLACK
    }

    private val innerRowPaint = Paint().apply {
        style = Paint.Style.FILL
        color = Color.RED
    }

    private val taskNamePaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        textSize = resources.getDimension(R.dimen.task_name_text_size)
        color = Color.BLACK
        // textAlign = Paint.Align.RIGHT

    }

    private val taskValuePaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        textSize = resources.getDimension(R.dimen.task_value_text_size)
        color = Color.BLUE
    }

    private val taskPercentPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        textSize = resources.getDimension(R.dimen.task_value_text_size)
        color = Color.GREEN
        style = Paint.Style.FILL
    }

    //список ингредиентов
    private var task: Ingredient? = null
    private var listComponent: List<Component> = emptyList()
    private var listNormComponent: List<NormComponent> = emptyList()

    fun setTask(task: Ingredient, norm: NormIngredient) {
        if (task != this.task) {
            this.task = task
            this.listComponent = task.component
            this.listNormComponent = norm.component
            // нужно пересчитать размеры
            // requestLayout()
            //  нужно перерисоваться
            invalidate()
        }
    }

    override fun onSaveInstanceState(): Parcelable {
        return SavedState(super.onSaveInstanceState()).also {
            it.ingredient = task
            it.listComponent = listComponent
            it.listNormComponent = listNormComponent
        }
    }

    override fun onRestoreInstanceState(state: Parcelable?) {
        if (state is SavedState) {
            super.onRestoreInstanceState(state.superState)
            onRestoreInstanceState(state)
        } else {
            super.onRestoreInstanceState(state)
        }
    }


    class SavedState : BaseSavedState {
        var ingredient: Ingredient? = null
        var listComponent = emptyList<Component>()
        var listNormComponent = emptyList<NormComponent>()

        // Коснтруктор для сохранения стейта
        constructor(superState: Parcelable?) : super(superState)

        // Коснтруктор для восстановления стейта
        constructor(source: Parcel?) : super(source) {
            source?.apply {
                // Порядок имеет значение
                ingredient = readSerializable() as Ingredient?
                listComponent = readSerializable() as List<Component>
                listNormComponent = readSerializable() as List<NormComponent>
            }
        }

        override fun writeToParcel(out: Parcel?, flags: Int) {
            super.writeToParcel(out, flags)
            out?.writeSerializable(ingredient)
            out?.writeList(listComponent)
            out?.writeList(listNormComponent)
        }

        companion object {
            // Как у любого Parcelable
            @JvmField
            val CREATOR: Parcelable.Creator<SavedState> = object : Parcelable.Creator<SavedState> {
                override fun createFromParcel(source: Parcel): SavedState = SavedState(source)
                override fun newArray(size: Int): Array<SavedState?> = arrayOfNulls(size)
            }
        }
    }

    fun onRestoreInstanceState(state: SavedState) {
        task = state.ingredient
        listComponent = state.listComponent
        listNormComponent = state.listNormComponent
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val resolveWidth = resolveSize(contentWidth, widthMeasureSpec)
        val resolveHeight = resolveSize(contentHeight, heightMeasureSpec)
        setMeasuredDimension(resolveWidth, resolveHeight)
    }

    override fun onDraw(canvas: Canvas) = with(canvas) {
        drawTasks()
    }

    private fun Canvas.drawTasks() {
        var textY = 85f
        var rectY = 50f
        if (task != null) {
            listComponent.forEach {
                // таски (фактическое значение и норма)
                val rowRectFact = RectF()
                val rowRectNorm = RectF()
                // Path для фигуры тасок
                val path = Path()
                val pathOut = Path()
                val nameComponent = it.name
                val valueComponent = it.value.toString()
                drawText(nameComponent, taskTextHorizontalMargin, textY, taskNamePaint)
                drawText(valueComponent, taskTextHorizontalMargin, textY + 40, taskValuePaint)
                // ширина текста
                val textWidth = taskNamePaint.measureText(nameComponent)
                //значение нормы
                val value = listNormComponent.filter { name -> name.name == it.name }
                // % от нормы
                val percentNormComponent = (it.value) / value.first().value * 100
                //начало координат для графа
                val startPointTask = 50f + textWidth
                //ширина координат графа
                //val normTaskWidth = periodWidth - startPointTask
                val normTaskWidth = this.width - taskVerticalEndMargin
                // ширина графа таски фактической (в пикселях)
                val widthTask = (normTaskWidth * it.value) / value.first().value
                //
                val taskWidth = startPointTask + widthTask.toFloat()

                rowRectFact.set(
                    startPointTask,
                    rectY + taskVerticalMargin,
                    taskWidth,
                    rectY + taskHeight - taskVerticalMargin
                )
                rowRectNorm.set(
                    startPointTask,
                    rectY + taskVerticalMargin,
                    normTaskWidth,
                    rectY + taskHeight - taskVerticalMargin
                )

                with(pathOut) {
                    reset()
                    addRoundRect(rowRectNorm, taskCornerRadius, taskCornerRadius, Path.Direction.CW)
                }

                with(path) {
                    reset()
                    addRoundRect(rowRectFact, taskCornerRadius, taskCornerRadius, Path.Direction.CW)
                    op(pathOut, Path.Op.INTERSECT)
                }
                drawPath(path, innerRowPaint)
                drawPath(pathOut, rowPaint)
                drawText(
                    String.format("%.2f", percentNormComponent) + " %",
                    normTaskWidth + taskTextHorizontalMargin,
                    rectY + taskHeight / 2 + taskVerticalMargin,
                    taskPercentPaint
                )

                textY += 120
                rectY += 120
            }
        }
    }
}