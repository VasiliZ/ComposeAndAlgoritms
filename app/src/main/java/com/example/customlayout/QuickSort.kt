package com.example.customlayout

class QuickSort {

    private fun partition(
        arrayInt: IntArray,
        start: Int,
        end: Int
    ): Int {
        val pivot = arrayInt[start]
        var i: Int = start
        var j: Int = end

        while (arrayInt[i] <= pivot && i < end) {
            i++
        }

        while (arrayInt[j] > pivot && j > start) {
            j--
        }

        if (i < j) {
            val temp = arrayInt[i]
            arrayInt[i] = arrayInt[j]
            arrayInt[j] = temp
        }


        arrayInt[start] = arrayInt[j]
        arrayInt[j] = pivot

        return j
    }


    //simple quick sort algoritm
    fun quickSort(
        arrayInt: IntArray,
        start: Int = 0,
        end: Int
    ) {
        if (start < end) {
            val partitionIndex = partition(arrayInt, start, end)
            quickSort(arrayInt, start, partitionIndex)
            quickSort(arrayInt, partitionIndex + 1, end)
        }
    }
}