package lab6;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class RedBlackTree {
    private enum Color { RED, BLACK }

    private final Node NIL = new Node(0, Color.BLACK, null, null, null);
    private Node root = NIL;

    private final class Node {
        int key;
        Color color;
        Node left;
        Node right;
        Node parent;

        Node(int key, Color color, Node left, Node right, Node parent) {
            this.key = key;
            this.color = color;
            this.left = (left == null ? NIL : left);
            this.right = (right == null ? NIL : right);
            this.parent = (parent == null ? NIL : parent);
        }
    }

    public boolean isEmpty() {
        return root == NIL;
    }

    public void clear() {
        root = NIL;
    }

    public boolean contains(int key) {
        return searchNode(key) != NIL;
    }

    private Node searchNode(int key) {
        Node x = root;
        while (x != NIL) {
            if (key == x.key) return x;
            x = key < x.key ? x.left : x.right;
        }
        return NIL;
    }

    // Insertion
    public void insert(int key) {
        Node z = new Node(key, Color.RED, NIL, NIL, NIL);
        Node y = NIL;
        Node x = root;
        while (x != NIL) {
            y = x;
            if (z.key < x.key) x = x.left; else x = x.right;
        }
        z.parent = y;
        if (y == NIL) {
            root = z;
        } else if (z.key < y.key) {
            y.left = z;
        } else {
            y.right = z;
        }
        z.left = NIL;
        z.right = NIL;
        z.color = Color.RED;
        insertFixup(z);
    }

    private void insertFixup(Node z) {
        while (z.parent.color == Color.RED) {
            if (z.parent == z.parent.parent.left) {
                Node y = z.parent.parent.right; // uncle
                if (y.color == Color.RED) {
                    z.parent.color = Color.BLACK;
                    y.color = Color.BLACK;
                    z.parent.parent.color = Color.RED;
                    z = z.parent.parent;
                } else {
                    if (z == z.parent.right) {
                        z = z.parent;
                        leftRotate(z);
                    }
                    z.parent.color = Color.BLACK;
                    z.parent.parent.color = Color.RED;
                    rightRotate(z.parent.parent);
                }
            } else {
                Node y = z.parent.parent.left; // uncle
                if (y.color == Color.RED) {
                    z.parent.color = Color.BLACK;
                    y.color = Color.BLACK;
                    z.parent.parent.color = Color.RED;
                    z = z.parent.parent;
                } else {
                    if (z == z.parent.left) {
                        z = z.parent;
                        rightRotate(z);
                    }
                    z.parent.color = Color.BLACK;
                    z.parent.parent.color = Color.RED;
                    leftRotate(z.parent.parent);
                }
            }
        }
        root.color = Color.BLACK;
    }

    private void leftRotate(Node x) {
        Node y = x.right;
        x.right = y.left;
        if (y.left != NIL) y.left.parent = x;
        y.parent = x.parent;
        if (x.parent == NIL) root = y;
        else if (x == x.parent.left) x.parent.left = y;
        else x.parent.right = y;
        y.left = x;
        x.parent = y;
    }

    private void rightRotate(Node x) {
        Node y = x.left;
        x.left = y.right;
        if (y.right != NIL) y.right.parent = x;
        y.parent = x.parent;
        if (x.parent == NIL) root = y;
        else if (x == x.parent.right) x.parent.right = y;
        else x.parent.left = y;
        y.right = x;
        x.parent = y;
    }

    // Deletion (optional but implemented)
    public boolean delete(int key) {
        Node z = searchNode(key);
        if (z == NIL) return false;
        Node y = z;
        Color yOriginalColor = y.color;
        Node x;
        if (z.left == NIL) {
            x = z.right;
            transplant(z, z.right);
        } else if (z.right == NIL) {
            x = z.left;
            transplant(z, z.left);
        } else {
            y = minimum(z.right);
            yOriginalColor = y.color;
            x = y.right;
            if (y.parent == z) {
                x.parent = y;
            } else {
                transplant(y, y.right);
                y.right = z.right;
                y.right.parent = y;
            }
            transplant(z, y);
            y.left = z.left;
            y.left.parent = y;
            y.color = z.color;
        }
        if (yOriginalColor == Color.BLACK) deleteFixup(x);
        return true;
    }

    private void transplant(Node u, Node v) {
        if (u.parent == NIL) root = v;
        else if (u == u.parent.left) u.parent.left = v;
        else u.parent.right = v;
        v.parent = u.parent;
    }

    private Node minimum(Node x) {
        while (x.left != NIL) x = x.left;
        return x;
    }

    private void deleteFixup(Node x) {
        while (x != root && x.color == Color.BLACK) {
            if (x == x.parent.left) {
                Node w = x.parent.right;
                if (w.color == Color.RED) {
                    w.color = Color.BLACK;
                    x.parent.color = Color.RED;
                    leftRotate(x.parent);
                    w = x.parent.right;
                }
                if (w.left.color == Color.BLACK && w.right.color == Color.BLACK) {
                    w.color = Color.RED;
                    x = x.parent;
                } else {
                    if (w.right.color == Color.BLACK) {
                        w.left.color = Color.BLACK;
                        w.color = Color.RED;
                        rightRotate(w);
                        w = x.parent.right;
                    }
                    w.color = x.parent.color;
                    x.parent.color = Color.BLACK;
                    w.right.color = Color.BLACK;
                    leftRotate(x.parent);
                    x = root;
                }
            } else {
                Node w = x.parent.left;
                if (w.color == Color.RED) {
                    w.color = Color.BLACK;
                    x.parent.color = Color.RED;
                    rightRotate(x.parent);
                    w = x.parent.left;
                }
                if (w.right.color == Color.BLACK && w.left.color == Color.BLACK) {
                    w.color = Color.RED;
                    x = x.parent;
                } else {
                    if (w.left.color == Color.BLACK) {
                        w.right.color = Color.BLACK;
                        w.color = Color.RED;
                        leftRotate(w);
                        w = x.parent.left;
                    }
                    w.color = x.parent.color;
                    x.parent.color = Color.BLACK;
                    w.left.color = Color.BLACK;
                    rightRotate(x.parent);
                    x = root;
                }
            }
        }
        x.color = Color.BLACK;
    }

    // Traversals
    public List<Integer> inOrder() {
        List<Integer> res = new ArrayList<>();
        traverseInOrder(root, v -> res.add(v));
        return res;
    }

    public List<Integer> preOrder() {
        List<Integer> res = new ArrayList<>();
        traversePreOrder(root, v -> res.add(v));
        return res;
    }

    public List<Integer> postOrder() {
        List<Integer> res = new ArrayList<>();
        traversePostOrder(root, v -> res.add(v));
        return res;
    }

    private void traverseInOrder(Node node, Consumer<Integer> visit) {
        if (node == NIL) return;
        traverseInOrder(node.left, visit);
        visit.accept(node.key);
        traverseInOrder(node.right, visit);
    }

    private void traversePreOrder(Node node, Consumer<Integer> visit) {
        if (node == NIL) return;
        visit.accept(node.key);
        traversePreOrder(node.left, visit);
        traversePreOrder(node.right, visit);
    }

    private void traversePostOrder(Node node, Consumer<Integer> visit) {
        if (node == NIL) return;
        traversePostOrder(node.left, visit);
        traversePostOrder(node.right, visit);
        visit.accept(node.key);
    }

    public void printTree() {
        printTree(root, 0);
    }

    private void printTree(Node node, int indent) {
        if (node == NIL) return;
        printTree(node.right, indent + 4);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < indent; i++) sb.append(' ');
        sb.append(node.key).append(node.color == Color.RED ? "(R)" : "(B)");
        System.out.println(sb.toString());
        printTree(node.left, indent + 4);
    }
}

