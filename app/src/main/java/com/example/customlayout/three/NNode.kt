package com.example.customlayout.three

data class NNode(
    val value: Int,
    var listChildren: MutableList<NNode?> = mutableListOf()
) {
    fun traverseDepthFirst(
        node: NNode?,
        action: (value: Int?) -> Unit
    ) {

        val stack = ArrayDeque<NNode?>()
        stack.addFirst(node)

        while (stack.isNotEmpty()) {
            val currentNode = stack.removeLast()

            action.invoke(currentNode?.value)

            if (currentNode != null) {
                for (index in currentNode.listChildren.size - 1 downTo (0)) {
                    stack.add(currentNode.listChildren[index])
                }
            }
        }
    }
}
