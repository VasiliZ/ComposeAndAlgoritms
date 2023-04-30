package com.example.customlayout

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.customlayout.data.QuickSortItem
import com.example.customlayout.data.RadioGroupItemType
import com.example.customlayout.lazylayout.data.LazyListItem
import com.example.customlayout.three.NNode
import com.example.customlayout.three.Node
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.random.Random

private const val DELAY_OPERATION_MILLIS = 200L
private const val MAX_LIST_ITEM_VALUE = 1000
private const val LIST_SIZE = 10

class ScreenViewModel : ViewModel() {
    var bubbleSortList: SnapshotStateList<LazyListItem> = initBubbleSortList()
        private set

    var quickSortList: SnapshotStateList<QuickSortItem> = initQuickSortList()
        private set

    val radioGroupList = RadioGroupItemType.values()

    var selectedRadioButtonItem = mutableStateOf(RadioGroupItemType.BUBBLE_SORT)
    var three = mutableStateOf(buildSimpleBinaryThree())
        private set
    val nNodeThree = rootOfNAryTree()

    init {
        nNodeThree.traverseDepthFirst(nNodeThree) {
            // Log.d("TAG1", ": $it")
        }
    }

    private fun initBubbleSortList(): SnapshotStateList<LazyListItem> {
        return List(LIST_SIZE) {
            LazyListItem(mutableStateOf(Random.nextInt(MAX_LIST_ITEM_VALUE)))
        }.toMutableStateList()
    }

    fun refreshBubbleSort() {
        bubbleSortList.clear()
        bubbleSortList = initBubbleSortList()
    }

    private fun initQuickSortList(): SnapshotStateList<QuickSortItem> {
        return List(LIST_SIZE) {
            QuickSortItem(mutableStateOf(Random.nextInt(MAX_LIST_ITEM_VALUE)))
        }.toMutableStateList()
    }

    fun refreshQuickSort() {
        quickSortList.clear()
        quickSortList = initQuickSortList()
    }


    fun onStartBubbleSort() {
        viewModelScope.launch {
            var swap = true
            while (swap) {
                swap = false
                for (i in 0 until bubbleSortList.size - 1) {
                    delay(DELAY_OPERATION_MILLIS)
                    if (bubbleSortList[i].value.value > bubbleSortList[i + 1].value.value) {
                        val temp = bubbleSortList[i].value.value
                        bubbleSortList[i].value.value = bubbleSortList[i + 1].value.value
                        bubbleSortList[i + 1].value.value = temp
                        swap = true
                    }
                }
            }
        }
    }

    fun onStartQuickSort() {
        quickSort(quickSortList, 0, quickSortList.size - 1)
    }

    private fun partition(
        arrayInt: SnapshotStateList<QuickSortItem>,
        start: Int,
        end: Int
    ): Int {
        val pivot = arrayInt[start].value.value
        var i: Int = start
        var j: Int = end

        while (i < j) {
            while (arrayInt[i].value.value <= pivot && i < end) {
                i++
            }

            while (arrayInt[j].value.value > pivot && j > start) {
                j--
            }

            if (i <= j) {
                val temp = arrayInt[i].value.value
                arrayInt[i].value.value = arrayInt[j].value.value
                arrayInt[j].value.value = temp
            }
        }


        arrayInt[start].value.value = arrayInt[j].value.value
        arrayInt[j].value.value = pivot

        return j
    }

    private fun quickSort(
        arrayInt: SnapshotStateList<QuickSortItem>,
        start: Int = 0,
        end: Int
    ) {
        viewModelScope.launch {
            if (start < end) {
                val partitionIndex = partition(arrayInt, start, end)
                arrayInt[partitionIndex].isPivot.value = true
                delay(DELAY_OPERATION_MILLIS)
                arrayInt[partitionIndex].isPivot.value = false
                quickSort(arrayInt, start, partitionIndex)
                quickSort(arrayInt, partitionIndex + 1, end)

            }
        }
    }

    fun buildSimpleBinaryThree(): Node {

        val rootNode = Node(1)
        rootNode.leftChild = Node(2)
        rootNode.rightChild = Node(3)
        rootNode.leftChild?.leftChild = Node(4)
        rootNode.leftChild?.rightChild = Node(5)
        rootNode.rightChild?.leftChild = Node(6)
        rootNode.rightChild?.rightChild = Node(7)

        return rootNode
    }

    fun onStartTraversePreorder() {
        viewModelScope.launch {
            traversePreorder(three.value)
        }
    }

    fun onStartTraverseInOrder() {
        viewModelScope.launch {
            traverseInOrder(three.value)
        }
    }

    fun onStartTraversePostOrder() {
        viewModelScope.launch {
            traversePostOrder(three.value)
        }
    }

    fun refreshBinaryThree() {
        three.value = buildSimpleBinaryThree()
    }

    private suspend fun traversePreorder(
        node: Node?,
    ) {
        if (node != null) {
            delay(300)
            node.isChecked.value = true
            traversePreorder(node.leftChild)
            traversePreorder(node.rightChild)
        }
    }


    private suspend fun traverseInOrder(
        node: Node?
    ) {
        if (node != null) {
            traverseInOrder(node.leftChild)
            delay(300)
            node.isChecked.value = true
            traverseInOrder(node.rightChild)
        }
    }

    private suspend fun traversePostOrder(
        node: Node?
    ) {
        if (node != null) {
            traversePostOrder(node.leftChild)
            traversePostOrder(node.rightChild)
            delay(300)
            node.isChecked.value = true
        }
    }
}

fun rootOfNAryTree(): NNode {

    val level1Node1 = NNode(2)
    level1Node1.listChildren = mutableListOf(NNode(4), NNode(5), NNode(6))

    val level1Node2 = NNode(3)
    level1Node2.listChildren = mutableListOf(NNode(7), NNode(8))

    val rootNode = NNode(1)
    rootNode.listChildren = mutableListOf(level1Node1, level1Node2)

    return rootNode
}