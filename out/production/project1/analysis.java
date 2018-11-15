import java.io.*;
import java.util.ArrayList;

public class analysis {
    private static RB_Tree rb_tree = new RB_Tree();
    private static BplusTree<String,String> bPlusTree = new BplusTree<String, String>(4);
    private static String path;
    private static long beforeTime , afterTime;
    private static ArrayList<Long> time1 = new ArrayList<>();
    private static ArrayList<Long> time2 = new ArrayList<>();
    private static ArrayList<Long> time3 = new ArrayList<>();
    private static ArrayList<Long> time4 = new ArrayList<>();
    private static ArrayList<Long> time5 = new ArrayList<>();
    private static ArrayList<Long> time6 = new ArrayList<>();
    private static ArrayList<Long> time7 = new ArrayList<>();
    private static ArrayList<Long> time8 = new ArrayList<>();
    private static ArrayList<Long> time9 = new ArrayList<>();
    private static ArrayList<Long> time10 = new ArrayList<>();

    public static void main(String[] args){
        RB_insert_test();
        BPlus_insert_test();
        RB_delete_test();
        BPlus_delete_test();
        RB_add_test();
        BPlus_add_test();
        RB_query_test();
        BPlus_query_test();
        RB_queries_test("cat" , "ant");
        BPlus_queries_test("ant" , "cat");
        printTime();
    }

    //The RB tree's insert test function , insert the data of 1_initial.txt
    private static void RB_insert_test(){
        path = "./src/1_initial.txt";
        try{
            String key,value;
            File file = new File(path);
            InputStreamReader isr = new InputStreamReader(new FileInputStream(file), "GBK");
            BufferedReader br = new BufferedReader(isr);
            int line = 0;
            br.readLine();
            //Read the first line , to read the data behind it.

            beforeTime = System.nanoTime();
            while((key=br.readLine()) != null && (value=br.readLine()) != null){
                //Read two data each time
                rb_tree.insert(rb_tree , new RB_Node(key,value));
                //Insert the data use tree's insert function
                line+=2;
                if(line%200 == 0){
                    afterTime = System.nanoTime();
                    time1.add(afterTime - beforeTime);
                    //Add the time of insert into the arrayList time1
                    if(rb_tree.size <= 500){
                        System.out.println("RBTree's preOrderPrint:");
                        rb_tree.preOrderPrint(rb_tree);
                        System.out.println("The tree's current line is " + line/2);
                    }

                    beforeTime = System.nanoTime();
                    //Update the beforeTime
                }//Print the result after the insertion
            }
            isr.close();
            br.close();
        }catch (IOException ioe){
            ioe.printStackTrace();
        }
    }

    //The BPlus tree's insert test function , insert the data of 1_initial.txt
    private static void BPlus_insert_test(){
        try{
            String key,value;
            File file = new File(path);
            InputStreamReader isr = new InputStreamReader(new FileInputStream(file), "GBK");
            BufferedReader br = new BufferedReader(isr);
            int line = 0;
            br.readLine();
            //Read the first line , to read the data behind it.

            beforeTime = System.nanoTime();
            while((key=br.readLine()) != null && (value=br.readLine()) != null){
//                Read two data each time
                bPlusTree.insertOrUpdate(key,value);
//                Insert the data use tree's insert function
                line+=2;
                if(line%200 == 0){
                    afterTime = System.nanoTime();
                    time2.add(afterTime - beforeTime);
                    //Add the time of insert into the arrayList time2
                    if(bPlusTree.getSize() <= 500){
                        System.out.println("BPlusTree's preOrderPrint:");
                        bPlusTree.preOrderPrint(bPlusTree);
                        System.out.println("The tree's current line is " + line/2);
                    }

                    beforeTime = System.nanoTime();
                    //Update the beforeTime
                }//Print the result after the insertion
            }
            isr.close();
            br.close();
        }catch (IOException ioe){
            ioe.printStackTrace();
        }
    }

    //The RB tree's delete test function , delete the data of 2_delete.txt after calling the insert function of RB tree.
    private static void RB_delete_test(){
        path = "./src/2_delete.txt";
        try{
            String key;
            File file = new File(path);
            InputStreamReader isr = new InputStreamReader(new FileInputStream(file), "GBK");
            BufferedReader br = new BufferedReader(isr);
            int line = 0;
            br.readLine();
            //Read the first line , to read the data behind it.

            beforeTime = System.nanoTime();
            while((key=br.readLine()) != null){
                //Read two data each time
                rb_tree.delete(rb_tree , rb_tree.search(rb_tree.rootNode,key));
                //Delete the data use tree's delete function
                line+=1;
                if(line % 100 == 0 ){
                    afterTime = System.nanoTime();
                    time3.add(afterTime - beforeTime);
                    //Add the time of insert into the arrayList time3
                    if(rb_tree.size <= 500){
                        System.out.println("RBTree's delete operation: ");
                        rb_tree.preOrderPrint(rb_tree);
                        System.out.println("Before delete the tree's line is " + (rb_tree.size + 100) + " and current line is " + rb_tree.size);
                    }
                    beforeTime = System.nanoTime();
                    //Update the beforeTime
                }//Print the result after the deletion
            }
            isr.close();
            br.close();
        }catch (IOException ioe){
            ioe.printStackTrace();
        }
    }

    //The BPlus tree's delete test function , delete the data of 2_delete.txt after calling the insert function of BPlus tree.
    private static void BPlus_delete_test(){
        try{
            String key;
            File file = new File(path);
            InputStreamReader isr = new InputStreamReader(new FileInputStream(file), "GBK");
            BufferedReader br = new BufferedReader(isr);
            int line = 0;
            br.readLine();
            //Read the first line , to read the data behind it.

            beforeTime = System.nanoTime();
            while((key=br.readLine()) != null){
                //Read two data each time
                bPlusTree.remove(key);
                //Delete the data use tree's delete function
                line+=1;
                if(line % 100 == 0){
                    afterTime = System.nanoTime();
                    time4.add(afterTime - beforeTime);
                    //Add the time of insert into the arrayList time4
                    if(bPlusTree.getSize() <= 500){
                        System.out.println("RBTree's delete operation: ");
                        bPlusTree.preOrderPrint(bPlusTree);
                        System.out.println("Before delete the tree's line is " + (rb_tree.size + 100) + " and current line is " + rb_tree.size);
                    }
                    beforeTime = System.nanoTime();
                    //Update the beforeTime
                }//Print the result after the deletion
            }
            isr.close();
            br.close();
        }catch (IOException ioe){
            ioe.printStackTrace();
        }
    }

    //The RB tree's add test function , insert the data of 3_insert.txt after calling the delete function of RB tree
    private static void RB_add_test(){
        path = "./src/3_insert.txt";
        try{
            String key,value;
            File file = new File(path);
            InputStreamReader isr = new InputStreamReader(new FileInputStream(file), "GBK");
            BufferedReader br = new BufferedReader(isr);
            int line = 0;
            br.readLine();
            //Read the first line , to read the data behind it.

            beforeTime = System.nanoTime();
            while((key=br.readLine()) != null && (value=br.readLine()) != null){
                //Read two data each time
                rb_tree.insert(rb_tree , new RB_Node(key,value));
                //Insert the data use tree's insert function
                line+=2;
                if(line%200 == 0){
                    afterTime = System.nanoTime();
                    time5.add(afterTime - beforeTime);
                    //Add the time of insert into the arrayList time5
                    if(rb_tree.size <= 500){
                        System.out.println("RBTree's delete operation: ");
                        rb_tree.preOrderPrint(rb_tree);
                        System.out.println("Before delete the tree's line is " + (rb_tree.size + 100) + " and current line is " + rb_tree.size);
                    }
                    beforeTime = System.nanoTime();
                    //Update the beforeTime
                }//Print the result after the insertion
            }
            isr.close();
            br.close();
        }catch (IOException ioe){
            ioe.printStackTrace();
        }
    }

    //The RB tree's add test function , insert the data of 3_insert.txt after calling the delete function of RB tree
    private static void BPlus_add_test(){
        try{
            String key,value;
            File file = new File(path);
            InputStreamReader isr = new InputStreamReader(new FileInputStream(file), "GBK");
            BufferedReader br = new BufferedReader(isr);
            int line = 0;
            br.readLine();
            //Read the first line , to read the data behind it.

            beforeTime = System.nanoTime();
            while((key=br.readLine()) != null && (value=br.readLine()) != null){
                //Read two data each time
                bPlusTree.insertOrUpdate(key,value);
                //Insert the data use tree's insert function
                line+=2;
                if(line%200 == 0){
                    afterTime = System.nanoTime();
                    time6.add(afterTime - beforeTime);
                    //Add the time of insert into the arrayList time6
                    if(bPlusTree.getSize() <= 500){
                        System.out.println("RBTree's delete operation: ");
                        bPlusTree.preOrderPrint(bPlusTree);
                        System.out.println("Before delete the tree's line is " + (rb_tree.size + 100) + " and current line is " + rb_tree.size);
                    }
                    beforeTime = System.nanoTime();
                    //Update the beforeTime
                }//Print the result after the insertion
            }
            isr.close();
            br.close();
        }catch (IOException ioe){
            ioe.printStackTrace();
        }
    }

    //The RB tree's search a word , randomly search 10 words from the RB tree
    private static void RB_query_test(){
        System.out.println("The RB search: ");
        ArrayList<String> arrayList = new ArrayList<>();
        String thePath = "./src/1_initial.txt";
        try{
            String key,value;
            FileReader fr = new FileReader(thePath);
            BufferedReader br = new BufferedReader(fr);
            br.readLine();
            //Read the first line , to read the data behind it.

            while((key=br.readLine()) != null && (value=br.readLine()) != null){
                //Read two data each time
                if(!arrayList.contains(key)){
                    arrayList.add(key);
                }
            }
            fr.close();
            br.close();
        }catch (IOException ioe){
            ioe.printStackTrace();
        }
        for(int i = 0 ; i < 10 ; i++){
            int number = (1 + (int)(Math.random() * 100));
            beforeTime = System.nanoTime();
            String value = rb_tree.search(rb_tree.rootNode , arrayList.get(number)).value;
            afterTime = System.nanoTime();
            System.out.println("The English is " + arrayList.get(number) + " and the Chinese is " + value);
            time7.add(afterTime - beforeTime);
        }
    }

    //The BPlus tree's search a word , randomly search 10 words from the BPlus tree
    private static void BPlus_query_test(){
        System.out.println("The BPlus search: ");
        ArrayList<String> arrayList = new ArrayList<>();
        String thePath = "./src/1_initial.txt";
        try{
            String key,value;
            FileReader fr = new FileReader(thePath);
            BufferedReader br = new BufferedReader(fr);
            br.readLine();
            //Read the first line , to read the data behind it.

            while((key=br.readLine()) != null && (value=br.readLine()) != null){
                //Read two data each time
                if(!arrayList.contains(key)){
                    arrayList.add(key);
                }
            }
            fr.close();
            br.close();
        }catch (IOException ioe){
            ioe.printStackTrace();
        }
        for(int i = 0 ; i < 10 ; i++){
            int number = (1 + (int)(Math.random() * 100));
            beforeTime = System.nanoTime();
            String value = bPlusTree.get(arrayList.get(number));
            afterTime = System.nanoTime();
            if(value == null){
                System.out.println("The English : " + arrayList.get(number) + " doesn't exist");
            }else
                System.out.println("The English is " + arrayList.get(number) + " and the Chinese is " + value);
            time8.add(afterTime - beforeTime);
        }
    }

    //The RB tree's range search function , specifically search the words between these two words
    private static void RB_queries_test(String key1 , String key2){
        if(key1.compareTo(key2) > 0){
            String temp = key1;
            key1 = key2;
            key2 = temp;
        }
        System.out.println("The range search of RB tree between " + key1 + " and " + key2 + " :");
        beforeTime = System.nanoTime();
        RB_queries_test(rb_tree.rootNode , key1 , key2);
        afterTime = System.nanoTime();
        time9.add(afterTime - beforeTime);
    }

    private static void RB_queries_test(RB_Node x , String key1 , String key2){
        if(x != null && x != rb_tree.nilNode){
            RB_queries_test(x.leftNode , key1 , key2);
            if(x.key.compareTo(key1) >= 0 && x.key.compareTo(key2) <= 0){
                System.out.println("English: " + x.key + " Chinese: " + x.value);
            }
            RB_queries_test(x.rightNode , key1 , key2);
        }
    }

    //The BPlus tree's range search function , specifically search the words between these two words
    private static void BPlus_queries_test(String key1 , String key2){
        if(key1.compareTo(key2) > 0){
            String temp = key1;
            key1 = key2;
            key2 = temp;
        }
        System.out.println("The range search of BPlus tree between " + key1 + " and " + key2 + " :");
        beforeTime = System.nanoTime();
        BPlus_queries_test(bPlusTree.root , key1 , key2);
        afterTime = System.nanoTime();
        time10.add(afterTime - beforeTime);
    }

    private static void BPlus_queries_test(BplusNode<String , String> x , String key1 , String key2){
        if(x.isLeaf){
            for(int i = 0 ; i < x.entries.size() ; i++){
                if(x.entries.get(i).getKey().compareTo(key1) >= 0 && x.entries.get(i).getKey().compareTo(key2) <= 0){
                    System.out.println("English: " + x.entries.get(i).getKey() + " Chinese: " + x.entries.get(i).getValue());
                }
            }
            if(x.next != null){
                BPlus_queries_test(x.next,key1,key2);
            }
        }else {
            BPlus_queries_test(x.children.get(0) , key1 , key2);
        }
    }

    //The function is to print all the operation's cost time
    private static void printTime(){
        long average = 0;
        System.out.print("The initial of RB tree's cost time(every 100 operations): ");
        for (Long Time1 : time1) {
            average += Time1;
            System.out.print(Time1 + " ");
        }
        System.out.println();
        System.out.println(average / 50);

        average = 0;
        System.out.print("The initial of BPlus tree's cost time(every 100 operations): ");
        for (Long Time2 : time2) {
            average += Time2;
            System.out.print(Time2 + " ");
        }
        System.out.println();
        System.out.println(average / 50);

        average = 0;
        System.out.print("The delete of RB tree's cost time(every 100 operations): ");
        for (Long Time3 : time3) {
            average += Time3;
            System.out.print(Time3 + " ");
        }
        System.out.println();
        System.out.println(average / 10);

        average = 0;
        System.out.print("The delete of BPlus tree's cost time(every 100 operations): ");
        for (Long Time4 : time4) {
            average += Time4;
            System.out.print(Time4 + " ");
        }
        System.out.println();
        System.out.println(average / 10);

        average = 0;
        System.out.print("The insert of RB tree's cost time(every 100 operations): ");
        for (Long Time5 : time5) {
            average += Time5;
            System.out.print(Time5 + " ");
        }
        System.out.println();
        System.out.println(average / 10);

        average = 0;
        System.out.print("The insert of BPlus tree's cost time(every 100 operations): ");
        for (Long Time6 : time6) {
            average += Time6;
            System.out.print(Time6 + " ");
        }
        System.out.println();
        System.out.println(average / 10);

        average = 0;
        System.out.print("The search of RB tree's cost time(each word): ");
        for (Long Time7 : time7) {
            average += Time7;
            System.out.print(Time7 + " ");
        }
        System.out.println();
        System.out.println(average / 10);

        average = 0;
        System.out.print("The search of BPlus tree's cost time(each word): ");
        for (Long Time8 : time8) {
            average += Time8;
            System.out.print(Time8 + " ");
        }
        System.out.println();
        System.out.println(average / 10);

        System.out.print("The range search of RB tree's cost time(every two words): ");
        for (Long Time9 : time9) {
            System.out.print(Time9 + " ");
        }
        System.out.println();

        System.out.print("The range search of BPlus tree's cost time(every two words): ");
        for (Long Time10 : time10) {
            System.out.print(Time10 + " ");
        }
        System.out.println();
    }
}
