package ru.job4j.treeExamples;


public class Main {
    public static void main(String[] args) {
        Tree root =
                new Tree(20,
                    new Tree(7,
                        new Tree(4, null, new Tree(6)), new Tree(9)),
                    new Tree(35,
                        new Tree(31, new Tree(28), null),
                        new Tree(40, new Tree(38), new Tree(52))));
        System.out.println(sumDeep(root));
    }

    static class Tree {
        int value;
        Tree left;
        Tree right;

        public Tree(int value, Tree left, Tree right) {
            this.value = value;
            this.left = left;
            this.right = right;
        }

        public Tree(int value) {
            this.value = value;
        }
    }

    public static int sumRecursuve(Tree root) {
        int summ = root.value;

        if (root.left != null) {
            summ += sumRecursuve(root.left);
        }

        if (root.right != null) {
            summ += sumRecursuve(root.right);
        }
        return summ;
    }

    public static int sumDeep(Tree root) {
        SimpleStack<Tree> stack = new SimpleStack<>();
        stack.push(root);

        int summ = 0;
        while (!stack.isEmpty()) {
            Tree node = stack.pop();

            System.out.println(node.value);

            summ += node.value;

            if (node.right != null) {
                stack.push(node.right);
            }

            if (node.left != null) {
                stack.push(node.left);
            }
        }
        return summ;
    }

    public static int sumWide(Tree root) {
        SimpleQueue<Tree> queue = new SimpleQueue<>();
        queue.add(root);

        int summ = 0;

        while (!queue.isEmpty()) {
            Tree node = queue.remove();

            System.out.println(node.value);

            summ += node.value;

            if (node.left != null) {
                queue.add(node.left);
            }

            if (node.right != null) {
                queue.add(node.right);
            }

        }
        return summ;
    }
}
