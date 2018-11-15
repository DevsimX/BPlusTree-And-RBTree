public class RB_Tree {
    RB_Node rootNode;
    RB_Node nilNode;
    long size = 0;
    //Use to store the data and put out in the UI
    private String out = "";

    //RB_Tree's construct function
    RB_Tree(){
        nilNode = new RB_Node();
        rootNode = nilNode;
    }

    //RB_Tree's basic functions from the line 14 to line 299
    private void leftRotate(RB_Tree T , RB_Node x){
        RB_Node y = x.rightNode;

        x.rightNode = y.leftNode;

        if(y.leftNode != T.nilNode){
            y.leftNode.parentNode = x;
        }

        y.parentNode = x.parentNode;

        if(x.parentNode == T.nilNode){
            T.rootNode = y;
        }else if(x == x.parentNode.leftNode){
            x.parentNode.leftNode = y;
        }else {
            x.parentNode.rightNode = y;
        }

        y.leftNode = x;

        x.parentNode = y;
    }

    private void rightRotate(RB_Tree T , RB_Node x){
        RB_Node y = x.leftNode;

        x.leftNode = y.rightNode;

        if(y.rightNode != T.nilNode){
            y.rightNode.parentNode = x;
        }

        y.parentNode = x.parentNode;

        if(x.parentNode == T.nilNode){
            T.rootNode = y;
        }else if(x == x.parentNode.rightNode){
            x.parentNode.rightNode = y;
        }else {
            x.parentNode.leftNode = y;
        }

        y.rightNode = x;

        x.parentNode = y;
    }

    void insert(RB_Tree T , RB_Node z){
        if(checkExist(z.key)){
            search(rootNode , z.key).value = z.value;
            return;
        }

        RB_Node y = T.nilNode;

        RB_Node x = T.rootNode;

        while (x != T.nilNode){
            y = x;

            if(z.key.compareTo(x.key) < 0){
                x = x.leftNode;
            }else {
                x = x.rightNode;
            }
        }
        z.parentNode = y;

        if(y == T.nilNode){
            T.rootNode = z;
        }else if(z.key.compareTo(y.key) < 0){
            y.leftNode = z;
        }else {
            y.rightNode = z;
        }

        z.leftNode = T.nilNode;

        z.rightNode = T.nilNode;

        z.color = true;

        fixUp(T,z);

        size++;
    }

    private void fixUp(RB_Tree T , RB_Node z){
        RB_Node y;
        while (z.parentNode.color){
            if(z.parentNode == z.parentNode.parentNode.leftNode){
                y = z.parentNode.parentNode.rightNode;

                if(y.color){
                    z.parentNode.color = false;

                    y.color = false;

                    z.parentNode.parentNode.color = true;

                    z = z.parentNode.parentNode;
                }else {
                    if(z == z.parentNode.rightNode){
                        z = z.parentNode;
                        leftRotate(T,z);
                    }
                    z.parentNode.color = false;

                    z.parentNode.parentNode.color = true;

                    rightRotate(T,z.parentNode.parentNode);
                }
            }else {
                y = z.parentNode.parentNode.leftNode;

                if(y.color){
                    z.parentNode.color = false;

                    y.color = false;

                    z.parentNode.parentNode.color = true;

                    z = z.parentNode.parentNode;
                }else{
                    if(z == z.parentNode.leftNode){
                        z = z.parentNode;
                        rightRotate(T,z);
                    }

                    z.parentNode.color = false;

                    z.parentNode.parentNode.color = true;

                    leftRotate(T,z.parentNode.parentNode);
                }
            }
        }
        T.rootNode.color = false;
    }

    void delete(RB_Tree T , RB_Node z){
        if(z == null || z == nilNode || !checkExist(z.key)){
            return;
        }
        RB_Node y = z;
        RB_Node x;

        Boolean originalColor = y.color;

        if(z.leftNode == T.nilNode){
            x = z.rightNode;

            transplant(T,z,z.rightNode);
        }else if(z.rightNode == T.nilNode){
            x = z.leftNode;
            transplant(T,z,z.leftNode);
        }else{
            y = minimum(z.rightNode);

            originalColor = y.color;

            x = y.rightNode;

            if(y.parentNode == z){
                x.parentNode = y;
            }else {
                transplant(T,y,y.rightNode);

                y.rightNode = z.rightNode;

                y.rightNode.parentNode = y;
            }

            transplant(T,z,y);

            y.leftNode = z.leftNode;

            y.leftNode.parentNode = y;

            y.color = z.color;
        }
        if(!originalColor)
            deleteFixUp(T,x);
        size--;
    }

    private void deleteFixUp(RB_Tree T , RB_Node x){
        RB_Node w;

        while (x != T.rootNode && !x.color){
            if(x == x.parentNode.leftNode){
                w = x.parentNode.rightNode;

                if(w.color){
                    w.color = false;

                    x.parentNode.color = true;

                    leftRotate(T,x.parentNode);

                    w = x.parentNode.rightNode;
                }

                if(!w.leftNode.color && !w.rightNode.color){
                    w.color = true;

                    x = x.parentNode;
                }else {
                    if(!w.rightNode.color){
                        w.leftNode.color = false;

                        w.color = true;

                        rightRotate(T,w);

                        w = x.parentNode.rightNode;
                    }

                    w.color = x.parentNode.color;

                    x.parentNode.color = false;

                    w.rightNode.color = false;

                    leftRotate(T,x.parentNode);

                    x = T.rootNode;
                }
            }else {
                w = x.parentNode.leftNode;

                if(w.color){
                    w.color = false;

                    x.parentNode.color = true;

                    rightRotate(T,x.parentNode);

                    w = x.parentNode.leftNode;
                }

                if(!w.leftNode.color && !w.rightNode.color){
                    w.color = true;

                    x = x.parentNode;
                }else {
                    if(!w.leftNode.color){
                        w.rightNode.color = false;

                        w.color = true;

                        leftRotate(T,w);

                        w = x.parentNode.leftNode;
                    }

                    w.color = x.parentNode.color;

                    x.parentNode.color = false;

                    w.leftNode.color = false;

                    rightRotate(T,x.parentNode);

                    x = T.rootNode;
                }
            }
        }
        x.color = false;
    }

    private RB_Node minimum(RB_Node x){
        while (x.leftNode != nilNode){
            x = x.leftNode;
        }
        return x;
    }

    private void transplant(RB_Tree T, RB_Node u, RB_Node v){
        if(u.parentNode == T.nilNode){
            T.rootNode = v;
        }else if(u == u.parentNode.leftNode){
            u.parentNode.leftNode = v;
        }else u.parentNode.rightNode = v;

        v.parentNode = u.parentNode;
    }

    //Traversal the RB_Tree by using the preOrder and print the result on the console
    void preOrderPrint(RB_Tree tree){
        preOrderPrint(tree.rootNode , 0 , 0);
    }

    //Reload function , recurrently call the function to implement preOrder
    private void preOrderPrint(RB_Node x , int level , int direction){
        if(x != null && x!= nilNode){
            System.out.println("level=" + level++ + " child=" + direction + " " + x.key + " " + x.value + "(" + getColor(x.color) + ")");

            preOrderPrint(x.leftNode , level , 0);

            preOrderPrint(x.rightNode , level , 1);
        }else {
            System.out.println("level=" + level + " child=" + direction + " null");
        }
    }

    //Change the boolean to String
    private String getColor(Boolean color){
        if(!color){
            return "BLACK";
        }else {
            return "RED";
        }
    }

    RB_Node search(RB_Node x , String key){
        while (x != nilNode && x != null && !key.equals(x.key)){
            if(key.compareTo(x.key) < 0){
                x = x.leftNode;
            }else x = x.rightNode;
        }

        return x;
    }

    //Check whether the key exist in the RB_Tree
    Boolean checkExist(String key){
        return search(rootNode, key) != null && search(rootNode, key) != nilNode;
    }

    //Same as the preOrder , but put the result in the variable out and use the variable to implement the UI.
    String getTree(){
        out = "";
        recurrent(rootNode , 0 , 0);
        return this.out;
    }

    //The getTree's recurrent function , same as the reload preOrderPrint function
    private void recurrent(RB_Node x , int level , int direction){
        if(x != null && x!= nilNode){
            out += "level=" + level++ + " child=" + direction + " " + x.key + " " + x.value + "(" + getColor(x.color) + ")" + "\n";

            recurrent(x.leftNode , level , 0);

            recurrent(x.rightNode , level , 1);
        }
    }

    //Implement the range search feature
    String rangeSearch(String key1 , String key2){
        return rangeSearch(rootNode , key1 , key2);
    }

    //The rangeSearch's recurrent function
    private String rangeSearch(RB_Node x , String key1 , String key2){
        if(x != null && x != nilNode){
            String string1 = rangeSearch(x.leftNode , key1 , key2);
            String string2 = "";
            if(x.key.compareTo(key2) > 0){
                return string1 + string2;
            }
            if(x.key.compareTo(key1) >= 0 && x.key.compareTo(key2) <= 0){
                string2 += "English: " + x.key + " Chinese: " + x.value + "\n";
            }
            String string3 = rangeSearch(x.rightNode , key1 , key2);
            return string1 + string2 + string3;
        }
        return "";
    }
}
