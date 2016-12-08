An implementation of Red Black trees in Java, with the following features.

<b>Red Black tree Introduction</b><br>
the longest path from the root to a leaf cannot be more than twice of the shortest path from the root to a leaf. This means that the tree is always balanced and the operations are always O(logn).

Red Black Tree has these properties:<br>
1. Every node is either red or black<br>
2. Every leaf (NULL pointer) is black<br>
3. If a node is red, both children are black<br>
4. Every path from node to descendent leaf contains the same number of black nodes<br>
5. The root is always black<br>

<b>Red Black tree Methods<b><br>

My implementation consists of the following methods, the most critical of which is RBTree Fixup to ensure that the RB tree maintains its properties after new nodes are added.<br><br>

while x != root[T] and color[x] = BLACK<br>
    do if x = left[p[x]]<br>
          then w := right[p[x]]<br>
               if color[w] = RED<br>
                  then color[w] := BLACK<br>
                       color[p[x]] := RED<br>
                       Left-Rotate(T,p[x])<br>
                       w := right[p[x]]<br>
                if color[left[w]] = BLACK and color[right[w]] = BLACK<br>
                   then color[w] := RED<br>
                        x := p[x]<br>
                   else if color[right[w]] = BLACK<br>
                           then color[left[w]] := BLACK<br>
                                color[w] := RED<br>
                                Right-Rotate(T,w)<br>
                                w := right[p[x]]<br>
                        color[w] := color[p[x]]<br>
                        color[p[x]] := BLACK<br>
                        color[right[w]] := BLACK<br>
                        Left-Rotate(T,p[x])<br>
                        x := root[T]<br>
       else (same as then clause<br>
             with "right" and "left" exchanged)<br>
color[x] := BLACK<br>
<br>
printTree(): Start at the root node and traverse the tree using preorder
<br>
addNode(String): place a new node in the binary search tree with data the parameter and color it red.<br>

getSibling(RBNode): returns the sibling node of the parameter If the sibling does not
exist, then return null.<br>

getAunt(RBNode): returns the aunt of the parameter or the sibling of the parent node.
If the aunt node does not exist, then return null.<br>

getGrandparent(RBNode): Similar to getAunt() and getSibling(), returns the parent of
your parent node, if it doesn’t exist return null.<br>

rotateLeft(RBNode) and rotateRight(RBNode) functions: left, resp. right, rotate
around the node parameter. See next figure that demonstrates the two rotations:<br>

fixTree(RBNode current) recursive function: recursively traverse the tree to
make it a Red Black tree. <br>


<b>The RBTTester contains Junit test cases to verify the methods used and outlined above.<b><br>
